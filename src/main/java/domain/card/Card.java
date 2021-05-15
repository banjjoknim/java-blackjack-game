package domain.card;

public class Card {

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
        return type.hasTenNumber();
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Type getType() {
        return type;
    }

}
