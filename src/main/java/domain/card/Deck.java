package domain.card;

import domain.user.User;
import domain.user.Users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final int ZERO = 0;
    private static final int NUMBER_OF_CARDS_BY_RULE = 2;
    private static final int TOP = 0;

    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>(Card.getCachedCards());
        Collections.shuffle(cards);
    }

    public void distributeCardsToUsers(Users users) {
        for (int i = ZERO; i < NUMBER_OF_CARDS_BY_RULE; i++) {
            users.receiveCards(this);
        }
    }

    public void distributeCard(User user) {
        user.addCard(cards.remove(TOP));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
