package Amazon.SmartValues;

import Amazon.OnlyOnePerRequest.DateStamp;
import Amazon.Utilities.BytesAsHex;
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
    private final Text region;

    public AuthorizationHeader(Value<URL> endpoint, Value<String> httpMethod, Map<Value<String>, Value<String>> headers, DateStamp stamp, Text region) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
        this.headers = headers;
        this.stamp = stamp;
        this.region = region;
    }

    public String get() {
        return new FormattedText(new StringText("%s-%s Credential=%s, SignedHeaders=%s, Signature=%s"),
                new Scheme(),
                new Algorithm(),
                new Credential(stamp, region),
                new CanonicalHeaderNames(headers),
                new BytesAsHex(new Signature(httpMethod, endpoint, headers, stamp, region))).get();
    }
}
