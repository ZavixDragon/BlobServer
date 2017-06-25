package Amazon.SmartValues;

import Amazon.OO.FormattedString;
import Amazon.OO.StringText;
import Amazon.OO.Text;
import Amazon.OO.Value;
import Amazon.SharedValues.AmazonContentHeaderName;

import java.net.URL;
import java.util.Map;

public final class CanonicalRequest extends Text {
    private final Value<String> httpMethod;
    private final Value<URL> endpoint;
    private final Map<Value<String>, Value<String>> headers;

    public CanonicalRequest(Value<String> httpMethod, Value<URL> endpoint, Map<Value<String>, Value<String>> headers) {
        this.httpMethod = httpMethod;
        this.endpoint = endpoint;
        this.headers = headers;
    }

    public String get() {
        return new FormattedString(new StringText("%s\n%s\n\n%s\n%s\n%s"),
                httpMethod,
                new CanonicalPath(endpoint),
                new CanonicalHeaders(headers),
                new CanonicalHeaderNames(headers),
                headers.get(new AmazonContentHeaderName())).get();
    }
}
