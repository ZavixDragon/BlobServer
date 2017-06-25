package Amazon.SmartValues;

import Amazon.OO.Text;
import Amazon.OO.Value;

import java.net.URL;

public class CanonicalQueryParameters extends Text {
    private final Value<URL> endpoint;

    public CanonicalQueryParameters(Value<URL> endpoint) {
        this.endpoint = endpoint;
    }

    public String get() {
        String query = endpoint.get().getQuery();
        return query == null ? "" : query;
    }
}
