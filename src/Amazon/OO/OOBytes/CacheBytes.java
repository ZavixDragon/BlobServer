package Amazon.OO.OOBytes;

import java.util.function.Supplier;

public final class CacheBytes extends Bytes {
    private final Supplier<byte[]> getValue;
    private byte[] value;

    public CacheBytes(Bytes value) {
        this(() -> value.get());
    }

    public CacheBytes(Supplier<byte[]> getValue) {
        this.getValue = getValue;
    }

    public byte[] get() {
        if (value == null)
            value = getValue.get();
        return value;
    }
}
