package Amazon;

import Amazon.OO.StringText;
import Amazon.OO.Value;
import Amazon.OnlyOnePerRequest.DateStamp;
import Main.InputStreamToString;
import WebRequests.AmazonRequest;
import WebRequests.GetWebRequest;
import WebRequests.WebRequest;
import java.io.InputStream;
import java.net.*;
import java.util.Map;
import java.util.stream.Collectors;

public final class AmazonGetWebRequest implements WebRequest<String> {
    private final Value<String> bucket;
    private final Value<String> id;

    public AmazonGetWebRequest(String bucket, String id) {
        this(new StringText(bucket), new StringText(id));
    }

    public AmazonGetWebRequest(Value<String> bucket, Value<String> id) {
        this.bucket = bucket;
        this.id = id;
    }

    public String resolve() {
        Value<URL> url = new AmazonGetUrl(bucket, id);
        Map<String, String> headers = new AmazonGetHeaderBuilder(url, new DateStamp()).get().entrySet().stream().collect(Collectors.toMap(x -> x.getKey().get(), x -> x.getValue().get()));
        return new AmazonRequest(url.get(), "GET", headers, null).resolve();
    }
}
