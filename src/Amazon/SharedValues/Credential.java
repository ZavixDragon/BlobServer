package Amazon.SharedValues;

import Amazon.OO.FormattedString;
import Amazon.OO.StringText;
import Amazon.OO.Text;
import Amazon.ConfigValues.AccessKey;

public final class Credential extends Text {
    public String get() {
        return new FormattedString(new StringText("%s/%s"),
                new AccessKey(),
                new CredentialScope()).get();
    }
}
