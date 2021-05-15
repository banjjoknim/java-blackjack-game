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
        BlackJackGame.initializeCardsOfAllUsers(deck, players, dealer);
        OutputView.printCardsOfAllUsers(players, dealer);
        BlackJackGame.askWantMoreCardToAllPlayers(deck, players);
        BlackJackGame.distributeCardToDealer(deck, dealer);
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

    private static void initializeCardsOfAllUsers(Deck deck, Players players, Dealer dealer) {
        deck.distributeCardsToPlayersAndDealer(players, dealer);
        OutputView.printHandedOutTwoCardsToPlayers(players);
    }

    private static void askWantMoreCardToAllPlayers(Deck deck, Players players) {
        players.getPlayers()
                .forEach(player -> askWantMoreCardToPlayer(deck, player));
    }

    private static void askWantMoreCardToPlayer(Deck deck, Player player) {
        if (player.isBust() || player.isBlackJack()) {
            return;
        }
        chooseAnswer(deck, player);
    }

    private static void chooseAnswer(Deck deck, Player player) {
        OutputView.printDoYouWantOneMoreCard(player);
        Answer answer = new Answer(InputView.inputAnswer());
        if (answer.isYes()) {
            distributeCardToPlayer(deck, player);
        }
    }

    private static void distributeCardToPlayer(Deck deck, Player player) {
        deck.distributeCard(player);
        OutputView.printCardsHeldByPlayer(player);
        BlackJackGame.askWantMoreCardToPlayer(deck, player);
    }

    private static void distributeCardToDealer(Deck deck, Dealer dealer) {
        if (dealer.hasSmallNumberLessThanRuleNumber()) {
            OutputView.printDealerGetOneMoreCard();
            deck.distributeCard(dealer);
            BlackJackGame.distributeCardToDealer(deck, dealer);
        }
    }

}
