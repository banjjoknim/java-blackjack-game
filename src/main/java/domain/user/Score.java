package domain.user;

import java.util.Objects;

public class Score {

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
}
