package Amazon.Headers;

import Amazon.OO.OOText.SimpleText;
import Amazon.SimpleValues.DateStamp;

public final class DateHeader extends Header  {
    public DateHeader(DateStamp stamp){
        super(new SimpleText("x-amz-date"), new SimpleText(stamp.getDateTimeStamp()));
    }
}
