package domain.user;

import domain.card.Cards;
import domain.card.Deck;
import domain.result.GameResult;

public class Player extends User {
    private static final String YES = "y";

    private final BettingMoney bettingMoney;
    private boolean isStay = false;

    public Player(UserName userName, BettingMoney bettingMoney) {
        super(userName);
        this.bettingMoney = bettingMoney;
    }

    public boolean isOwnTurn() {
        return cards.getStatus().isSurvival() && !isStay;
    }

    public void proceedOwnTurn(String answer, Deck deck) {
        if (YES.equals(answer) && !isStay) {
            super.hit(deck);
            return;
        }
        this.isStay = true;
    }

    public Profit calculateFinalProfit(Dealer dealer) {
        GameResult gameResult = getGameResult(dealer);
        return bettingMoney.calculateProfit(gameResult);
    }

    private GameResult getGameResult(Dealer dealer) {
        Cards playerCards = this.cards;
        Cards dealerCards = dealer.cards;
        int gameResultValue = Integer.compare(playerCards.calculateTotalCardNumber(), dealerCards.calculateTotalCardNumber());
        Status playerStatus = playerCards.getStatus();
        Status dealerStatus = dealerCards.getStatus();
        return GameResult.determineGameResult(gameResultValue, playerStatus, dealerStatus);
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

}
