package Amazon.Utilities;

import Amazon.OO.Value;
import WebRequests.StringAsUrl;

import java.net.URL;

public final class UrlFromString implements Value<URL> {
    private Value<String> url;

    public UrlFromString(Value<String> url) {
        this.url = url;
    }

    public URL get() {
        return new StringAsUrl(url.get()).get();
    }
}
