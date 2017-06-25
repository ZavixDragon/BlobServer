package WebRequests;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class GetWebRequest implements WebRequest<InputStream> {
    private Supplier<URL> getUrl;
    private String outputType;
    private Map<String, String> headers;

    public GetWebRequest(String url, String outputType) {
        this(url, outputType, new HashMap<>());
    }

    public GetWebRequest(String url, String outputType, Map<String, String> headers) {
        constructor(() -> {
            try {
                return new URL(url);
            } catch (MalformedURLException ex) {
                throw new RuntimeException(url, ex);
            }
        }, outputType, headers);
    }

    public GetWebRequest(URL url, String outputType) {
        this(() -> url, outputType, new HashMap<>());
    }

    public GetWebRequest(URL url, String outputType, Map<String, String> headers) {
        constructor(() -> url, outputType, headers);
    }

    public GetWebRequest(Supplier<URL> getUrl, String outputType) {
        this(getUrl, outputType, new HashMap<>());
    }

    public GetWebRequest(Supplier<URL> getUrl, String outputType, Map<String, String> headers) {
        constructor(getUrl, outputType, headers);
    }

    private void constructor(Supplier<URL> getUrl, String outputType, Map<String, String> headers) {
        this.getUrl = getUrl;
        this.outputType = outputType;
        this.headers = headers;
    }

    public InputStream resolve() {
        try {
            return openConnection().getInputStream();
        } catch(Exception ex) {
            throw new RuntimeException("GET", ex);
        }
    }

    private HttpURLConnection openConnection() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) getUrl.get().openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setUseCaches(false);
        headers.entrySet().forEach(x -> connection.setRequestProperty(x.getKey(), x.getValue()));
        return connection;
    }
}
