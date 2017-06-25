package Amazon.Utilities;

import Amazon.OO.Value;
import Amazon.SharedValues.Algorithm;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class HmacSigned implements Value<byte[]> {
    private final Value<String> data;
    private final Value<byte[]> key;

    public HmacSigned(Value<String> data, Value<byte[]> key) {
        this.data = data;
        this.key = key;
    }

    public byte[] get() {
        try {
            String algorithm = new Algorithm().get().replace("-", "");
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.get(), algorithm));
            return mac.doFinal(data.get().getBytes("UTF-8"));
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
