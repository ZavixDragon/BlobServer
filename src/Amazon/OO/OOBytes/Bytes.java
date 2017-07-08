package Amazon.OO.OOBytes;

import Amazon.OO.Value;

public abstract class Bytes implements Value<byte[]> {
    @Override
    public boolean equals(Object o) {
        return get().equals(((Value<byte[]>)o).get());
    }

    @Override
    public int hashCode() {
        return get().hashCode();
    }
}
