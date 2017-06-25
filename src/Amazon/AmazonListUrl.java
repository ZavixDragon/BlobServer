package Amazon;

import Amazon.OO.FormattedText;
import Amazon.OO.StringText;
import Amazon.OO.Value;
import Amazon.SharedValues.Protocol;
import Amazon.Utilities.UrlFromString;

import java.net.URL;

public class AmazonListUrl implements Value<URL> {
    private final Value<String> bucket;

    public AmazonListUrl(Value<String> bucket) {
        this.bucket = bucket;
    }

    public URL get() {
        return new UrlFromString(
                new FormattedText(new StringText("%s://%s.s3.amazonaws.com/?list-type=2"), new Protocol(), bucket)).get();
    }
}
