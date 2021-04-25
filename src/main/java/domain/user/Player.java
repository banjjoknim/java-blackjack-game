package domain.user;

public class Player extends User {

    private final PlayerName name;
    private final BettingMoney bettingMoney;

    public Player(PlayerName name, BettingMoney bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public PlayerName getName() {
        return name;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

}