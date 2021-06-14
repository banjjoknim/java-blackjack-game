package domain.card;

public enum Symbol {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private String name;

    Symbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
