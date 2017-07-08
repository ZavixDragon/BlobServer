package Amazon.Requests;

import Amazon.OO.CacheValue;
import Amazon.OO.OOBytes.Bytes;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;
import Amazon.Requests.URLs.GetUrl;

import java.net.URL;

public final class AwsGetRequest extends Bytes {
    private final Text bucket;
    private final Text id;

    public AwsGetRequest(Text bucket, Text id) {
        this.bucket = bucket;
        this.id = id;
    }

    public byte[] get() {
        Value<URL> url = new CacheValue<>(new GetUrl(bucket, id));
        Text httpMethod = new SimpleText("GET");
        return new AwsHttpGetRequest(url, httpMethod, new AwsHeaders(url, httpMethod, new SimpleText("us-west-2"))).get();
    }
}
