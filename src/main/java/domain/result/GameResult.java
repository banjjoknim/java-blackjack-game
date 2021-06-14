package domain.result;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public enum GameResult {
    WIN(1, new BigDecimal("1")),
    DRAW(0, new BigDecimal("0")),
    LOSE(-1, new BigDecimal("-1"));

    private static final int SCALE = 0;

    private int value;
    private BigDecimal dividendRate;

    GameResult(int value, BigDecimal dividendRate) {
        this.value = value;
        this.dividendRate = dividendRate;
    }

    public static GameResult determineGameResult(int gameResultValue) {
        return Arrays.stream(GameResult.values())
                .filter(gameResult -> gameResult.value == gameResultValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임 결과입니다."));
    }

    public BigDecimal multiplyDividendRate(BigDecimal amount) {
        return dividendRate.multiply(amount)
                .setScale(SCALE, RoundingMode.HALF_EVEN);
    }

}
