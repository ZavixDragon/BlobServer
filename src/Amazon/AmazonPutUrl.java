package Amazon;

import Amazon.OO.FormattedText;
import Amazon.OO.StringText;
import Amazon.OO.Value;
import Amazon.SharedValues.Protocol;
import Amazon.Utilities.TextAsUrl;

import java.net.URL;

public class AmazonPutUrl implements Value<URL> {
    private final Value<String> bucket;
    private final Value<String> id;

    public AmazonPutUrl(Value<String> bucket, Value<String> id) {
        this.bucket = bucket;
        this.id = id;
    }

    public URL get() {
        return new TextAsUrl(
                new FormattedText(new StringText("%s://s3-%s.amazonaws.com/%s/%s"), new Protocol(), new StringText("us-west-2"), bucket, id)).get();
    }
}
