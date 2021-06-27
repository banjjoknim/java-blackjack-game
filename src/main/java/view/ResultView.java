package view;

import domain.blackjack.BlackjackGame;
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

    public static void printDealPhaseInformation(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        List<Player> players = blackjackGame.getPlayers();
        StringBuilder resultBuilder = new StringBuilder();
        appendDealPhaseInformationOfDealer(resultBuilder, dealer);
        appendDealPhaseInformationOfPlayers(resultBuilder, players);
        System.out.println(resultBuilder);
    }

    private static void appendDealPhaseInformationOfDealer(StringBuilder resultBuilder, Dealer dealer) {
        Card card = dealer.getState().getCards().get(FIRST);
        resultBuilder.append("딜러 카드 : ");
        resultBuilder.append(buildCardInformation(card));
    }

    private static void appendDealPhaseInformationOfPlayers(StringBuilder resultBuilder, List<Player> players) {
        players.forEach(player -> appendDealPhaseInformationOfPlayer(resultBuilder, player));
    }

    private static void appendDealPhaseInformationOfPlayer(StringBuilder resultBuilder, Player player) {
        resultBuilder.append("\n");
        resultBuilder.append(buildPlayerInformation(player));
    }

    public static void printPlayerInformation(Player player) {
        System.out.println(buildPlayerInformation(player));
    }

    public static void printDealerHasHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printAllUserInformationAndScore(Dealer dealer, List<Player> players) {
        printDealerInformationAndScore(dealer);
        printPlayersInformationAndScore(players);
    }

    private static void printDealerInformationAndScore(Dealer dealer) {
        System.out.println(buildDealerInformationAndScore(dealer));
    }

    private static String buildDealerInformationAndScore(Dealer dealer) {
        StringBuilder builder = new StringBuilder("딜러 카드 : ");
        builder.append(buildCardsInformation(dealer.getState().getCards()));
        builder.append(" - 결과 : ");
        builder.append(dealer.getScore().getValue());
        return builder.toString();
    }

    private static void printPlayersInformationAndScore(List<Player> players) {
        System.out.println(buildPlayersInformationAndScore(players));

    }

    private static String buildPlayersInformationAndScore(List<Player> players) {
        StringBuilder builder = new StringBuilder();
        players.forEach(player -> buildPlayerInformationAndScore(builder, player));
        return builder.toString();
    }

    private static void buildPlayerInformationAndScore(StringBuilder builder, Player player) {
        builder.append(buildPlayerInformation(player));
        builder.append(" - 결과 : ");
        builder.append(player.getScore().getValue());
        builder.append("\n");
    }

    private static String buildPlayerInformation(Player player) {
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
}
