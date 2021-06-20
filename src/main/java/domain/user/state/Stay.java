package domain.user.state;

import domain.card.Card;

import java.util.List;

public class Stay extends Ended {

    public Stay(List<Card> cards) {
        super(cards);
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
