package domain.user;

import java.math.BigDecimal;
import java.util.Objects;

public class Profit {
    public static final Profit ZERO = new Profit(new BigDecimal("0"));

    private BigDecimal amount;

    public Profit(BigDecimal amount) {
        this.amount = amount;
    }

    public Profit addProfit(Profit profit) {
        return new Profit(amount.add(profit.amount));
    }

    public Profit negate() {
        return new Profit(amount.negate());
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profit profit = (Profit) o;
        return Objects.equals(amount, profit.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

}
