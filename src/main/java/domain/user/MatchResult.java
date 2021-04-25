package domain.user;

public enum MatchResult {
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private int value;

    MatchResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
