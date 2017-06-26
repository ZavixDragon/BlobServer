package Amazon;

import Amazon.OO.FormattedText;
import Amazon.OO.StringText;
import Amazon.OO.Value;
import Amazon.SharedValues.Protocol;
import Amazon.Utilities.TextAsUrl;

import java.net.URL;

public class AmazonPutBucketUrl implements Value<URL> {
    private final Value<String> bucket;

    public AmazonPutBucketUrl(Value<String> bucket) {
        this.bucket = bucket;
    }

    public URL get() {
        return new TextAsUrl(
                new FormattedText(new StringText("%s://%s.s3.amazonaws.com/"), new Protocol(), bucket)).get();
    }
}
