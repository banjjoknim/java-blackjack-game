package view;

import domain.card.Card;
import domain.user.*;

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
        players.getPlayers().stream().forEach(OutputView::printCardsHeldByPlayer);
    }

    public static void printCardsHeldByPlayer(Player player) {
        String cardTypeAndSymbols = player.getCards().stream()
                .map(card -> card.getType().getNumber() + card.getSymbol().getSymbolName())
                .collect(joining(SEPARATOR));
        System.out.println(player.getPlayerName().getName() + "카드 : " + cardTypeAndSymbols);
    }

    public static void printCardsHeldByDealer(Dealer dealer) {
        Card firstCard = dealer.getCards().get(FIRST);
        System.out.println("딜러 : " + firstCard.getType().getNumber() + firstCard.getSymbol().getSymbolName());
    }

    public static void printDoYouWantOneMoreCard(Player player) {
        System.out.println(player.getPlayerName().getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

}