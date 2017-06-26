package Amazon;

import Amazon.OO.Lowercase;
import Amazon.OO.StringText;
import Amazon.OO.Text;
import Amazon.OO.Value;
import Amazon.OnlyOnePerRequest.DateStamp;
import Amazon.SharedValues.AmazonContentHeaderName;
import Amazon.SmartValues.AuthorizationHeader;
import Amazon.SmartValues.Host;
import Amazon.Utilities.BytesAsHex;
import Amazon.Utilities.Sha256;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class AmazonGetHeaderBuilder implements Value<Map<Value<String>, Value<String>>> {
    private final Value<URL> endpoint;
    private final DateStamp stamp;
    private final Text region;

    public AmazonGetHeaderBuilder(Value<URL> endpoint, DateStamp stamp, Text region) {
        this.endpoint = endpoint;
        this.stamp = stamp;
        this.region = region;
    }

    public Map<Value<String>, Value<String>> get() {
        Map<Value<String>, Value<String>> headers = new HashMap<>();
        headers.put(new AmazonContentHeaderName(), new Lowercase(new BytesAsHex(new Sha256(new StringText("")))));
        headers.put(new StringText("x-amz-date"), new StringText(stamp.getDateTimeStamp()));
        headers.put(new StringText("Host"), new Host(endpoint));
        headers.put(new StringText("Authorization"), new AuthorizationHeader(endpoint, new StringText("GET"), headers, stamp, region));
        return headers;
    }
}
