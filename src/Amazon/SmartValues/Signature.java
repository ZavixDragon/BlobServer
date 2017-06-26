package Amazon.SmartValues;

import Amazon.ConfigValues.SecretKey;
import Amazon.OO.*;
import Amazon.OnlyOnePerRequest.DateStamp;
import Amazon.SharedValues.Scheme;
import Amazon.SharedValues.ServiceName;
import Amazon.SharedValues.Termination;
import Amazon.Utilities.SignHmac;

import java.net.URL;
import java.util.Map;

public final class Signature implements Value<byte[]> {
    private final Value<String> httpMethod;
    private final Value<URL> endpoint;
    private final Map<Value<String>, Value<String>> headers;
    private final DateStamp stamp;
    private final Text region;

    public Signature(Value<String> httpMethod, Value<URL> endpoint, Map<Value<String>, Value<String>> headers, DateStamp stamp, Text region) {
        this.httpMethod = httpMethod;
        this.endpoint = endpoint;
        this.headers = headers;
        this.stamp = stamp;
        this.region = region;
    }

    public byte[] get() {
        return new SignHmac(
                new StringToSign(httpMethod, endpoint, headers, stamp, region),
                new SignHmac(
                        new Termination(),
                        new SignHmac(
                                new ServiceName(),
                                new SignHmac(
                                        region,
                                        new SignHmac(
                                                new StringText(stamp.getDateStamp()),
                                                new TextAsBytes(
                                                        new ConcattedText(
                                                                new Scheme(),
                                                                new SecretKey()))))))).get();
    }
}
