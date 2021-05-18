package domain.user;

public enum Status {
    BUST, BLACK_JACK, SURVIVAL;

    private static final int BLACK_JACK_NUMBER = 21;

    public static Status determineStatus(int totalCardNumber, boolean canBlackJack) {
        if (totalCardNumber > BLACK_JACK_NUMBER) {
            return BUST;
        }
        if (totalCardNumber == BLACK_JACK_NUMBER && canBlackJack) {
            return BLACK_JACK;
        }
        return SURVIVAL;
    }

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isBlackJack() {
        return this == BLACK_JACK;
    }

}
