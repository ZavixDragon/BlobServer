package Amazon.OO.OOText;

import Amazon.OO.OONumber.Number2;

public class WholeNumberAsText extends Text {
    private final Number2 value;

    public WholeNumberAsText(Number2 value) {
        this.value = value;
    }

    public String get() {
        return "" + value.get().intValue();
    }
}
