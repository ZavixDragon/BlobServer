package Amazon.OO.OOText;

import java.util.Arrays;
import java.util.List;

public final class Format extends Text {
    private final Text text;
    private final List<Text> insertValues;

    public Format(Text text, Text... insertValues) {
        this.text = text;
        this.insertValues = Arrays.asList(insertValues);
    }

    public String get() {
        return String.format(text.get(), insertValues.stream().map(x -> x.get()).toArray());
    }
}
