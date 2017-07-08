package Amazon.OO.OONumber;

import java.math.BigDecimal;

public final class SimpleNumber extends Number2 {
    private final BigDecimal value;

    public SimpleNumber(int value) {
        this(new BigDecimal(value));
    }

    public SimpleNumber(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal get() {
        return value;
    }
}
