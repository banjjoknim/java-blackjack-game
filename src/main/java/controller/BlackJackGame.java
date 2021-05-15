package controller;

import domain.card.Deck;
import domain.user.*;
import view.InputView;
import view.OutputView;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static view.InputView.YES;

public class BlackJackGame {

    public static void main(String[] args) {
        Players players = inputPlayers();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        deck.distributeCardsToPlayersAndDealer(players, dealer);
        OutputView.printDistributeCardsMessageAndCardsOfAllUsers(players, dealer);
        proceedGame(players, dealer, deck);
        OutputView.printDealerAndPlayersResult(players, dealer);
        OutputView.printDealerAndPlayersProfit(players, dealer);
    }

    private static Players inputPlayers() {
        List<PlayerName> playerNames = inputPlayerNames();
        List<Player> players = playerNames.stream()
                .map(BlackJackGame::convertNamesAndBettingMoneyIntoPlayers)
                .collect(toList());
        return new Players(players);
    }

    private static List<PlayerName> inputPlayerNames() {
        OutputView.printPleaseInputNames();
        return convertInputNamesIntoPlayerNames(InputView.inputNames());
    }

    private static List<PlayerName> convertInputNamesIntoPlayerNames(List<String> playerNames) {
        return playerNames.stream()
                .map(String::trim)
                .map(PlayerName::new)
                .collect(toList());
    }

    private static Player convertNamesAndBettingMoneyIntoPlayers(PlayerName playerName) {
        OutputView.printAskHowMuchBettingToPlayer(playerName);
        BettingMoney bettingMoney = convertAmountIntoBettingMoney(InputView.inputAmount());
        return new Player(playerName, bettingMoney);
    }

    private static BettingMoney convertAmountIntoBettingMoney(BigDecimal amount) {
        return new BettingMoney(amount);
    }

    private static void proceedGame(Players players, Dealer dealer, Deck deck) {
        proceedPlayersTurn(deck, players);
        proceedDealerTurn(deck, dealer);
    }

    private static void proceedPlayersTurn(Deck deck, Players players) {
        players.getPlayers()
                .forEach(player -> chooseAnswer(deck, player));
    }

    private static void chooseAnswer(Deck deck, Player player) {
        while (!player.isBust() && !player.isBlackJack()) {
            OutputView.printDoYouWantOneMoreCard(player);
            if (YES.equals(InputView.inputAnswer())) {
                player.hit(deck);
                OutputView.printCardsHeldByPlayer(player);
                continue;
            }
            break;
        }
    }

    private static void proceedDealerTurn(Deck deck, Dealer dealer) {
        while (!dealer.isStay()) {
            dealer.hit(deck);
            OutputView.printDealerGetOneMoreCard();
        }
    }

}
