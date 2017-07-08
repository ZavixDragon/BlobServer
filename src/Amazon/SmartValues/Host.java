package Amazon.SmartValues;

import Amazon.OO.OOText.Text;
import Amazon.OO.Value;

import java.net.URL;

public final class Host extends Text {
    private final Value<URL> endpoint;

    public Host(Value<URL> endpoint) {
        this.endpoint = endpoint;
    }

    public String get() {
        URL currentEndpoint = endpoint.get();
        return currentEndpoint.getPort() > -1
                ? currentEndpoint.getHost() + ":" + Integer.toString(currentEndpoint.getPort())
                : currentEndpoint.getHost();
    }
}
