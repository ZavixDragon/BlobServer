package Amazon.OO.OOBytes;

import Amazon.OO.OOText.Text;

public final class SimpleBytes extends Bytes {
    private byte[] bytes;

    public SimpleBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] get() {
        return bytes;
    }

    private static byte[] getBytes(Text data) {
        try {
            return data.get().getBytes("UTF-8");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
