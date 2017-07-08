package Amazon.OO.OOText;

import java.util.function.Supplier;

public final class CacheText extends Text {
    private final Supplier<String> getValue;
    private String value;

    public CacheText(Text value) {
        this(() -> value.get());
    }

    public CacheText(Supplier<String> getValue) {
        this.getValue = getValue;
    }

    public String get() {
        if (value == null)
            value = getValue.get();
        return value;
    }
}
