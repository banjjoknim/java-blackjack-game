package controller;

import domain.user.*;
import view.InputView;
import view.OutputView;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlackJackGame {

    public static void main(String[] args) {
        PlayerNames playerNames = inputPlayerNames();
        Players players = inputPlayers(playerNames);
        Dealer dealer = new Dealer();
        distributeCard(players, dealer);
    }

    private static PlayerNames inputPlayerNames() {
        OutputView.printPleaseInputNames();
        List<PlayerName> playerNames = convertInputNamesIntoPlayerNames(InputView.inputNames());
        return new PlayerNames(playerNames);
    }

    private static List<PlayerName> convertInputNamesIntoPlayerNames(List<String> playerNames) {
        return playerNames.stream()
                .map(String::trim)
                .map(PlayerName::new)
                .collect(toList());
    }

    private static Players inputPlayers(PlayerNames playerNames) {
        List<Player> players = playerNames.getPlayerNames().stream()
                .map(BlackJackGame::convertNamesAndBettingMoneyIntoPlayers)
                .collect(toList());
        return new Players(players);
    }

    private static Player convertNamesAndBettingMoneyIntoPlayers(PlayerName playerName) {
        OutputView.printAskHowMuchBettingToPlayer(playerName);
        BettingMoney bettingMoney = inputBettingMoneyFromInputAmount(InputView.inputAmount());
        return new Player(playerName, bettingMoney);
    }

    private static BettingMoney inputBettingMoneyFromInputAmount(BigDecimal amount) {
        return new BettingMoney(amount);
    }

    private static void distributeCard(Players players, Dealer dealer) {
        OutputView.printHandedOutTwoCardsToPlayers(players);
        for (int i = 0; i < 2; i++) {
            distributeCardToPlayers(players);
            distributeCardToDealer(dealer);
        }
        printCards(players, dealer);
    }

    private static void distributeCardToPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            player.drawCard();
        }
    }

    private static void distributeCardToDealer(Dealer dealer) {
        dealer.drawCard();
    }

    private static void printCards(Players players, Dealer dealer) {
        OutputView.printCardsHeldByDealer(dealer);
        players.getPlayers().stream().forEach(OutputView::printCardsHeldByPlayer);
    }

}