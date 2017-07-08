package Amazon.OO.OOText;

import Amazon.OO.Value;

public abstract class Text implements Value<String> {
    @Override
    public boolean equals(Object o) {
        return get().equals(((Value<String>)o).get());
    }

    @Override
    public int hashCode() {
        return get().hashCode();
    }
}