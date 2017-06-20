package WebRequests;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Supplier;

public class GetWebRequest implements WebRequest<InputStreamReader> {
    private Supplier<URL> getUrl;
    private String outputType;

    public GetWebRequest(String url, String outputType) {
        constructor(() -> {
            try {
                return new URL(url);
            } catch (MalformedURLException ex) {
                throw new RuntimeException(url, ex);
            }
        }, outputType);
    }

    public GetWebRequest(URL url, String outputType) {
        constructor(() -> url, outputType);
    }

    public GetWebRequest(Supplier<URL> getUrl, String outputType) {
        constructor(getUrl, outputType);
    }

    private void constructor(Supplier<URL> getUrl, String outputType) {
        this.getUrl = getUrl;
        this.outputType = outputType;
    }

    public InputStreamReader resolve() {
        try {
            return new InputStreamReader(openConnection().getInputStream());
        } catch(Exception ex) {
            throw new RuntimeException("GET", ex);
        }
    }

    private HttpURLConnection openConnection() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) getUrl.get().openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept", outputType);
        return connection;
    }
}
