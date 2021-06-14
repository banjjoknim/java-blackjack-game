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
        return !isStay;
    }

    public void proceedOwnTurn(String answer, Deck deck) {
        if (YES.equals(answer) && !isStay) {
            super.hit(deck);
            return;
        }
        this.isStay = true;
    }

    public Profit calculateFinalProfit(Dealer dealer) {
        GameResult gameResult = findGameResult(dealer);
        return bettingMoney.calculateProfit(gameResult);
    }

    private GameResult findGameResult(Dealer dealer) {
        Cards playerCards = state.getCards();
        Cards dealerCards = dealer.state.getCards();
        int gameResultValue = Integer.compare(playerCards.calculateTotalCardNumber(), dealerCards.calculateTotalCardNumber());
        return GameResult.determineGameResult(gameResultValue);
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

}
