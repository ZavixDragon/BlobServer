package Amazon;

import Amazon.OO.StringText;
import Amazon.OO.Value;
import Amazon.OnlyOnePerRequest.DateStamp;
import Main.InputStreamToString;
import WebRequests.AmazonRequest;
import WebRequests.PostWebRequest;
import WebRequests.WebRequest;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

public class AmazonPutWebRequest implements WebRequest<String> {
    private final Value<String> bucket;
    private final Value<String> id;
    private final Value<String> content;

    public AmazonPutWebRequest(String bucket, String id, String content) {
        this(new StringText(bucket), new StringText(id), new StringText(content));
    }

    public AmazonPutWebRequest(Value<String> bucket, Value<String> id, Value<String> content) {
        this.bucket = bucket;
        this.id = id;
        this.content = content;
    }

    public String resolve() {
        Value<URL> url = new AmazonPutUrl(bucket, id);
        Map<String, String> headers = new AmazonPutHeaderBuilder(url, content, new DateStamp()).get().entrySet().stream().collect(Collectors.toMap(x -> x.getKey().get(), x -> x.getValue().get()));
        return new AmazonRequest(url.get(), "PUT", headers, content.get()).resolve();
    }
}
