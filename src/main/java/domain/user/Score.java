package domain.user;

import java.util.Objects;

public class Score implements Comparable<Score> {
    private static final int ZERO = 0;

    private int value;

    public Score(int value) {
        validateScore(value);
        this.value = value;
    }

    private void validateScore(int value) {
        boolean isNegative = value < ZERO;
        if (isNegative) {
            throw new IllegalArgumentException("점수는 0보다 작을 수 없습니다.");
        }
    }

    public boolean isBiggerThan(int value) {
        return this.value > value;
    }

    public boolean isSame(int value) {
        return this.value == value;
    }

    public boolean isSmallerThan(int dealerRuleScoreValue) {
        return value < dealerRuleScoreValue;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return value == score1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(value, o.value);
    }
}
