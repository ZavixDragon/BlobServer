package Amazon;

import Amazon.OO.StringText;
import Amazon.OO.Value;
import Amazon.OnlyOnePerRequest.DateStamp;
import WebRequests.WebRequest;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AmazonListWebRequest implements WebRequest<List<String>> {
    private final Value<String> bucket;

    public AmazonListWebRequest(String bucket) {
        this(new StringText(bucket));
    }

    public AmazonListWebRequest(Value<String> bucket) {
        this.bucket = bucket;
    }

    public List<String> resolve() {
        Value<URL> url = new AmazonListUrl(bucket);
        Map<String, String> headers = new AmazonGetHeaderBuilder(url, new DateStamp(), new StringText("us-west-2")).get().entrySet().stream().collect(Collectors.toMap(x -> x.getKey().get(), x -> x.getValue().get()));
        return new AmazonListResponse(new AmazonRequest(url.get(), "GET", headers, null).resolve()).get();
    }
}
