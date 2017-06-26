package Amazon.Utilities;

import Amazon.OO.Text;
import Amazon.OO.Value;

import java.net.URLEncoder;

public final class EncodeUrl extends Text {
    private final Value<String> url;

    public EncodeUrl(Value<String> url) {
        this.url = url;
    }

    public String get() {
        try {
            return URLEncoder.encode(url.get(), "UTF-8").replace("%2F", "/");
        } catch (Exception ex) {
            throw new RuntimeException("UTF-8 encoding is not supported.", ex);
        }
    }
}
