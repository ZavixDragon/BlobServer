package WebRequests;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Supplier;

public class PostJsonWebRequest<Input, Output> implements WebRequest<Output> {
    private Supplier<URL> getUrl;
    private Input content;
    private Class<Output> outputType;
    private Gson gson;

    public PostJsonWebRequest(String url, Input content, Class<Output> outputType) {
        constructor(() -> {
            try {
                return new URL(url);
            } catch (MalformedURLException ex) {
                throw new RuntimeException(url, ex);
            }
        }, content, outputType);
    }

    public PostJsonWebRequest(URL url, Input content, Class<Output> outputType) {
        constructor(() -> url, content, outputType);
    }

    private void constructor(Supplier<URL> getUrl, Input content, Class<Output> outputType) {
        this.getUrl = getUrl;
        this.content = content;
        this.outputType = outputType;
        this.gson = new Gson();
    }

    public Output resolve() {
        InputStreamReader reader = new PostWebRequest(getUrl, gson.toJson(content), "application/json; charset=UTF-8", "application/json").resolve();
        return gson.fromJson(reader, outputType);
    }
}
