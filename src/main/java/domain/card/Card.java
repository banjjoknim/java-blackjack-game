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

    private Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    public static Card of(Symbol symbol, Type type) {
        return CACHE.stream()
                .filter(card -> card.symbol == symbol && card.type == type)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 카드입니다."));
    }

    public boolean isAce() {
        return type == Type.ACE;
    }

    public static List<Card> getCACHE() {
        return new ArrayList<>(CACHE);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Type getType() {
        return type;
    }
}
