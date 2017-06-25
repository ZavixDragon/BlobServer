package Amazon.SmartValues;

import Amazon.ConfigValues.SecretKey;
import Amazon.OO.ConcattedText;
import Amazon.OO.TextAsBytes;
import Amazon.OO.Value;
import Amazon.SharedValues.RegionName;
import Amazon.SharedValues.Scheme;
import Amazon.SharedValues.ServiceName;
import Amazon.SharedValues.Termination;
import Amazon.SharedValues.TodayDateStamp;
import Amazon.Utilities.HmacSigned;

import java.net.URL;
import java.util.Map;

public final class Signature implements Value<byte[]> {
    private final Value<String> httpMethod;
    private final Value<URL> endpoint;
    private final Map<Value<String>, Value<String>> headers;

    public Signature(Value<String> httpMethod, Value<URL> endpoint, Map<Value<String>, Value<String>> headers) {
        this.httpMethod = httpMethod;
        this.endpoint = endpoint;
        this.headers = headers;
    }

    public byte[] get() {
        return new HmacSigned(
                new StringToSign(httpMethod, endpoint, headers),
                new HmacSigned(
                        new Termination(),
                        new HmacSigned(
                                new ServiceName(),
                                new HmacSigned(
                                        new RegionName(),
                                        new HmacSigned(
                                                new TodayDateStamp(),
                                                new TextAsBytes(
                                                        new ConcattedText(
                                                                new Scheme(),
                                                                new SecretKey()))))))).get();
    }
}
