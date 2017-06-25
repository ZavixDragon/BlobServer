package Amazon.OnlyOnePerRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class DateStamp {
    private String dateStamp;
    private String dateTimeStamp;

    public DateStamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
        dateStamp = dateFormat.format(date);
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        dateTimeFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
        dateTimeStamp = dateTimeFormat.format(date);
    }

    public String getDateStamp() {
        return dateStamp;
    }

    public String getDateTimeStamp() {
        return dateTimeStamp;
    }
}
