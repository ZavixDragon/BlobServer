package Amazon.OO.OOText;

import java.util.function.Supplier;

public final class SimpleText extends Text {
    private final Supplier<String> text;

    public SimpleText(String text) {
        this(() -> text);
    }

    public SimpleText(Supplier<String> text) {
        this.text = text;
    }

    public String get() {
        return text.get();
    }
}
