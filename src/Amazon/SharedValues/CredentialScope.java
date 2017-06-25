package Amazon.SharedValues;

import Amazon.OO.FormattedString;
import Amazon.OO.StringText;
import Amazon.OO.Text;

public final class CredentialScope extends Text {
    public String get() {
        return new FormattedString(new StringText("%s/%s/%s/%s"),
                new TodayDateStamp(),
                new RegionName(),
                new ServiceName(),
                new Termination()).get();
    }
}
