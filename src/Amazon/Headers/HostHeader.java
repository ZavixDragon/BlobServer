package Amazon.Headers;

import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;
import Amazon.SmartValues.Host;

import java.net.URL;

public final class HostHeader extends Header {
    public HostHeader(Value<URL> url) {
        this(new Host(url));
    }

    public HostHeader(Text host) {
        super(new SimpleText("Host"), host);
    }
}
