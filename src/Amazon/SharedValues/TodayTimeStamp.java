package Amazon.SharedValues;

import Amazon.OO.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public final class TodayTimeStamp extends Text {
    public String get() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        dateTimeFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
        return dateTimeFormat.format(new Date());
    }
}
