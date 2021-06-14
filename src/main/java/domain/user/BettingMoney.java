package domain.user;

import domain.result.GameResult;

import java.math.BigDecimal;
import java.util.Objects;

public class BettingMoney {
    private static final int POSITIVE = 1;

    private BigDecimal amount;

    public BettingMoney(BigDecimal amount) {
        validateBettingMoneyAmount(amount);
        this.amount = amount;
    }

    private void validateBettingMoneyAmount(BigDecimal amount) {
        boolean isPositiveAmount = amount.compareTo(BigDecimal.ZERO) == POSITIVE;
        if (!isPositiveAmount) {
            throw new IllegalArgumentException("배팅 금액은 0보다 커야 합니다.");
        }
    }

    public Profit calculateProfit(GameResult gameResult) {
        BigDecimal profitAmount = gameResult.multiplyDividendRate(amount);
        return new Profit(profitAmount);
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
