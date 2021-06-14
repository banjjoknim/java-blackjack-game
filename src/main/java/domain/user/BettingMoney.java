package domain.user;

import java.math.BigDecimal;
import java.util.Objects;

public class BettingMoney {
    private static final int POSITIVE = -1;

    private BigDecimal amount;

    public BettingMoney(BigDecimal amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(BigDecimal amount) {
        if (BigDecimal.ZERO.compareTo(amount) != POSITIVE) {
            throw new IllegalArgumentException("배팅 금액은 0보다 커야합니다.");
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BettingMoney that = (BettingMoney) o;
        return Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
