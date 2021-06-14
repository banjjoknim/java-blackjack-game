package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private static final List<Card> CACHE = new ArrayList<>();

    static {
        for (Symbol symbol : Symbol.values()) {
            for (Type type : Type.values()) {
                CACHE.add(new Card(symbol, type));
            }
        }
    }

    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    public static List<Card> getCACHE() {
        return new ArrayList<>(CACHE);
    }
}
