package Amazon.OO.OOText;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Concat extends Text {
    private final List<Text> texts;

    public Concat(Text... texts) {
        this.texts = Arrays.asList(texts);
    }

    public String get() {
        return String.join("", texts.stream().map(x -> x.get()).collect(Collectors.toList()));
    }
}
