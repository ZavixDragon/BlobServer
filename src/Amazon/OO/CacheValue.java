package Amazon.OO;

import java.util.function.Supplier;

public final class CacheValue<T> implements Value<T> {
    private final Supplier<T> getValue;
    private T value;

    public CacheValue(Value<T> value) {
        this((Supplier<T>)() -> value.get());
    }

    public CacheValue(Supplier<T> getValue) {
        this.getValue = getValue;
    }

    public T get() {
        if (value == null)
            value = getValue.get();
        return value;
    }
}
