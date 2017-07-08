package Amazon.SmartValues;

import Amazon.OO.OOBytes.TextAsBytes;
import Amazon.OO.OOText.Format;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;
import Amazon.SimpleValues.DateStamp;
import Amazon.OO.OOBytes.BytesAsHex;
import Amazon.Utilities.Sha256;

public final class TextToSign extends Text {
    private final Text value;

    public TextToSign(Text scheme, Text algorithm, DateStamp stamp, Text credentialScope, Text canonicalRequest) {
        value = new Format(new SimpleText("%s-%s\n%s\n%s\n%s"),
                    scheme,
                    algorithm,
                    new SimpleText(stamp.getDateTimeStamp()),
                    credentialScope,
                    new BytesAsHex(new Sha256(new TextAsBytes(canonicalRequest))));
    }

    public String get() {
        String result = value.get();
        return result;
    }
}
