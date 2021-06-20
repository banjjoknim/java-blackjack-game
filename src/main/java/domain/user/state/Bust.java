package domain.user.state;

import domain.card.Card;

import java.util.List;

public class Bust extends Ended {

    public Bust(List<Card> cards) {
        super(cards);
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
