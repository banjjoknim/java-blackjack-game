package domain.user;

import domain.result.GameResult;

public class Player extends User {

    private final PlayerName playerName;
    private final BettingMoney bettingMoney;

    public Player(PlayerName playerName, BettingMoney bettingMoney) {
        this.playerName = playerName;
        this.bettingMoney = bettingMoney;
    }

    public Profit calculateFinalProfit(Dealer dealer) {
        GameResult gameResult = determineGameResult(dealer);
        return bettingMoney.calculateProfit(gameResult);
    }

    private GameResult determineGameResult(Dealer dealer) {
        int gameResultValue = Integer.compare(this.calculateTotalCardNumber(), dealer.calculateTotalCardNumber());
        Status playerStatus = this.determineStatus();
        Status dealerStatus = dealer.determineStatus();
        return GameResult.determineWinOrDrawOrLose(gameResultValue, playerStatus, dealerStatus);
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

}
