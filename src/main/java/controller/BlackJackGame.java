package controller;


import domain.card.Deck;
import domain.user.*;
import view.InputView;
import view.OutputView;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlackJackGame {

    public static void main(String[] args) {
        Players players = inputPlayers();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        deck.distributeCardsToPlayersAndDealer(players, dealer);
        OutputView.printDistributeCardsMessageAndCardsOfAllUsers(players, dealer);
        proceedPlayersTurn(players, deck);
        proceedDealerTurn(dealer, deck);
        OutputView.printDealerAndPlayersResult(players, dealer);
        OutputView.printDealerAndPlayersProfit(players, dealer);
    }

    private static Players inputPlayers() {
        List<UserName> userNames = inputPlayerNames();
        List<Player> players = userNames.stream()
                .map(BlackJackGame::convertNamesAndBettingMoneyIntoPlayers)
                .collect(toList());
        return new Players(players);
    }

    private static List<UserName> inputPlayerNames() {
        return InputView.inputNames().stream()
                .map(String::trim)
                .map(UserName::new)
                .collect(toList());
    }

    private static Player convertNamesAndBettingMoneyIntoPlayers(UserName userName) {
        BettingMoney bettingMoney = convertAmountIntoBettingMoney(InputView.inputAmount(userName));
        return new Player(userName, bettingMoney);
    }

    private static BettingMoney convertAmountIntoBettingMoney(BigDecimal amount) {
        return new BettingMoney(amount);
    }

    private static void proceedPlayersTurn(Players players, Deck deck) {
        players.getPlayers().forEach(player -> proceedPlayerTurn(player, deck));
    }

    private static void proceedPlayerTurn(Player player, Deck deck) {
        while (player.isOwnTurn()) {
            player.proceedOwnTurn(InputView.inputAnswer(player), deck);
            OutputView.printCardsHeldByPlayer(player);
        }
    }

    private static void proceedDealerTurn(Dealer dealer, Deck deck) {
        while (!dealer.isStay()) {
            dealer.hit(deck);
            OutputView.printDealerGetOneMoreCard();
        }
    }

}
