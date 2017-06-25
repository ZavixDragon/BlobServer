package Amazon;

import Amazon.OO.FormattedText;
import Amazon.OO.StringText;
import Amazon.OO.Value;
import Amazon.SharedValues.Protocol;
import Amazon.SharedValues.RegionName;
import Amazon.Utilities.UrlFromString;

import java.net.URL;

public class AmazonPutUrl implements Value<URL> {
    private final Value<String> bucket;
    private final Value<String> id;

    public AmazonPutUrl(Value<String> bucket, Value<String> id) {
        this.bucket = bucket;
        this.id = id;
    }

    public URL get() {
        return new UrlFromString(
                new FormattedText(new StringText("%s://s3-%s.amazonaws.com/%s/%s"), new Protocol(), new RegionName(), bucket, id)).get();
    }
}
