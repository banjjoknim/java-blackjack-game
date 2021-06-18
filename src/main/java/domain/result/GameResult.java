package domain.result;

import java.util.Arrays;

public enum GameResult {
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private int value;

    GameResult(int value) {
        this.value = value;
    }

    public static GameResult from(int value) {
        return Arrays.stream(GameResult.values())
                .filter(gameResult -> gameResult.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게임 결과가 존재하지 않습니다."));
    }
}
