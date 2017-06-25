package Amazon.SmartValues;

import Amazon.SharedValues.CredentialScope;
import Amazon.OO.FormattedString;
import Amazon.OO.StringText;
import Amazon.OO.Text;
import Amazon.OO.Value;
import Amazon.SharedValues.Algorithm;
import Amazon.SharedValues.Scheme;
import Amazon.SharedValues.TodayTimeStamp;
import Amazon.Utilities.BytesToHex;
import Amazon.Utilities.Sha256;

import java.net.URL;
import java.util.Map;

public final class StringToSign extends Text {
    private final Value<String> httpMethod;
    private final Value<URL> endpoint;
    private final Map<Value<String>, Value<String>> headers;

    public StringToSign(Value<String> httpMethod, Value<URL> endpoint, Map<Value<String>, Value<String>> headers) {
        this.httpMethod = httpMethod;
        this.endpoint = endpoint;
        this.headers = headers;
    }

    public String get() {
        return new FormattedString(new StringText("%s-%s\n%s\n%s\n%s"),
                new Scheme(),
                new Algorithm(),
                new TodayTimeStamp(),
                new CredentialScope(),
                new BytesToHex(new Sha256(new CanonicalRequest(httpMethod, endpoint, headers)))).get();
    }
}
