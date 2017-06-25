package Amazon.SmartValues;

import Amazon.OnlyOnePerRequest.DateStamp;
import Amazon.OO.FormattedText;
import Amazon.OO.StringText;
import Amazon.OO.Text;
import Amazon.OO.Value;
import Amazon.SharedValues.Algorithm;
import Amazon.SharedValues.Scheme;
import Amazon.Utilities.BytesToHex;
import Amazon.Utilities.Sha256;

import java.net.URL;
import java.util.Map;

public final class StringToSign extends Text {
    private final Value<String> httpMethod;
    private final Value<URL> endpoint;
    private final Map<Value<String>, Value<String>> headers;
    private final DateStamp stamp;

    public StringToSign(Value<String> httpMethod, Value<URL> endpoint, Map<Value<String>, Value<String>> headers, DateStamp stamp) {
        this.httpMethod = httpMethod;
        this.endpoint = endpoint;
        this.headers = headers;
        this.stamp = stamp;
    }

    public String get() {
        return new FormattedText(new StringText("%s-%s\n%s\n%s\n%s"),
                new Scheme(),
                new Algorithm(),
                new StringText(stamp.getDateTimeStamp()),
                new CredentialScope(stamp),
                new BytesToHex(new Sha256(new CanonicalRequest(httpMethod, endpoint, headers)))).get();
    }
}
