package WebRequests;

import java.net.URL;

public class StringAsUrl {
    private final String url;

    public StringAsUrl(String url) {
        this.url = url;
    }

    public URL get() {
        try {
            return new URL(url);
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
