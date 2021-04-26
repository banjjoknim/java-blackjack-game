package domain.user;

import domain.result.GameResult;

import java.math.BigDecimal;

public class Player extends User {

    private final PlayerName playerName;
    private final BettingMoney bettingMoney;

    public Player(PlayerName playerName, BettingMoney bettingMoney) {
        this.playerName = playerName;
        this.bettingMoney = bettingMoney;
    }

    public BigDecimal calculateFinalProfit(Dealer dealer) {
        GameResult gameResult = determineGameResult(dealer);
        return bettingMoney.calculateProfit(gameResult);
    }

    public GameResult determineGameResult(Dealer dealer) {
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