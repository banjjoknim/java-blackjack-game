package domain.user;

public class Dealer extends User {
    private static final int DEALER_RULE_SCORE = 17;

    public Dealer() {
    }

    @Override
    public boolean isWait() {
        if (super.hand.calculateScore() >= DEALER_RULE_SCORE) {
            return false;
        }
        return true;
    }
}
