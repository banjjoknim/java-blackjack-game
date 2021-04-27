package view;

import domain.card.Card;
import domain.result.PlayerProfitStatistics;
import domain.user.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class OutputView {
    private static final String SEPARATOR = ", ";
    private static final int FIRST = 0;


    public static void printPleaseInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printAskHowMuchBettingToPlayer(PlayerName playerName) {
        System.out.println(playerName.getName() + "의 배팅 금액은?");
    }

    public static void printHandedOutTwoCardsToPlayers(Players players) {
        String playerNames = players.getPlayers().stream()
                .map(Player::getPlayerName)
                .map(PlayerName::getName)
                .collect(joining(SEPARATOR));
        System.out.println("딜러와 " + playerNames + "에게 2장씩 나누어 주었습니다.");
    }

    public static void printCards(Players players, Dealer dealer) {
        OutputView.printCardsHeldByDealer(dealer);
        players.getPlayers().forEach(OutputView::printCardsHeldByPlayer);
    }

    public static void printResults(Players players, Dealer dealer) {
        printDealerResult(dealer);
        printPlayersResult(players);
    }

    private static void printDealerResult(Dealer dealer) {
        System.out.println("딜러 : " + getCardsInfo(dealer) + " - 결과 : " + dealer.calculateTotalCardNumber());
    }

    private static void printPlayersResult(Players players) {
        players.getPlayers().forEach(OutputView::printPlayerResult);
    }

    private static void printPlayerResult(Player player) {
        String playerName = player.getPlayerName().getName();
        int sumOfCardNumbers = player.calculateTotalCardNumber();
        String playerResultFormat = playerName + " 카드 : " + getCardsInfo(player) + " - 결과 : " + sumOfCardNumbers;
        System.out.println(playerResultFormat);
    }

    public static void printCardsHeldByPlayer(Player player) {
        String cardTypeAndSymbols = getCardsInfo(player);
        System.out.println(player.getPlayerName().getName() + " 카드 : " + cardTypeAndSymbols);
    }

    private static String getCardsInfo(User dealer) {
        return dealer.getCards().stream()
                .map(card -> card.getType().getTypeName() + card.getSymbol().getSymbolName())
                .collect(joining(SEPARATOR));
    }

    private static void printCardsHeldByDealer(Dealer dealer) {
        Card firstCard = dealer.getCards().get(FIRST);
        System.out.println("딜러 : " + firstCard.getType().getTypeName() + firstCard.getSymbol().getSymbolName());
    }

    public static void printDoYouWantOneMoreCard(Player player) {
        System.out.println(player.getPlayerName().getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDealerGetOneMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printDealerAndPlayersProfit(Players players, PlayerProfitStatistics playerProfitStatistics) {
        printDealerProfit(playerProfitStatistics);
        printPlayersProfit(players, playerProfitStatistics);
    }

    private static void printDealerProfit(PlayerProfitStatistics playerProfitStatistics) {
        System.out.println("딜러 : " + playerProfitStatistics.calculateDealerProfit());
    }

    private static void printPlayersProfit(Players players, PlayerProfitStatistics playerProfitStatistics) {
        for (Player player : players.getPlayers()) {
            printPlayerProfit(player, playerProfitStatistics);
        }
    }

    private static void printPlayerProfit(Player player, PlayerProfitStatistics playerProfitStatistics) {
        System.out.println(player.getPlayerName().getName() +
                " : " +
                playerProfitStatistics.getPlayerProfit(player)
                        .setScale(0, RoundingMode.HALF_EVEN)
                        .doubleValue());
    }
}