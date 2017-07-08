package Amazon.Requests.URLs;

import Amazon.OO.OOText.Format;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;
import Amazon.OO.TextAsUrl;

import java.net.URL;

public class PutBucketUrl implements Value<URL> {
    private final Text bucket;

    public PutBucketUrl(Text bucket) {
        this.bucket = bucket;
    }

    public URL get() {
        return new TextAsUrl(
                new Format(
                        new SimpleText("https://%s.s3.amazonaws.com/"),
                        bucket)).get();
    }
}
