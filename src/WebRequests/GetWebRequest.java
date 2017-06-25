package WebRequests;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GetWebRequest implements WebRequest<InputStream> {
    private URL url;
    private String method;
    private Map<String, String> headers;

    public GetWebRequest(URL url) {
        this(url, "GET");
    }

    public GetWebRequest(URL url, String method) {
        this(url, method, new HashMap<>());
    }

    public GetWebRequest(URL url, String method, Map<String, String> headers) {
        this.url = url;
        this.method = method;
        this.headers = headers;
    }

    public InputStream resolve() {
        HttpURLConnection connection = null;
        try {
            connection = openConnection();
            return connection.getInputStream();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private HttpURLConnection openConnection() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        headers.entrySet().forEach(x -> connection.setRequestProperty(x.getKey(), x.getValue()));
        return connection;
    }
}
