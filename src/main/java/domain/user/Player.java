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
        GameResult gameResult = determineWinOrDrawOrLose(dealer);
        return bettingMoney.calculateProfit(gameResult);
    }

    public GameResult determineWinOrDrawOrLose(Dealer dealer) {
        int gameResultValue = Integer.compare(super.calculateTotalCardNumber(), dealer.calculateTotalCardNumber());
        return GameResult.determineGameResult(gameResultValue, this, dealer);
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

}