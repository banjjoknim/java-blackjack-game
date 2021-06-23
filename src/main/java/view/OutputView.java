package view;

import domain.card.Card;
import domain.user.Dealer;

public class OutputView {
    private static final int FIRST = 0;

    private OutputView() {
    }

    public static void printDealerCards(Dealer dealer) {
        StringBuilder messageBuilder = new StringBuilder("딜러 : ");
        Card card = dealer.getState().getCards().get(FIRST);
        messageBuilder.append(card.getType().getName());
        messageBuilder.append(card.getSymbol().getName());
        System.out.println(messageBuilder);
    }
}
