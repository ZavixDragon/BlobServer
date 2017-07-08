package Amazon.SmartValues;

import Amazon.OO.OOText.Format;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;

public final class Credential extends Text {
    private final Text value;

    public Credential(Text accessKey, Text credentialScope) {
        value = new Format(new SimpleText("%s/%s"),
                accessKey,
                credentialScope);
    }

    public String get() {
        return value.get();
    }
}
