package view;

import domain.card.Card;
import domain.result.PlayerProfits;
import domain.user.*;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class OutputView {
    private static final String SEPARATOR = ", ";
    private static final int FIRST = 0;

    private OutputView() {
    }

    public static void printPleaseInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printAskHowMuchBettingToPlayer(UserName userName) {
        System.out.println(userName.getName() + "의 배팅 금액은?");
    }

    public static void printDistributeCardsMessageAndCardsOfAllUsers(Users users) {
        printHandedOutTwoCardsToPlayers(users);
        printCardsOfAllUsers(users);
    }

    private static void printHandedOutTwoCardsToPlayers(Users users) {
        String playerNames = users.getUsers().stream()
                .filter(user -> user instanceof Player)
                .map(User::getUserName)
                .map(UserName::getName)
                .collect(joining(SEPARATOR));
        System.out.println("딜러와 " + playerNames + "에게 2장씩 나누어 주었습니다.");
    }

    private static void printCardsOfAllUsers(Users users) {
        Dealer dealer = users.getDealer();
        List<Player> players = users.getPlayers();
        printCardsOfDealer(dealer);
        printCardsOfPlayers(players);
    }

    private static void printCardsOfDealer(Dealer dealer) {
        Card firstCard = dealer.getCards().get(FIRST);
        printCardsHeldByDealer(firstCard);
    }

    public static void printCardsHeldByDealer(Card card) {
        String firstCardTypeName = card.getType().getName();
        String firstCardSymbolName = card.getSymbol().getSymbolName();
        System.out.println("딜러 : " + firstCardTypeName + firstCardSymbolName);
    }

    private static void printCardsOfPlayers(List<Player> players) {
        players.forEach(OutputView::printCardsHeldByPlayer);
    }

    public static void printCardsHeldByPlayer(Player player) {
        String playerName = player.getUserName().getName();
        String playerCards = convertUserToCards(player);
        System.out.println(playerName + " 카드 : " + playerCards);
    }

    private static String convertUserToCards(User user) {
        return user.getCards().stream()
                .map(card -> card.getType().getName() + card.getSymbol().getSymbolName())
                .collect(joining(SEPARATOR));
    }

    public static void printDoYouWantOneMoreCard(Player player) {
        String playerName = player.getUserName().getName();
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDealerGetOneMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printAllUsersResult(Users users) {
        Dealer dealer = users.getDealer();
        List<Player> players = users.getPlayers();
        printDealerResult(dealer);
        players.forEach(OutputView::printPlayerResult);
    }

    private static void printDealerResult(Dealer dealer) {
        String dealerCards = convertUserToCards(dealer);
        int dealerTotalCardNumber = dealer.calculateTotalCardNumber();
        System.out.println("딜러 : " + dealerCards + " - 결과 : " + dealerTotalCardNumber);
    }

    private static void printPlayerResult(Player player) {
        String playerName = player.getUserName().getName();
        String playerCards = convertUserToCards(player);
        int playerTotalCardNumber = player.calculateTotalCardNumber();
        String playerResultFormat = playerName + " 카드 : " + playerCards + " - 결과 : " + playerTotalCardNumber;
        System.out.println(playerResultFormat);
    }

    public static void printAllUsersProfit(Users users) {
        List<Player> players = users.getPlayers();
        PlayerProfits playerProfits = users.producePlayerProfits();
        printDealerProfit(playerProfits);
        printPlayersProfit(players, playerProfits);
    }

    private static void printDealerProfit(PlayerProfits playerProfits) {
        Profit dealerProfit = playerProfits.calculateDealerProfit();
        System.out.println("딜러 : " + dealerProfit.getAmount());
    }

    private static void printPlayersProfit(List<Player> players, PlayerProfits playerProfits) {
        players.forEach(player -> printPlayerProfit(player, playerProfits));
    }

    private static void printPlayerProfit(Player player, PlayerProfits playerProfits) {
        String playerName = player.getUserName().getName();
        Profit playerProfit = playerProfits.getPlayerProfit(player);
        System.out.println(playerName + " : " + playerProfit.getAmount());
    }

}
