package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final int TOP = 0;

    private static List<Card> cards = new ArrayList<>();

    static {
        Arrays.stream(Symbol.values()).forEach(Deck::fillCards);
        shuffle();
    }

    private static void fillCards(Symbol symbol) {
        Arrays.stream(Type.values()).forEach(type -> cards.add(new Card(symbol, type)));
    }

    private static void shuffle() {
        Collections.shuffle(cards);
    }

    public static Card giveCardOnTop() {
        return cards.remove(TOP);
    }

    public static List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
