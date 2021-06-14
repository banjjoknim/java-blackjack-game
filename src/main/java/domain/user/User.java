package domain.user;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import domain.card.Type;
import domain.user.state.State;
import domain.user.state.Survival;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {

    private final UserName userName;
    protected State state;

    public User(UserName userName) {
        this.userName = userName;
        this.state = new Survival(new Cards());
    }

    public void hit(Deck deck) {
        deck.distributeCard(this);
    }

    public void draw(Card card) {
        state.draw(card);
        this.state = state.determineState();
    }

    public UserName getUserName() {
        return userName;
    }

    public State getState() {
        return state;
    }
}
