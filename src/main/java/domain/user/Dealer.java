package domain.user;

public class Dealer extends User {
    private static final int DEALER_RULE_NUMBER = 17;

    public Dealer() {
    }

    public boolean isStay() {
        return super.calculateTotalCardNumber() >= DEALER_RULE_NUMBER;
    }

}
