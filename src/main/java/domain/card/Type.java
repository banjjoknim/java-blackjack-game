package domain.card;

public enum Type {
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10),
    TEN("10", 10),
    NINE("9", 9),
    EIGHT("8", 8),
    SEVEN("7", 7),
    SIX("6", 6),
    FIVE("5", 5),
    FOUR("4", 4),
    THREE("3", 3),
    TWO("2", 2),
    ACE("A", 1);

    private static int TEN_NUMBER = 10;

    private String typeName;
    private int number;

    Type(String typeName, int number) {
        this.typeName = typeName;
        this.number = number;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public boolean isTenNumber() {
        return number == TEN_NUMBER;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getNumber() {
        return number;
    }

}
