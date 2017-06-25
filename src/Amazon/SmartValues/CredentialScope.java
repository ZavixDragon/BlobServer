package Amazon.SmartValues;

import Amazon.OO.FormattedText;
import Amazon.OO.StringText;
import Amazon.OO.Text;
import Amazon.OnlyOnePerRequest.DateStamp;
import Amazon.SharedValues.RegionName;
import Amazon.SharedValues.ServiceName;
import Amazon.SharedValues.Termination;

public final class CredentialScope extends Text {
    private final DateStamp stamp;

    public CredentialScope(DateStamp stamp) {
        this.stamp = stamp;
    }

    public String get() {
        return new FormattedText(new StringText("%s/%s/%s/%s"),
                new StringText(stamp.getDateStamp()),
                new RegionName(),
                new ServiceName(),
                new Termination()).get();
    }
}
