package domain.result;

import domain.user.Dealer;
import domain.user.Player;

import java.math.BigDecimal;
import java.util.Arrays;

public enum GameResult {
    WIN(1, new BigDecimal("1")),
    WIN_WITH_BLACK_JACK(1, new BigDecimal("1.5")),
    DRAW(0, new BigDecimal("0")),
    DRAW_WITH_BLACK_JACK(0, new BigDecimal("0")),
    LOSE(-1, new BigDecimal("-1"));

    private int value;
    private BigDecimal dividendRate;

    GameResult(int value, BigDecimal dividendRate) {
        this.value = value;
        this.dividendRate = dividendRate;
    }

    public static GameResult determineGameResult(int gameResultValue, Player player, Dealer dealer) {
        if (gameResultValue == LOSE.value || player.isBust() || (!player.isBlackJack() && dealer.isBlackJack())) {
            return LOSE;
        }
        if (gameResultValue == WIN.value || dealer.isBust() || (player.isBlackJack() && !dealer.isBlackJack())) {
            return determineWinOrWinWithBlackJack(player);
        }
        return determineDrawOrDrawWithBlackJack(player, dealer);
    }

    private static GameResult determineWinOrWinWithBlackJack(Player player) {
        if (player.isBlackJack()) {
            return WIN_WITH_BLACK_JACK;
        }
        return WIN;
    }

    private static GameResult determineDrawOrDrawWithBlackJack(Player player, Dealer dealer) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return DRAW_WITH_BLACK_JACK;
        }
        return DRAW;
    }

    public BigDecimal multiplyDividendRate(BigDecimal amount) {
        return dividendRate.multiply(amount);
    }

}
