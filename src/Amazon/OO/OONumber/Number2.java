package Amazon.OO.OONumber;

import Amazon.OO.Value;

import java.math.BigDecimal;

public abstract class Number2 implements Value<BigDecimal> {
    public boolean equals(Object o) {
        return get().compareTo(((Value<BigDecimal>)o).get()) == 0;
    }

    public int hashCode() {
        return get().hashCode();
    }
}
