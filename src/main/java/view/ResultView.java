package view;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class ResultView {
    private static final int FIRST = 0;
    private static final String CARD_INFORMATION_SEPARATOR = ", ";

    private ResultView() {
    }

    public static void printDealPhaseResult(Dealer dealer, List<Player> players) {
        StringBuilder resultBuilder = new StringBuilder();
        appendDealPhaseResultOfDealer(resultBuilder, dealer);
        appendDealPhaseResultOfPlayers(resultBuilder, players);
        System.out.println(resultBuilder);
    }

    private static void appendDealPhaseResultOfDealer(StringBuilder resultBuilder, Dealer dealer) {
        Card card = dealer.getState().getCards().get(FIRST);
        resultBuilder.append("딜러 카드 : ");
        resultBuilder.append(buildCardInformation(card));
    }

    private static void appendDealPhaseResultOfPlayers(StringBuilder resultBuilder, List<Player> players) {
        players.forEach(player -> appendDealPhaseResultOfPlayer(resultBuilder, player));
    }

    private static void appendDealPhaseResultOfPlayer(StringBuilder resultBuilder, Player player) {
        resultBuilder.append("\n");
        resultBuilder.append(player.getName().getName() + " 카드 : ");
        String playerCardsResult = player.getState().getCards().stream()
                .map(ResultView::buildCardInformation)
                .collect(joining(CARD_INFORMATION_SEPARATOR));
        resultBuilder.append(playerCardsResult);
    }

    private static StringBuilder buildCardInformation(Card card) {
        StringBuilder informationBuilder = new StringBuilder();
        informationBuilder.append(card.getType().getName());
        informationBuilder.append(card.getSymbol().getName());
        return informationBuilder;
    }
}
