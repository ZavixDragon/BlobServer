package Amazon.OO.OOBytes;

import Amazon.OO.OOText.Text;
import Amazon.OO.Value;

import java.util.Locale;

public final class BytesAsHex extends Text {
    private final Bytes bytes;

    public BytesAsHex(Bytes bytes) {
        this.bytes = bytes;
    }

    public String get() {
        byte[] data = bytes.get();
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (int i = 0; i < data.length; i++) {
            String hex = Integer.toHexString(data[i]);
            if (hex.length() == 1) {
                sb.append("0");
            } else if (hex.length() == 8) {
                hex = hex.substring(6);
            }
            sb.append(hex);
        }
        return sb.toString().toLowerCase(Locale.getDefault());
    }
}
