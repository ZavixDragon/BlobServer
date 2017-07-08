package Amazon.Requests;

import Amazon.Headers.Header;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public final class AwsConnection implements Value<HttpURLConnection> {
    private final Value<URL> url;
    private final Text httpMethod;
    private final Value<List<Header>> headers;

    public AwsConnection(Value<URL> url, Text httpMethod, Value<List<Header>> headers) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.headers = headers;
    }

    public HttpURLConnection get() {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.get().openConnection();
            connection.setRequestMethod(httpMethod.get());
            headers.get().stream().forEach(x -> connection.setRequestProperty(x.getKey(), x.getValue()));
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            return connection;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
