package domain.card;

import domain.user.Dealer;
import domain.user.Players;
import domain.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final int ZERO = 0;
    private static final int NUMBER_OF_CARDS_BY_RULE = 2;
    private static final int TOP = 0;

    private static final List<Card> CARDS_CACHE = new ArrayList<>();

    static {
        for (Symbol symbol : Symbol.values()) {
            Arrays.stream(Type.values())
                    .forEach(type -> CARDS_CACHE.add(new Card(symbol, type)));
        }
    }

    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>(CARDS_CACHE);
        Collections.shuffle(cards);
    }

    public void distributeCardsToPlayersAndDealer(Players players, Dealer dealer) {
        for (int i = ZERO; i < NUMBER_OF_CARDS_BY_RULE; i++) {
            players.drawCardEachPlayer(this);
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
