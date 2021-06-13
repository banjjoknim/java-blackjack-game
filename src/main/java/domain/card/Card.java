package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card {
    private static final List<Card> CACHED_CARDS = new ArrayList<>();

    static {
        for (Symbol symbol : Symbol.values()) {
            Arrays.stream(Type.values())
                    .forEach(type -> CACHED_CARDS.add(new Card(symbol, type)));
        }
    }

    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    public boolean isAceType() {
        return type.isAce();
    }

    public boolean isTenNumberType() {
        return type.isTenNumber();
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Type getType() {
        return type;
    }

    public static List<Card> getCachedCards() {
        return CACHED_CARDS;
    }
}
