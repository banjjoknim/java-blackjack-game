package domain.card;

import domain.user.User;

import java.util.Collections;
import java.util.List;

public class Deck {
    private static final int TOP = 0;

    private List<Card> cards;

    public Deck() {
        this.cards = Card.getCACHE();
    }

    public void distributeCard(User user) {
        user.draw(cards.remove(TOP));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
