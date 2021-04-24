package domain.user;

import java.math.BigDecimal;
import java.util.Objects;

public class BettingMoney {
    private static final int POSITIVE = 1;

    private BigDecimal bettingMoney;

    public BettingMoney(BigDecimal bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    private void validateBettingMoney(BigDecimal bettingMoney) {
        int bettingMoneyAmount = bettingMoney.compareTo(BigDecimal.ZERO);
        if (bettingMoneyAmount != POSITIVE) {
            throw new IllegalArgumentException("배팅 금액은 0보다 커야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BettingMoney that = (BettingMoney) o;
        return Objects.equals(bettingMoney, that.bettingMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bettingMoney);
    }

}