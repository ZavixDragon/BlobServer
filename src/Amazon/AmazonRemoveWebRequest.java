package Amazon;

import Amazon.OO.StringText;
import Amazon.OO.Value;
import Amazon.OnlyOnePerRequest.DateStamp;
import Main.InputStreamToString;
import WebRequests.AmazonRequest;
import WebRequests.GetWebRequest;
import WebRequests.WebRequest;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

public class AmazonRemoveWebRequest implements WebRequest<String> {
    private final Value<String> bucket;
    private final Value<String> id;

    public AmazonRemoveWebRequest(String bucket, String id) {
        this(new StringText(bucket), new StringText(id));
    }

    public AmazonRemoveWebRequest(Value<String> bucket, Value<String> id) {
        this.bucket = bucket;
        this.id = id;
    }

    public String resolve() {
        Value<URL> url = new AmazonGetUrl(bucket, id);
        Map<String, String> headers = new AmazonRemoveHeaderBuilder(url, new DateStamp()).get().entrySet().stream().collect(Collectors.toMap(x -> x.getKey().get(), x -> x.getValue().get()));
        return new AmazonRequest(url.get(), "DELETE", headers, null).resolve();
    }
}
