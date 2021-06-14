package domain.user;

public class Dealer extends User {
    private static final UserName DEALER_NAME = new UserName("딜러");
    private static final int DEALER_RULE_NUMBER = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isOwnTurn() {
        return super.state.getTotalCardNumber() < DEALER_RULE_NUMBER;
    }

}
