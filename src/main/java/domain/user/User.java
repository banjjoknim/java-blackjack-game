package domain.user;

import domain.card.Card;

public abstract class User {

    private Hand hand = new Hand();

    public void draw(Card card) {
        hand.addCard(card);
    }

    public boolean isWaiting() {
        return !hand.hasEnded();
    }

    public Hand getHand() {
        return hand;
    }
}
