package Amazon.SharedValues;

import Amazon.OO.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public final class TodayDateStamp extends Text {
    public String get() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMdd");
        dateTimeFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
        return dateTimeFormat.format(new Date());
    }
}
