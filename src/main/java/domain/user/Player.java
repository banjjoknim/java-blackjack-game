package domain.user;

public class Player extends User {

    private final Name name;
    private final BettingMoney bettingMoney;

    public Player(Name name, BettingMoney bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    @Override
    public boolean isWait() {
        return !super.hand.hasEnded();
    }

    public Name getName() {
        return name;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
