package Amazon.SmartValues;

import Amazon.OO.StringText;
import Amazon.OO.Text;
import Amazon.OO.Value;
import Amazon.Utilities.UrlEncoded;

import java.net.URL;

public final class CanonicalPath extends Text {
    private final Value<URL> url;

    public CanonicalPath(Value<URL> url) {
        this.url = url;
    }

    public String get() {
        String encodedPath = new UrlEncoded(new StringText(url.get().getPath())).get();
        return encodedPath.startsWith("/") ? encodedPath : "/" + encodedPath;
    }
}
