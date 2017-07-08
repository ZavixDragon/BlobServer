package Amazon.SmartValues;

import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;
import Amazon.Utilities.EncodeUrl;

import java.net.URL;

public final class CanonicalPath extends Text {
    private final Value<URL> url;

    public CanonicalPath(Value<URL> url) {
        this.url = url;
    }

    public String get() {
        String encodedPath = new EncodeUrl(new SimpleText(url.get().getPath())).get();
        return encodedPath.startsWith("/") ? encodedPath : "/" + encodedPath;
    }
}
