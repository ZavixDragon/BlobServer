package Amazon.Utilities;

import Amazon.OO.Text;
import Amazon.OO.Value;

import java.math.BigInteger;

public final class BytesToHex extends Text {
    private final Value<byte[]> bytes;

    public BytesToHex(Value<byte[]> bytes) {
        this.bytes = bytes;
    }

    public String get() {
        return String.format("%0" + (bytes.get().length << 1) + "X", new BigInteger(1, bytes.get()));
    }
}
