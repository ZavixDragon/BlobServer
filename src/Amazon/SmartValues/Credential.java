package Amazon.SmartValues;

import Amazon.OO.FormattedText;
import Amazon.OO.StringText;
import Amazon.OO.Text;
import Amazon.ConfigValues.AccessKey;
import Amazon.OnlyOnePerRequest.DateStamp;
import Amazon.SmartValues.CredentialScope;

public final class Credential extends Text {
    private final DateStamp stamp;
    private final Text region;

    public Credential(DateStamp stamp, Text region) {
        this.stamp = stamp;
        this.region = region;
    }

    public String get() {
        return new FormattedText(new StringText("%s/%s"),
                new AccessKey(),
                new CredentialScope(stamp, region)).get();
    }
}
