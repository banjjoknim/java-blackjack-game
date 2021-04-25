package domain.user;

import domain.result.GameResult;

public class Player extends User {

    private final PlayerName playerName;
    private final BettingMoney bettingMoney;

    public Player(PlayerName playerName, BettingMoney bettingMoney) {
        this.playerName = playerName;
        this.bettingMoney = bettingMoney;
    }

    public GameResult determineMatchResult(Dealer dealer) {
        int matchResultValue = Integer.compare(super.calculateSumOfCardNumbers(), dealer.calculateSumOfCardNumbers());
        return GameResult.getGameResult(matchResultValue, super.isBlackJack());
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

}