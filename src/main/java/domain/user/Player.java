package domain.user;

import domain.card.Deck;
import domain.result.GameResult;

public class Player extends User {

    private final PlayerName playerName;
    private final BettingMoney bettingMoney;
    private boolean isStay = false;

    public Player(PlayerName playerName, BettingMoney bettingMoney) {
        this.playerName = playerName;
        this.bettingMoney = bettingMoney;
    }

    public boolean isOwnTurn() {
        return getStatus().isSurvival() && !isStay;
    }

    public void proceedOwnTurn(boolean isStay, Deck deck) {
        if (isStay) {
            this.isStay = true;
            return;
        }
        super.hit(deck);
    }

    public Profit calculateFinalProfit(Dealer dealer) {
        GameResult gameResult = getGameResult(dealer);
        return bettingMoney.calculateProfit(gameResult);
    }

    private GameResult getGameResult(Dealer dealer) {
        int gameResultValue = Integer.compare(this.calculateTotalCardNumber(), dealer.calculateTotalCardNumber());
        Status playerStatus = this.getStatus();
        Status dealerStatus = dealer.getStatus();
        return GameResult.determineGameResult(gameResultValue, playerStatus, dealerStatus);
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

}
