package WebRequests;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AmazonGetWebRequest implements WebRequest<String> {
    private final String bucket;
    private final String id;
    private final String date;
    private final String method = "GET";
    private final String region = "us-west-2";
    private final String service = "s3";
    private final String host = "s3.amazonaws.com";

    public AmazonGetWebRequest(String bucket, String id) {
        this.bucket = bucket;
        this.id = id;
        date = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());
    }

    public String resolve() {
        return new InputStreamToString(stream()).get();
    }

    private InputStream stream() {
        try {
            return openConnection().getInputStream();
        } catch(Exception ex) {
            throw new RuntimeException("GET", ex);
        }
    }

    private HttpURLConnection openConnection() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) generateUrl().openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("x-amz-content-sha256", "UNSIGNED-PAYLOAD");
        connection.setRequestProperty("x-amz-date", date);
        connection.setRequestProperty("Authorization", generateAuthHeader());
        return connection;
    }

    private URL generateUrl() throws MalformedURLException {
        return new URL(String.format("%s.%s/%s", bucket, host, id));
    }

    private String generateAuthHeader() throws Exception {
        return String.format("AWS4-HMAC-SHA256 Credential=%s/%s/us-west-2/s3/aws4_request,SignedHeaders=%s,Signature=%s",
                getAccessKey(), generateCredentialScope(), getSignedHeaders(), generateSignature());
    }

    private String generateCredentialScope() {
        return String.format("%s/%s/%s/%s", date, region, service, "aws4_request");
    }

    private String getSignedHeaders() {
        return "host;x-amz-date";
    }

    private String generateSignature() throws Exception {
        return toHex(hmacSha256(generateStringToSign(), generateSignatureKey()));
    }

    private String generateStringToSign() throws Exception {
        return String.format("AWS4-HMAC-SHA256\n%s\n%s\n%s", date, generateCredentialScope(), hashCanonicalRequest());
    }

    private String hashCanonicalRequest() throws Exception {
        return toHex(sha256(generateCanonicalRequest()));
    }

    private String generateCanonicalRequest() throws Exception {
        return String.format("%s\n%s\n%s\n%s\n%s\n%s", method, generateCanonicalUri(), generateCanonicalQuery(), generateCanonicalHeaders(), getSignedHeaders(), toHex(sha256("")));
    }

    private String generateCanonicalUri() {
        return "/";
    }

    private String generateCanonicalQuery() {
        return "/" + id;
    }

    private String generateCanonicalHeaders() {
        return String.format("host:%s.%s\nx-amz-date:%s\n", bucket, host, date);
    }

    private byte[] generateSignatureKey() throws Exception {
        byte[] kSecret = ("AWS4" + getSecretKey()).getBytes("UTF8");
        byte[] kDate = hmacSha256(date, kSecret);
        byte[] kRegion = hmacSha256(region, kDate);
        byte[] kService = hmacSha256(service, kRegion);
        byte[] kSigning = hmacSha256("aws4_request", kService);
        return kSigning;
    }

    private String toHex(byte[] bytes) {
        return String.format("%0" + (bytes.length << 1) + "X", new BigInteger(1, bytes));
    }

    private byte[] hmacSha256(String data, byte[] key) throws Exception {
        String algorithm="HmacSHA256";
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(data.getBytes("UTF8"));
    }

    private byte[] sha256(String data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data.getBytes(StandardCharsets.UTF_8));
    }

    private String getAccessKey() {
        return "AKIAIWXCPOEBE4JEKPIA";
    }

    private String getSecretKey() {
        return "P9GKmu2Pgx6Az3Qp9cps2GzQOstVAzStjRJyV4pk";
    }
}
