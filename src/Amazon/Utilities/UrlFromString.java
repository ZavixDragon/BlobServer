package Amazon.Utilities;

import Amazon.OO.Value;

import java.net.URL;

public final class UrlFromString implements Value<URL> {
    private Value<String> url;

    public UrlFromString(Value<String> url) {
        this.url = url;
    }

    public URL get() {
        try {
            return new URL(url.get());
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
