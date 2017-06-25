package Amazon.OO;

public final class TextAsBytes implements Value<byte[]> {
    private final Value<String> text;

    public TextAsBytes(Value<String> text) {
        this.text = text;
    }

    public byte[] get() {
        return text.get().getBytes();
    }
}
