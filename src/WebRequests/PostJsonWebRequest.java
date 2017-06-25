package WebRequests;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Supplier;

public class PostJsonWebRequest<Input, Output> implements WebRequest<Output> {
    private final URL url;
    private final Input content;
    private final Class<Output> outputType;
    private final Gson gson = new Gson();

    public PostJsonWebRequest(URL url, Input content, Class<Output> outputType) {
        this.url = url;
        this.content = content;
        this.outputType = outputType;
    }

    public Output resolve() {
        InputStreamReader reader = new InputStreamReader(new PostWebRequest(url, gson.toJson(content), "application/json; charset=UTF-8", "application/json").resolve());
        return gson.fromJson(reader, outputType);
    }
}
