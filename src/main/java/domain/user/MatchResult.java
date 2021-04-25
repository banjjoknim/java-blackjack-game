package domain.user;

import java.util.Arrays;

public enum MatchResult {
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private int value;

    MatchResult(int value) {
        this.value = value;
    }

    public static MatchResult getMatchResult(int matchResultValue) {
        return Arrays.stream(MatchResult.values())
                .filter(matchResult -> matchResult.value == matchResultValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게임 결과는 승, 무, 패 중에서 하나여야 합니다."));
    }

    public int getValue() {
        return value;
    }

}
