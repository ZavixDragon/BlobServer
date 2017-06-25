package Amazon;

import Amazon.OO.IntText;
import Amazon.OO.Lowercase;
import Amazon.OO.StringText;
import Amazon.OO.Value;
import Amazon.OnlyOnePerRequest.DateStamp;
import Amazon.SharedValues.AmazonContentHeaderName;
import Amazon.SmartValues.AuthorizationHeader;
import Amazon.SmartValues.Host;
import Amazon.Utilities.BytesToHex;
import Amazon.Utilities.Sha256;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AmazonPutHeaderBuilder {
    private final Value<URL> endpoint;
    private final Value<String> content;
    private final DateStamp stamp;

    public AmazonPutHeaderBuilder(Value<URL> endpoint, Value<String> content, DateStamp stamp) {
        this.endpoint = endpoint;
        this.content = content;
        this.stamp = stamp;
    }

    public Map<Value<String>, Value<String>> get() {
        Map<Value<String>, Value<String>> headers = new HashMap<>();
        headers.put(new AmazonContentHeaderName(), new Lowercase(new BytesToHex(new Sha256(content))));
        headers.put(new StringText("content-length"), new IntText(content.get().length()));
        headers.put(new StringText("x-amz-date"), new StringText(stamp.getDateTimeStamp()));
        headers.put(new StringText("Host"), new Host(endpoint));
        headers.put(new StringText("Authorization"), new AuthorizationHeader(endpoint, new StringText("PUT"), headers, stamp));
        return headers;
    }
}
