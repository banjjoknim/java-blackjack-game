package domain.user;

import domain.card.Deck;
import domain.result.GameResult;

public class Player extends User {
    private static final boolean STAY = true;

    private final PlayerName playerName;
    private final BettingMoney bettingMoney;
    private boolean isStay = false;

    public Player(PlayerName playerName, BettingMoney bettingMoney) {
        this.playerName = playerName;
        this.bettingMoney = bettingMoney;
    }

    public void proceedOwnTurn(boolean isStay, Deck deck) {
        if (isStay) {
            this.isStay = STAY;
            return;
        }
        super.hit(deck);
    }

    public Profit calculateFinalProfit(Dealer dealer) {
        GameResult gameResult = determineGameResult(dealer);
        return bettingMoney.calculateProfit(gameResult);
    }

    private GameResult determineGameResult(Dealer dealer) {
        int gameResultValue = Integer.compare(this.calculateTotalCardNumber(), dealer.calculateTotalCardNumber());
        Status playerStatus = this.getStatus();
        Status dealerStatus = dealer.getStatus();
        return GameResult.determineWinOrDrawOrLose(gameResultValue, playerStatus, dealerStatus);
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    public boolean isStay() {
        return isStay;
    }

}
