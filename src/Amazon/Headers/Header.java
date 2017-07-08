package Amazon.Headers;

import Amazon.OO.OOText.Text;

public abstract class Header {
    private final Text key;
    private final Text value;

    public Header(Text key, Text value) {
        this.key = key;
        this.value = value;
    }

    public final String getKey() {
        return key.get();
    }

    public final String getValue() {
        return value.get();
    }
}
