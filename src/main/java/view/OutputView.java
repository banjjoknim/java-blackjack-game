package view;

import domain.user.Player;
import domain.user.PlayerName;
import domain.user.Players;

import java.math.BigDecimal;

import static java.util.stream.Collectors.joining;

public class OutputView {
    public static final String SEPARATOR = ", ";

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

    public static void printDealerResult(String dealerCards, int dealerTotalCardNumber) {
        System.out.println("딜러 : " + dealerCards + " - 결과 : " + dealerTotalCardNumber);
    }

    public static void printPlayerResult(String playerName, String playerCards, int playerTotalCardNumber) {
        String playerResultFormat = playerName + " 카드 : " + playerCards + " - 결과 : " + playerTotalCardNumber;
        System.out.println(playerResultFormat);
    }

    public static void printCardsHeldByPlayer(String playerName, String playerCards) {
        System.out.println(playerName + " 카드 : " + playerCards);
    }

    public static void printCardsHeldByDealer(String cardTypeName, String cardSymbolName) {
        System.out.println("딜러 : " + cardTypeName + cardSymbolName);
    }

    public static void printDoYouWantOneMoreCard(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDealerGetOneMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printDealerProfit(BigDecimal dealerProfitAmount) {
        System.out.println("딜러 : " + dealerProfitAmount);
    }

    public static void printPlayerProfit(String playerName, BigDecimal profitAmount) {
        System.out.println(playerName + " : " + profitAmount);
    }

}
