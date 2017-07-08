package Amazon.SmartValues;

import Amazon.Headers.Header;
import Amazon.OO.OOText.Format;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;

public final class CanonicalRequest extends Text {
    private final Text value;

    public CanonicalRequest(Text httpMethod, Text canonicalPath, Text canonicalQueryParameters, Text canonicalHeaders, Text canonicalHeaderNames, Header contentHeader) {
        value = new Format(new SimpleText("%s\n%s\n%s\n%s\n%s\n%s"),
                httpMethod,
                canonicalPath,
                canonicalQueryParameters,
                canonicalHeaders,
                canonicalHeaderNames,
                new SimpleText(contentHeader.getValue()));
    }

    public String get() {
        String result = value.get();
        return result;
    }
}
