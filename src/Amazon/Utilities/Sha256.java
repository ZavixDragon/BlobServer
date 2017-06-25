package Amazon.Utilities;

import Amazon.OO.Value;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public final class Sha256 implements Value<byte[]> {
    private final Value<String> data;

    public Sha256(Value<String> data) {
        this.data = data;
    }

    public byte[] get() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(data.get().getBytes("UTF-8"));
            return digest.digest();
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
