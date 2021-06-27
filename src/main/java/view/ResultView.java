package view;

import domain.blackjack.BlackjackGame;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.UserProfits;

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

    public static void printResult(BlackjackGame blackjackGame) {
        printAllUserInformationAndScore(blackjackGame);
        printAllUserProfitAmount(blackjackGame);
    }

    private static void printAllUserInformationAndScore(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        List<Player> players = blackjackGame.getPlayers();
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
        players.forEach(player -> builder.append(buildPlayerInformationAndScore(player)));
        return builder.toString();
    }

    private static String buildPlayerInformationAndScore(Player player) {
        StringBuilder builder = new StringBuilder();
        builder.append(buildPlayerInformation(player));
        builder.append(" - 결과 : ");
        builder.append(player.getScore().getValue());
        builder.append("\n");
        return builder.toString();
    }

    private static String buildPlayerInformation(Player player) {
        StringBuilder builder = new StringBuilder();
        builder.append(player.getName().getName() + " 카드 : ");
        builder.append(buildCardsInformation(player.getState().getCards()));
        return builder.toString();
    }

    private static String buildCardsInformation(List<Card> cards) {
        return cards.stream()
                .map(ResultView::buildCardInformation)
                .collect(joining(CARD_INFORMATION_SEPARATOR));
    }

    private static String buildCardInformation(Card card) {
        StringBuilder builder = new StringBuilder();
        builder.append(card.getType().getName());
        builder.append(card.getSymbol().getName());
        return builder.toString();
    }

    private static void printAllUserProfitAmount(BlackjackGame blackjackGame) {
        StringBuilder builder = new StringBuilder();
        UserProfits userProfits = blackjackGame.getUserProfits();
        Dealer dealer = blackjackGame.getDealer();
        builder.append(buildDealerProfitAmount(userProfits, dealer));
        List<Player> players = blackjackGame.getPlayers();
        players.forEach(player -> builder.append(buildPlayerProfitResult(userProfits, player)));
        System.out.println(builder);
    }

    private static String buildDealerProfitAmount(UserProfits userProfits, Dealer dealer) {
        StringBuilder builder = new StringBuilder();
        builder.append("딜러 : ");
        builder.append(userProfits.getUserProfit(dealer).getAmount());
        builder.append("\n");
        return builder.toString();
    }

    private static String buildPlayerProfitResult(UserProfits userProfits, Player player) {
        StringBuilder builder = new StringBuilder();
        builder.append(player.getName().getName());
        builder.append(" : ");
        builder.append(userProfits.getUserProfit(player).getAmount());
        builder.append("\n");
        return builder.toString();
    }
}
