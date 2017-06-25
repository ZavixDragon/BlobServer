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
            return digest.digest(data.get().getBytes(StandardCharsets.UTF_8));
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
