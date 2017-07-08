package Amazon.SmartValues;

import Amazon.OO.OOText.Format;
import Amazon.OO.OOText.SimpleText;
import Amazon.OO.OOText.Text;
import Amazon.SimpleValues.DateStamp;

public final class CredentialScope extends Text {
    private final Text value;

    public CredentialScope(DateStamp stamp, Text region, Text serviceName, Text termination) {
        value = new Format(new SimpleText("%s/%s/%s/%s"),
                    new SimpleText(stamp.getDateStamp()),
                    region,
                    serviceName,
                    termination);
    }

    public String get() {
        return value.get();
    }
}
