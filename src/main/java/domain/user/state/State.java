package domain.user.state;

import domain.card.Card;
import domain.card.Cards;

public abstract class State {

    protected Cards cards;

    public State(Cards cards) {
        this.cards = cards;
    }

    public abstract void draw(Card card);

    public abstract State determineState();

    public State createStay() {
        return new Stay(cards);
    }

    public int getTotalCardNumber() {
        return cards.calculateTotalCardNumber();
    }

    public Cards getCards() {
        return cards;
    }
}
