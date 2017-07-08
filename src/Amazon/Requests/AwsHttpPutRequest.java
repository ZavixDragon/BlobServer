package Amazon.Requests;

import Amazon.Headers.Header;
import Amazon.OO.OOBytes.Bytes;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public final class AwsHttpPutRequest extends Bytes {
    private final Value<URL> url;
    private final Text httpMethod;
    private final Value<List<Header>> headers;
    private final Bytes body;

    public AwsHttpPutRequest(Value<URL> url, Text httpMethod, Value<List<Header>> headers, Bytes body) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.headers = headers;
        this.body = body;
    }

    public byte[] get() {
        try {
            HttpURLConnection connection = new AwsConnection(url, httpMethod, headers).get();
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(body.get());
            wr.flush();
            wr.close();
            return new AwsConnectionResponse(connection).get();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
