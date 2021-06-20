package domain.result;

import java.util.Arrays;

public enum Result {
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private int value;

    Result(int value) {
        this.value = value;
    }

    public static Result from(int value) {
        return Arrays.stream(Result.values())
                .filter(result -> result.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게임 결과가 존재하지 않습니다."));
    }
}
