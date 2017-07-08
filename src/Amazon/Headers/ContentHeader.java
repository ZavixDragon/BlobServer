package Amazon.Headers;

import Amazon.OO.OOBytes.Bytes;
import Amazon.OO.OOBytes.BytesAsHex;
import Amazon.OO.OOBytes.TextAsBytes;
import Amazon.OO.OOText.Lowercase;
import Amazon.OO.OOText.SimpleText;
import Amazon.Utilities.Sha256;

public final class ContentHeader extends Header {
    public ContentHeader() {
        this(new TextAsBytes(new SimpleText("")));
    }

    public ContentHeader(Bytes content) {
        super(new SimpleText("x-amz-content-sha256"), new Lowercase(new BytesAsHex(new Sha256(content))));
    }
}
