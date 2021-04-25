package domain.user;

public class Player extends User {

    private final PlayerName playerName;
    private final BettingMoney bettingMoney;

    public Player(PlayerName playerName, BettingMoney bettingMoney) {
        this.playerName = playerName;
        this.bettingMoney = bettingMoney;
    }

    private MatchResult determineMatchResult(Dealer dealer) {
        int matchResultValue = Integer.compare(super.calculateSumOfCardNumbers(), dealer.calculateSumOfCardNumbers());
        return MatchResult.getMatchResult(matchResultValue);
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

}