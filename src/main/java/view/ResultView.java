package view;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.function.Function;

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
        resultBuilder.append(buildPlayerCardsResult(player));
    }

    public static void printPlayerCardsResult(Player player) {
        System.out.println(buildPlayerCardsResult(player));
    }

    private static String buildPlayerCardsResult(Player player) {
        StringBuilder resultBuilder = new StringBuilder(player.getName().getName() + " 카드 : ");
        resultBuilder.append(buildCardsInformation(player.getState().getCards()));
        return resultBuilder.toString();
    }

    private static String buildCardsInformation(List<Card> cards) {
        return cards.stream()
                .map(ResultView::buildCardInformation)
                .collect(joining(CARD_INFORMATION_SEPARATOR));
    }

    private static String buildCardInformation(Card card) {
        StringBuilder cardInformationBuilder = new StringBuilder();
        cardInformationBuilder.append(card.getType().getName());
        cardInformationBuilder.append(card.getSymbol().getName());
        return cardInformationBuilder.toString();
    }

    public static void printDealerHasHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
