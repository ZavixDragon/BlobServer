package Amazon.SmartValues;

import Amazon.OnlyOnePerRequest.DateStamp;
import Amazon.Utilities.BytesToHex;
import Amazon.OO.FormattedText;
import Amazon.OO.StringText;
import Amazon.OO.Text;
import Amazon.OO.Value;
import Amazon.SharedValues.Algorithm;
import Amazon.SharedValues.Scheme;

import java.net.URL;
import java.util.Map;

public final class AuthorizationHeader extends Text {
    private final Value<URL> endpoint;
    private final Value<String> httpMethod;
    private final Map<Value<String>, Value<String>> headers;
    private final DateStamp stamp;

    public AuthorizationHeader(Value<URL> endpoint, Value<String> httpMethod, Map<Value<String>, Value<String>> headers, DateStamp stamp) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
        this.headers = headers;
        this.stamp = stamp;
    }

    public String get() {
        return new FormattedText(new StringText("%s-%s Credential=%s, SignedHeaders=%s, Signature=%s"),
                new Scheme(),
                new Algorithm(),
                new Credential(stamp),
                new CanonicalHeaderNames(headers),
                new BytesToHex(new Signature(httpMethod, endpoint, headers, stamp))).get();
    }
}
