package Amazon.SmartValues;

import Amazon.OO.FormattedText;
import Amazon.OO.StringText;
import Amazon.OO.Text;
import Amazon.OnlyOnePerRequest.DateStamp;
import Amazon.SharedValues.ServiceName;
import Amazon.SharedValues.Termination;

public final class CredentialScope extends Text {
    private final DateStamp stamp;
    private final Text region;

    public CredentialScope(DateStamp stamp, Text region) {
        this.stamp = stamp;
        this.region = region;
    }

    public String get() {
        return new FormattedText(new StringText("%s/%s/%s/%s"),
                new StringText(stamp.getDateStamp()),
                region,
                new ServiceName(),
                new Termination()).get();
    }
}
