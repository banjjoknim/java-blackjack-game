package domain.user.state;

import domain.card.Card;

import java.util.List;

public class Blackjack extends Ended {

    public Blackjack(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean isEnded() {
        return true;
    }
}
