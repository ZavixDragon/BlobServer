package Amazon.Utilities;

import Amazon.OO.Text;
import Amazon.OO.Value;

import java.math.BigInteger;
import java.util.Locale;

public final class BytesToHex extends Text {
    private final Value<byte[]> bytes;

    public BytesToHex(Value<byte[]> bytes) {
        this.bytes = bytes;
    }

    public String get() {
        byte[] data = bytes.get();
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (int i = 0; i < data.length; i++) {
            String hex = Integer.toHexString(data[i]);
            if (hex.length() == 1) {
                // Append leading zero.
                sb.append("0");
            } else if (hex.length() == 8) {
                // Remove ff prefix from negative numbers.
                hex = hex.substring(6);
            }
            sb.append(hex);
        }
        return sb.toString().toLowerCase(Locale.getDefault());
    }
}
