package WebRequests;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PostWebRequest implements WebRequest<InputStream> {
    private final URL url;
    private final String content;
    private final String contentType;
    private final String outputType;
    private final String method;
    private final Map<String, String> headers;

    public PostWebRequest(URL url, String content, String contentType, String outputType) {
        this(url, content, contentType, outputType, "POST");
    }

    public PostWebRequest(URL url, String content, String contentType, String outputType, String method) {
        this(url, content, contentType, outputType, method, new HashMap<>());
    }

    public PostWebRequest(URL url, String content, String contentType, String outputType, String method, Map<String, String> headers) {
        this.url = url;
        this.content = content;
        this.contentType = contentType;
        this.outputType = outputType;
        this.method = method;
        this.headers = headers;
    }

    public InputStream resolve() {
        try {
            HttpURLConnection connection = openConnection();
            sendPost(connection);
            return connection.getInputStream();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private HttpURLConnection openConnection() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", contentType);
        connection.setRequestProperty("Accept", outputType);
        headers.entrySet().forEach(x -> connection.setRequestProperty(x.getKey(), x.getValue()));
        return connection;
    }

    private void sendPost(HttpURLConnection connection) throws IOException {
        DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
        writer.writeBytes(content);
        writer.flush();
        writer.close();
    }
}
