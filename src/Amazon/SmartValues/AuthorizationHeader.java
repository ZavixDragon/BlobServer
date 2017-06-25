package Amazon.SmartValues;

import Amazon.Utilities.BytesToHex;
import Amazon.SharedValues.Credential;
import Amazon.OO.FormattedString;
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

    public AuthorizationHeader(Value<URL> endpoint, Value<String> httpMethod, Map<Value<String>, Value<String>> headers) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
        this.headers = headers;
    }

    public String get() {
        return new FormattedString(new StringText("%s-%s Credential=%s,SignedHeaders=%s,Signature=%s"),
                new Scheme(),
                new Algorithm(),
                new Credential(),
                new CanonicalHeaderNames(headers),
                new BytesToHex(new Signature(httpMethod, endpoint, headers))).get();
    }
}
