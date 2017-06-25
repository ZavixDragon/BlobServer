package Amazon.OO;

public class IntText extends Text {
    private final int value;

    public IntText(int value) {
        this.value = value;
    }

    public String get() {
        return "" + value;
    }
}
