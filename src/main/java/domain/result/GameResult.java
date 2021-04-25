package domain.result;

import java.util.Arrays;

public enum GameResult {
    WIN(1, false, 2),
    WIN_WITH_BLACK_JACK(1, true, 2.5),
    DRAW(0, false, 1),
    DRAW_WITH_BLACK_JACK(0, true, 1),
    LOSE(-1, false, -1);

    private int value;
    private boolean isBlackJack;
    private double dividendRate;

    GameResult(int value, boolean isBlackJack, double dividendRate) {
        this.value = value;
        this.isBlackJack = isBlackJack;
        this.dividendRate = dividendRate;
    }

    public static GameResult getGameResult(int gameResultValue, boolean isBlackJack) {
        return Arrays.stream(GameResult.values())
                .filter(gameResult -> gameResult.value == gameResultValue && gameResult.isBlackJack == isBlackJack)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게임 결과는 승, 무, 패 중에서 하나여야 합니다."));
    }

    public int getValue() {
        return value;
    }

    public boolean isBlackJack() {
        return isBlackJack;
    }

    public double getDividendRate() {
        return dividendRate;
    }

}
