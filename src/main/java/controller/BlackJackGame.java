package controller;

import domain.card.CardShuffler;
import domain.card.Deck;
import domain.card.RandomCardShuffler;
import domain.result.PlayerProfitStatistics;
import domain.user.*;
import view.InputView;
import view.OutputView;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static view.OutputView.printDealerAndPlayersProfit;
import static view.OutputView.printResults;

public class BlackJackGame {

    public static void main(String[] args) {
        Players players = inputPlayers();
        Dealer dealer = new Dealer();
        initializeCardsOfAllUsers(players, dealer, new RandomCardShuffler());
        askWantMoreCardToAllPlayers(players);
        distributeCardToDealer(dealer);
        printResults(players, dealer);
        PlayerProfitStatistics playerProfitStatistics = new PlayerProfitStatistics(players.producePlayersProfitStatistics(dealer));
        printDealerAndPlayersProfit(players, playerProfitStatistics);
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

    private static void initializeCardsOfAllUsers(Players players, Dealer dealer, CardShuffler cardShuffler) {
        Deck.initializeDeck(cardShuffler);
        Deck.distributeCardsToPlayersAndDealer(players, dealer);
        OutputView.printHandedOutTwoCardsToPlayers(players);
        OutputView.printCards(players, dealer);
    }

    private static void askWantMoreCardToAllPlayers(Players players) {
        players.getPlayers().forEach(BlackJackGame::chooseAnswer);
    }

    private static void chooseAnswer(Player player) {
        if (player.isBust() || player.isBlackJack()) {
            return;
        }
        OutputView.printDoYouWantOneMoreCard(player);
        Answer answer = new Answer(InputView.inputAnswer());
        if (answer.isYes()) {
            distributeCardToPlayer(player);
        }
    }

    private static void distributeCardToPlayer(Player player) {
        Deck.distributeCard(player);
        OutputView.printCardsHeldByPlayer(player);
        chooseAnswer(player);
    }

    private static void distributeCardToDealer(Dealer dealer) {
        if (dealer.hasSmallNumberLessThanRuleNumber()) {
            OutputView.printDealerGetOneMoreCard();
            Deck.distributeCard(dealer);
            distributeCardToDealer(dealer);
        }
    }

}