package Amazon.Headers;

import Amazon.OO.OOBytes.Bytes;
import Amazon.OO.OOBytes.BytesAsHex;
import Amazon.OO.OOText.Format;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;

public final class AuthorizationHeader extends Header {
    public AuthorizationHeader(Text scheme, Text algorithm, Text credential, Text canonicalHeaderNames, Bytes signature) {
        super(new SimpleText("Authorization"),
                new Format(new SimpleText("%s-%s Credential=%s, SignedHeaders=%s, Signature=%s"),
                    scheme, algorithm, credential, canonicalHeaderNames, new BytesAsHex(signature)));
    }
}
