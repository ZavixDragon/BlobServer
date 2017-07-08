package Amazon.Requests;

import Amazon.Headers.Header;
import Amazon.OO.OOBytes.Bytes;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AwsHttpGetRequest extends Bytes {
    private final Value<URL> url;
    private final Text httpMethod;
    private final Value<List<Header>> headers;

    public AwsHttpGetRequest(Value<URL> url, Text httpMethod, Value<List<Header>> headers) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.headers = headers;
    }

    public byte[] get() {
        try {
            HttpURLConnection connection = new AwsConnection(url, httpMethod, headers).get();
            return new AwsConnectionResponse(connection).get();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
