package Amazon.OO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ConcattedText extends Text {
    private final List<Value<String>> texts;

    public ConcattedText(Value<String>... texts) {
        this.texts = Arrays.asList(texts);
    }

    public String get() {
        return String.join("", texts.stream().map(x -> x.get()).collect(Collectors.toList()));
    }
}
