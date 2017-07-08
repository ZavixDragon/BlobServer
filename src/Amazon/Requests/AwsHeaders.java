package Amazon.Requests;

import Amazon.ConfigValues.AccessKey;
import Amazon.ConfigValues.SecretKey;
import Amazon.Headers.*;
import Amazon.OO.OOBytes.Bytes;
import Amazon.OO.OOBytes.CacheBytes;
import Amazon.OO.OOBytes.TextAsBytes;
import Amazon.OO.OOText.CacheText;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;
import Amazon.SimpleValues.*;
import Amazon.SmartValues.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class AwsHeaders implements Value<List<Header>> {
    private final Value<URL> url;
    private final Text httpMethod;
    private final Text region;
    private final Bytes content;

    public AwsHeaders(Value<URL> url, Text httpMethod, Text region) {
        this(url, httpMethod, region, new TextAsBytes(new SimpleText("")));
    }

    public AwsHeaders(Value<URL> url, Text httpMethod, Text region, Bytes content) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.region = region;
        this.content = content;
    }

    public List<Header> get() {
        DateStamp dateStamp = new DateStamp();
        Text scheme = new Scheme();
        Text algorithm = new Algorithm();
        Text serviceName = new ServiceName();
        Text termination = new Termination();
        Text accessKey = new AccessKey();
        Text credentialScope = new CacheText(new CredentialScope(dateStamp, region, serviceName, termination));
        Text credential = new CacheText(new Credential(accessKey, credentialScope));
        List<Header> headers = new ArrayList<>();
        Header contentHeader = new ContentHeader(content);
        headers.add(contentHeader);
        headers.add(new DateHeader(dateStamp));
        headers.add(new HostHeader(url));
        Text canonicalHeaderNames = new CacheText(new CanonicalHeaderNames(headers));
        Text canonicalPath = new CacheText(new CanonicalPath(url));
        Text canonicalQueryParameters = new CacheText(new CanonicalQueryParameters(url));
        Text canonicalHeaders = new CacheText(new CanonicalHeaders(headers));
        Text canonicalRequest = new CacheText(new CanonicalRequest(httpMethod, canonicalPath, canonicalQueryParameters, canonicalHeaders, canonicalHeaderNames, contentHeader));
        Text textToSign = new CacheText(new TextToSign(scheme, algorithm, dateStamp, credentialScope, canonicalRequest));
        Text secretKey = new SecretKey();
        Bytes signature = new CacheBytes(new Signature(algorithm, textToSign, termination, serviceName, region, dateStamp, scheme, secretKey));
        headers.add(new AuthorizationHeader(scheme, algorithm, credential, canonicalHeaderNames, signature));
        return headers;
    }
}
