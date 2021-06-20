package domain.user.state;

import domain.card.Card;
import domain.result.GameResult;

import java.util.List;

public class Bust extends Ended {

    public Bust(List<Card> cards) {
        super(cards);
    }

    @Override
    protected GameResult determineResult(State state) {
        return GameResult.LOSE;
    }

    @Override
    protected boolean isBlackjack() {
        return false;
    }

    @Override
    protected boolean isBust() {
        return true;
    }

    @Override
    protected boolean isStay() {
        return false;
    }

}
