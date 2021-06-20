package domain.user.state;

import domain.card.Card;
import domain.result.GameResult;

import java.util.List;

public class Stay extends Ended {

    public Stay(List<Card> cards) {
        super(cards);
    }

    @Override
    protected GameResult determineResult(State state) {
        if (state.isBust()) {
            return GameResult.WIN;
        }
        if (state.isBlackjack()) {
            return GameResult.LOSE;
        }
        return GameResult.from(Integer.compare(this.calculateScore(), state.calculateScore()));
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
        return true;
    }

}
