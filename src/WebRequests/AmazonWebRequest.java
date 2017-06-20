package WebRequests;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AmazonWebRequest implements WebRequest<String> {
    private final String id;

    public AmazonWebRequest(String id) {
        this.id = id;
    }

    public String resolve() {

        return ;
    }

    private InputStreamReader stream() {
        try {
            return new InputStreamReader(openConnection().getInputStream());
        } catch(Exception ex) {
            throw new RuntimeException("GET", ex);
        }
    }

    private HttpURLConnection openConnection() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) getUrl().openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", String.format("AWS4-HMAC-SHA256 Credential=%s/%s/us-west-2/s3/aws4_request,SignedHeaders=host;range;x-amz-date,Signature="));
        connection.setRequestProperty("x-amz-content-sha256", "UNSIGNED-PAYLOAD");
        connection.setRequestProperty("x-amz-date", DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now()));
        return connection;
    }

    private URL getUrl() throws MalformedURLException {
        return new URL("custom-magic-sets.s3.amazonaws.com/" + id);
    }
}
