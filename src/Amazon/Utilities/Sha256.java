package Amazon.Utilities;

import Amazon.OO.OOBytes.Bytes;
import Amazon.OO.Value;

import java.security.MessageDigest;

public final class Sha256 extends Bytes {
    private final Bytes data;

    public Sha256(Bytes data) {
        this.data = data;
    }

    public byte[] get() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(data.get());
            return digest.digest();
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
