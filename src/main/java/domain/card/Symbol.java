package domain.card;

public enum Symbol {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private String symbolName;

    Symbol(String symbolName) {
        this.symbolName = symbolName;
    }

    public String getSymbolName() {
        return symbolName;
    }

}
