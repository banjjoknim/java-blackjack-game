package domain.result;

import java.math.BigDecimal;
import java.util.Arrays;

public enum GameResult {
    WIN(1, false, new BigDecimal("1")),
    WIN_WITH_BLACK_JACK(1, true, new BigDecimal("1.5")),
    DRAW(0, false, new BigDecimal("0")),
    DRAW_WITH_BLACK_JACK(0, true, new BigDecimal("0")),
    LOSE(-1, false, new BigDecimal("-1"));

    private int value;
    private boolean isBlackJack;
    private BigDecimal dividendRate;

    GameResult(int value, boolean isBlackJack, BigDecimal dividendRate) {
        this.value = value;
        this.isBlackJack = isBlackJack;
        this.dividendRate = dividendRate;
    }

    public static GameResult determineGameResult(int gameResultValue, boolean isBlackJack) {
        return Arrays.stream(GameResult.values())
                .filter(gameResult -> gameResult.value == gameResultValue && gameResult.isBlackJack == isBlackJack)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게임 결과는 승, 무, 패 중에서 하나여야 합니다."));
    }

    public BigDecimal multiplyDividendRate(BigDecimal amount) {
        return dividendRate.multiply(amount);
    }

}
