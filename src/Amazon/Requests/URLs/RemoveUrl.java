package Amazon.Requests.URLs;

import Amazon.OO.OOText.Format;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;
import Amazon.OO.TextAsUrl;
import Amazon.OO.Value;

import java.net.URL;

public final class RemoveUrl implements Value<URL> {
    private final Value<URL> url;

    public RemoveUrl(Text bucket, Text id) {
        url = new TextAsUrl(new Format(new SimpleText("https://%s.s3.amazonaws.com/%s"), bucket, id));
    }

    public URL get() {
        return url.get();
    }
}
