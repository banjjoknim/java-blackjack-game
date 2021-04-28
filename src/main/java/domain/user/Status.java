package domain.user;

public enum Status {
    BUST, BLACK_JACK, SURVIVAL;

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isBlackJack() {
        return this == BLACK_JACK;
    }

}
