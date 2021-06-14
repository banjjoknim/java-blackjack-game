package domain.user;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import domain.card.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {

    private final UserName userName;
    protected final Cards cards = new Cards();

    public User(UserName userName) {
        this.userName = userName;
    }

    public void draw(Card card) {
        cards.addCard(card);
    }

    public void hit(Deck deck) {
        deck.distributeCard(this);
    }

    public UserName getUserName() {
        return userName;
    }

    public Cards getCards() {
        return cards;
    }
}
