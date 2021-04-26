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

    private static List<Card> cards = new ArrayList<>();

    public static void initializeDeck() {
        cards.clear();
        for (Symbol symbol : Symbol.values()) {
            Arrays.stream(Type.values()).forEach(type -> cards.add(new Card(symbol, type)));
        }
        shuffleCards();
    }

    private static void shuffleCards() {
        Collections.shuffle(cards);
    }

    public static void distributeCardsToPlayersAndDealer(Players players, Dealer dealer) {
        for (int i = ZERO; i < NUMBER_OF_CARDS_BY_RULE; i++) {
            players.drawCardEachPlayer();
            Deck.distributeCard(dealer);
        }
    }

    public static void distributeCard(User user) {
        user.addCard(cards.remove(TOP));
    }

    public static List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
