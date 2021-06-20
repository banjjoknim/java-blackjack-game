package domain.user.state;

import domain.card.Card;

import java.util.List;

public class Blackjack extends Ended {

    public Blackjack(List<Card> cards) {
        super(cards);
    }

    @Override
    protected boolean isBlackjack() {
        return true;
    }

    @Override
    protected boolean isBust() {
        return false;
    }

    @Override
    protected boolean isStay() {
        return false;
    }

}
