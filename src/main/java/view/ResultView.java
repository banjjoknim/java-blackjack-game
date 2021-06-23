package view;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

import static java.util.stream.Collectors.joining;

public class ResultView {
    private static final int FIRST = 0;
    private static final String CARD_SEPARATOR = ", ";

    private ResultView() {
    }

    public static void printDealerCards(Dealer dealer) {
        StringBuilder messageBuilder = new StringBuilder("딜러 카드 : ");
        Card card = dealer.getState().getCards().get(FIRST);
        messageBuilder.append(buildMessage(card));
        System.out.println(messageBuilder);
    }

    public static void printPlayerCards(Player player) {
        StringBuilder messageBuilder = new StringBuilder();
        String playerMessage = player.getName().getName() + " 카드 : ";
        String cardsMessage = player.getState().getCards().stream()
                .map(ResultView::buildMessage)
                .collect(joining(CARD_SEPARATOR));
        messageBuilder.append(playerMessage);
        messageBuilder.append(cardsMessage);
        System.out.println(messageBuilder);
    }

    private static StringBuilder buildMessage(Card card) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(card.getType().getName());
        messageBuilder.append(card.getSymbol().getName());
        return messageBuilder;
    }
}
