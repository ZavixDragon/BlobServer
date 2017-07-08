package Amazon.Utilities;

import Amazon.OO.OOBytes.Bytes;
import Amazon.OO.OOText.Text;
import Amazon.OO.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class SignHmac extends Bytes {
    private final Text algorithm;
    private final Text data;
    private final Bytes key;

    public SignHmac(Text algorithm, Text data, Bytes key) {
        this.algorithm = algorithm;
        this.data = data;
        this.key = key;
    }

    public byte[] get() {
        try {
            //String algorithm = algorithm new Algorithm().get().replace("-", "");
            Mac mac = Mac.getInstance(algorithm.get());
            mac.init(new SecretKeySpec(key.get(), algorithm.get()));
            return mac.doFinal(data.get().getBytes("UTF-8"));
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
