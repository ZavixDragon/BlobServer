package Amazon;

import Amazon.OO.FormattedText;
import Amazon.OO.StringText;
import Amazon.OO.Value;
import Amazon.SharedValues.Protocol;
import Amazon.Utilities.TextAsUrl;
import java.net.URL;

public final class AmazonGetUrl implements Value<URL> {
    private final Value<String> bucket;
    private final Value<String> id;

    public AmazonGetUrl(Value<String> bucket, Value<String> id) {
        this.bucket = bucket;
        this.id = id;
    }

    public URL get() {
        return new TextAsUrl(
                new FormattedText(new StringText("%s://%s.s3.amazonaws.com/%s"), new Protocol(), bucket, id)).get();
    }
}
