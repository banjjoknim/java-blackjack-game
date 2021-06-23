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
        resultBuilder.append(buildCardsInformation(player.getState().getCards()));
    }

    public static void printPlayerCardsResult(Player player) {
        StringBuilder resultBuilder = new StringBuilder(player.getName().getName() + " 카드 : ");
        resultBuilder.append(buildCardsInformation(player.getState().getCards()));
        System.out.println(resultBuilder);
    }

    private static String buildCardsInformation(List<Card> cards) {
        return cards.stream()
                .map(ResultView::buildCardInformation)
                .collect(joining(CARD_INFORMATION_SEPARATOR));
    }

    private static StringBuilder buildCardInformation(Card card) {
        StringBuilder informationBuilder = new StringBuilder();
        informationBuilder.append(card.getType().getName());
        informationBuilder.append(card.getSymbol().getName());
        return informationBuilder;
    }

    public static void printDealerHasHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
