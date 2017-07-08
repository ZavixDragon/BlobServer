package Amazon.Requests.URLs;

import Amazon.OO.OOText.Format;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;
import Amazon.OO.TextAsUrl;

import java.net.URL;

public class PutUrl implements Value<URL> {
    private final Text bucket;
    private final Text id;
    private final Text region;

    public PutUrl(Text bucket, Text id, Text region) {
        this.bucket = bucket;
        this.id = id;
        this.region = region;
    }

    public URL get() {
        return new TextAsUrl(
                new Format(
                        new SimpleText("https://s3-%s.amazonaws.com/%s/%s"),
                        region,
                        bucket,
                        id)).get();
    }
}
