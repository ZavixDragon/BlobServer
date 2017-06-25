package Amazon;

import Amazon.OO.StringText;
import Amazon.OO.Value;
import Amazon.SharedValues.AmazonContentHeaderName;
import Amazon.SharedValues.TodayTimeStamp;
import Amazon.SmartValues.AuthorizationHeader;
import Amazon.SmartValues.Host;
import Amazon.Utilities.BytesToHex;
import Amazon.Utilities.Sha256;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class AmazonGetHeaders implements Value<Map<Value<String>, Value<String>>> {
    private final Value<URL> endpoint;

    public AmazonGetHeaders(Value<URL> endpoint) {
        this.endpoint = endpoint;
    }

    public Map<Value<String>, Value<String>> get() {
        Map<Value<String>, Value<String>> headers = new HashMap<>();
        headers.put(new AmazonContentHeaderName(), new BytesToHex(new Sha256(new StringText(""))));
        headers.put(new StringText("x-amz-date"), new TodayTimeStamp());
        headers.put(new StringText("Host"), new Host(endpoint));
        headers.put(new StringText("Authorization"), new AuthorizationHeader(endpoint, new StringText("GET"), headers));
        return headers;
    }
}
