package domain.user;

import domain.card.Card;

public abstract class User {

    protected Hand hand = new Hand();

    public void draw(Card card) {
        hand.addCard(card);
        hand.changeState();
    }

    public abstract boolean isWait();

    public Hand getHand() {
        return hand;
    }
}
