package domain.user;

import domain.card.Cards;
import domain.card.Deck;
import domain.result.GameResult;
import domain.user.state.BlackJack;
import domain.user.state.Bust;
import domain.user.state.Stay;

public class Player extends User {
    private static final String YES = "y";

    private final BettingMoney bettingMoney;

    public Player(UserName userName, BettingMoney bettingMoney) {
        super(userName);
        this.bettingMoney = bettingMoney;
    }

    public boolean isOwnTurn() {
        return !(super.state instanceof Bust) && !(super.state instanceof BlackJack) && !(super.state instanceof Stay);
    }

    public void proceedOwnTurn(String answer, Deck deck) {
        if (YES.equals(answer) && !(super.state instanceof Stay)) {
            super.hit(deck);
        }
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
