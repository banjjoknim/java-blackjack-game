package domain.card;

import domain.user.Dealer;
import domain.user.Players;
import domain.user.User;

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

    public void distributeCardsToPlayersAndDealer(Players players, Dealer dealer) {
        for (int i = ZERO; i < NUMBER_OF_CARDS_BY_RULE; i++) {
            players.hit(this);
            distributeCard(dealer);
        }
    }

    public void distributeCard(User user) {
        user.addCard(cards.remove(TOP));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
