package WebRequests;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Supplier;

public class PostWebRequest implements WebRequest<InputStreamReader> {
    private Supplier<URL> getUrl;
    private String content;
    private String contentType;
    private String outputType;

    public PostWebRequest(String url, String content, String contentType, String outputType) {
        constructor(() -> {
            try {
                return new URL(url);
            } catch (MalformedURLException ex) {
                throw new RuntimeException(url, ex);
            }
        }, content, contentType, outputType);
    }

    public PostWebRequest(URL url, String content, String contentType, String outputType) {
        constructor(() -> url, content, contentType, outputType);
    }

    public PostWebRequest(Supplier<URL> getUrl, String content, String contentType, String outputType) {
        constructor(getUrl, content, contentType, outputType);
    }

    private void constructor(Supplier<URL> getUrl, String content, String contentType, String outputType) {
        this.getUrl = getUrl;
        this.content = content;
        this.contentType = contentType;
        this.outputType = outputType;
    }

    public InputStreamReader resolve() {
        try {
            HttpURLConnection connection = openConnection();
            sendPost(connection);
            return new InputStreamReader(connection.getInputStream());
        } catch(Exception ex) {
            throw new RuntimeException("POST", ex);
        }
    }

    private HttpURLConnection openConnection() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) getUrl.get().openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", contentType);
        connection.setRequestProperty("Accept", outputType);
        return connection;
    }

    private void sendPost(HttpURLConnection connection) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(content);
        writer.flush();
        writer.close();
    }
}
