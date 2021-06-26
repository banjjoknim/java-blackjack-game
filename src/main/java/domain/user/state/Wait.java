package domain.user.state;

import domain.card.Card;
import domain.result.GameResult;
import domain.user.Score;

import java.util.List;

public class Wait extends State {

    public Wait(List<Card> cards) {
        super(cards);
    }

    @Override
    public GameResult findResult(State state) {
        throw new IllegalStateException("현재 차례를 마치지 않아, 결과를 찾을 수 없습니다.");
    }

    @Override
    protected boolean isBlackjack() {
        return false;
    }

    @Override
    protected boolean isBust() {
        return false;
    }

    @Override
    protected boolean isStay() {
        return false;
    }

    @Override
    public State toStay() {
        return new Stay(cards);
    }

    @Override
    public State findNextState() {
        Score score = this.calculateScore();
        if (score.isBiggerThan(BLACKJACK_SCORE)) {
            return new Bust(cards);
        }
        if (score.isSame(BLACKJACK_SCORE) && this.isInitialCards()) {
            return new Blackjack(cards);
        }
        if (score.isSame(BLACKJACK_SCORE)) {
            return new Stay(cards);
        }
        return new Wait(cards);
    }

    @Override
    public boolean isEnded() {
        return false;
    }
}
