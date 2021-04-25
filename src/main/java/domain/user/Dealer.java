package domain.user;

public class Dealer extends User {
    private static final int DEALER_RULE_NUMBER = 17;

    public Dealer() {
    }

    public boolean hasSmallNumberLessThanRuleNumber() {
        return super.calculateSumOfCardNumbers() < DEALER_RULE_NUMBER;
    }

}