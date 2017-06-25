package Amazon.SmartValues;

import Amazon.ConfigValues.SecretKey;
import Amazon.OO.ConcattedText;
import Amazon.OO.StringText;
import Amazon.OO.TextAsBytes;
import Amazon.OO.Value;
import Amazon.OnlyOnePerRequest.DateStamp;
import Amazon.SharedValues.RegionName;
import Amazon.SharedValues.Scheme;
import Amazon.SharedValues.ServiceName;
import Amazon.SharedValues.Termination;
import Amazon.Utilities.HmacSigned;

import java.net.URL;
import java.util.Map;

public final class Signature implements Value<byte[]> {
    private final Value<String> httpMethod;
    private final Value<URL> endpoint;
    private final Map<Value<String>, Value<String>> headers;
    private final DateStamp stamp;

    public Signature(Value<String> httpMethod, Value<URL> endpoint, Map<Value<String>, Value<String>> headers, DateStamp stamp) {
        this.httpMethod = httpMethod;
        this.endpoint = endpoint;
        this.headers = headers;
        this.stamp = stamp;
    }

    public byte[] get() {
        return new HmacSigned(
                new StringToSign(httpMethod, endpoint, headers, stamp),
                new HmacSigned(
                        new Termination(),
                        new HmacSigned(
                                new ServiceName(),
                                new HmacSigned(
                                        new RegionName(),
                                        new HmacSigned(
                                                new StringText(stamp.getDateStamp()),
                                                new TextAsBytes(
                                                        new ConcattedText(
                                                                new Scheme(),
                                                                new SecretKey()))))))).get();
    }
}
