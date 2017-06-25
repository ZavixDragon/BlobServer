package Amazon.OO;

import java.util.Arrays;
import java.util.List;

public final class FormattedText extends Text {
    private final Value<String> text;
    private final List<Value<String>> insertValues;

    public FormattedText(Value<String> text, Value<String>... insertValues) {
        this.text = text;
        this.insertValues = Arrays.asList(insertValues);
    }

    public String get() {
        return String.format(text.get(), insertValues.stream().map(x -> x.get()).toArray());
    }
}
