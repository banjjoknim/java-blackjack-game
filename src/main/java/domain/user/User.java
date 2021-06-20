package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.user.state.State;

public abstract class User {

    protected State state;

    public User(State state) {
        this.state = state;
    }

    public void hit(Deck deck) {
        deck.distributeCard(this);
    }

    public void draw(Card card) {
        state.add(card);
        state = state.findNextState();
    }

    public abstract boolean isWait();

    public abstract boolean isPlayer();

    public abstract boolean isDealer();

    public State getState() {
        return state;
    }
}
