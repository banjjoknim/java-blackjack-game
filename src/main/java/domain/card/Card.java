package domain.card;

public class Card {

    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    public boolean isAce() {
        return type.equals(Type.ACE);
    }

    public boolean isTenNumberType() {
        return type.equals(Type.KING) || type.equals(Type.QUEEN) || type.equals(Type.JACK) || type.equals(Type.TEN);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Type getType() {
        return type;
    }

}