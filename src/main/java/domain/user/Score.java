package domain.user;

import java.util.Objects;

public class Score implements Comparable<Score> {

    private int score;

    public Score(int score) {
        validateScore(score);
        this.score = score;
    }

    private void validateScore(int score) {
        boolean isNegative = score < 0;
        if (isNegative) {
            throw new IllegalArgumentException("점수는 0보다 작을 수 없습니다.");
        }
    }

    public boolean isBiggerThan(int score) {
        return this.score > score;
    }

    public boolean isSame(int score) {
        return this.score == score;
    }

    public boolean isSmallerThan(int dealerRuleScore) {
        return score < dealerRuleScore;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(score, o.score);
    }
}
