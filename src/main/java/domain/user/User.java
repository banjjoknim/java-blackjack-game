package domain.user;

import domain.card.Card;

import java.util.List;

public abstract class User {

    private final List<Card> cards;

    public User(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
