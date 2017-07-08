package Amazon.OO.OOBytes;

import Amazon.OO.OOText.Text;

public final class TextAsBytes extends Bytes {
    private final Text text;

    public TextAsBytes(Text text) {
        this.text = text;
    }

    public byte[] get() {
        return text.get().getBytes();
    }
}
