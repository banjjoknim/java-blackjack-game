package domain.result;

import domain.user.Status;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum GameResult {
    WIN(1, new BigDecimal("1")),
    WIN_WITH_BLACK_JACK(1, new BigDecimal("1.5")),
    DRAW(0, new BigDecimal("0")),
    DRAW_WITH_BLACK_JACK(0, new BigDecimal("0")),
    LOSE(-1, new BigDecimal("-1"));

    private static final int SCALE = 0;

    private int value;
    private BigDecimal dividendRate;

    GameResult(int value, BigDecimal dividendRate) {
        this.value = value;
        this.dividendRate = dividendRate;
    }

    public static GameResult determineWinOrDrawOrLose(int gameResultValue, Status playerStatus, Status dealerStatus) {
        if (isLose(gameResultValue, playerStatus, dealerStatus)) {
            return LOSE;
        }
        if (isWin(gameResultValue, playerStatus, dealerStatus)) {
            return determineWinOrWinWithBlackJack(playerStatus);
        }
        return determineDrawOrDrawWithBlackJack(playerStatus, dealerStatus);
    }

    private static boolean isLose(int gameResultValue, Status playerStatus, Status dealerStatus) {
        return (!dealerStatus.isBust() && gameResultValue == LOSE.value) || playerStatus.isBust() || (!playerStatus.isBlackJack() && dealerStatus.isBlackJack());
    }

    private static boolean isWin(int gameResultValue, Status playerStatus, Status dealerStatus) {
        return gameResultValue == WIN.value || dealerStatus.isBust() || (playerStatus.isBlackJack() && !dealerStatus.isBlackJack());
    }

    private static GameResult determineWinOrWinWithBlackJack(Status playerStatus) {
        if (playerStatus.isBlackJack()) {
            return WIN_WITH_BLACK_JACK;
        }
        return WIN;
    }

    private static GameResult determineDrawOrDrawWithBlackJack(Status playerStatus, Status dealerStatus) {
        if (playerStatus.isBlackJack() && dealerStatus.isBlackJack()) {
            return DRAW_WITH_BLACK_JACK;
        }
        return DRAW;
    }

    public BigDecimal multiplyDividendRate(BigDecimal amount) {
        return dividendRate.multiply(amount)
                .setScale(SCALE, RoundingMode.HALF_EVEN);
    }

}
