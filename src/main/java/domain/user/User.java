package domain.user;

import domain.card.Card;
import domain.card.Deck;

public abstract class User {

    protected Hand hand = new Hand();

    public void hit(Deck deck) {
        deck.distributeCard(this);
    }

    public void draw(Card card) {
        hand.addCard(card);
        hand.update();
    }

    public abstract boolean isWait();

    public abstract boolean isPlayer();

    public abstract boolean isDealer();

    public Hand getHand() {
        return hand;
    }
}
