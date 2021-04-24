package domain.card;

public enum Type {
    KING(10), QUEEN(10), JACK(10), TEN(10), NINE(9), EIGHT(8), SEVEN(7),
    SIX(6), FIVE(5), FOUR(4), THREE(3), TWO(2), ACE_ONE(1), ACE_ELEVEN(11);

    private int number;

    Type(int number) {
        this.number = number;
    }

}
