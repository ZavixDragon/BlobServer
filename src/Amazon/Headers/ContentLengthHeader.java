package Amazon.Headers;

import Amazon.OO.OOBytes.Bytes;
import Amazon.OO.OONumber.Number2;
import Amazon.OO.OONumber.SimpleNumber;
import Amazon.OO.OOText.WholeNumberAsText;
import Amazon.OO.OOText.SimpleText;

public final class ContentLengthHeader extends Header {
    public ContentLengthHeader(Bytes content) {
        this(new SimpleNumber(content.get().length));
    }

    public ContentLengthHeader(Number2 length) {
        super(new SimpleText("content-length"), new WholeNumberAsText(length));
    }
}
