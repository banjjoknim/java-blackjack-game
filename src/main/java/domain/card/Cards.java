package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static List<Card> cards;

    static {
        Arrays.stream(Symbol.values()).forEach(Cards::fillCards);
    }

    private static void fillCards(Symbol symbol) {
        Arrays.stream(Type.values()).forEach(type -> cards.add(new Card(symbol, type)));
    }

    public static List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
