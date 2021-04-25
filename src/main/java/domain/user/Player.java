package domain.user;

public class Player extends User {

    private final PlayerName playerName;
    private final BettingMoney bettingMoney;

    public Player(PlayerName playerName, BettingMoney bettingMoney) {
        this.playerName = playerName;
        this.bettingMoney = bettingMoney;
    }

    private GameResult determineMatchResult(Dealer dealer) {
        int matchResultValue = Integer.compare(super.calculateSumOfCardNumbers(), dealer.calculateSumOfCardNumbers());
        return GameResult.getMatchResult(matchResultValue);
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

}