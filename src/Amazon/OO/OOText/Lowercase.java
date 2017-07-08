package Amazon.OO.OOText;

import Amazon.OO.Value;

public final class Lowercase extends Text {
    private final Value<String> text;

    public Lowercase(Value<String> text) {
        this.text = text;
    }

    public String get() {
        return text.get().toLowerCase();
    }
}
