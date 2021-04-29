package controller;

import domain.card.Card;
import domain.card.CardShuffler;
import domain.card.Deck;
import domain.card.RandomCardShuffler;
import domain.result.PlayerProfitStatistics;
import domain.user.*;
import view.InputView;
import view.OutputView;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static view.OutputView.*;

public class BlackJackGame {
    private static final int FIRST = 0;

    public static void main(String[] args) {
        Players players = inputPlayers();
        Dealer dealer = new Dealer();
        initializeCardsOfAllUsers(players, dealer, new RandomCardShuffler());
        showCardsOfAllUsers(players, dealer);
        askWantMoreCardToAllPlayers(players);
        distributeCardToDealer(dealer);
        showDealerAndPlayersResult(players, dealer);
        showDealerAndPlayersProfit(players, dealer);
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
    }

    private static void showCardsOfAllUsers(Players players, Dealer dealer) {
        showCardsOfDealer(dealer);
        showCardsOfPlayers(players);
    }

    private static void showCardsOfDealer(Dealer dealer) {
        Card firstCard = dealer.getCards().get(FIRST);
        String firstCardTypeName = firstCard.getType().getTypeName();
        String firstCardSymbolName = firstCard.getSymbol().getSymbolName();
        OutputView.printCardsHeldByDealer(firstCardTypeName, firstCardSymbolName);
    }

    private static void showCardsOfPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            String playerName = player.getPlayerName().getName();
            String playerCards = convertUserToCards(player);
            OutputView.printCardsHeldByPlayer(playerName, playerCards);
        }
    }

    private static void askWantMoreCardToAllPlayers(Players players) {
        players.getPlayers().forEach(BlackJackGame::askWantMoreCardToPlayer);
    }

    private static void askWantMoreCardToPlayer(Player player) {
        if (player.isBust() || player.isBlackJack()) {
            return;
        }
        chooseAnswer(player);
    }

    private static void chooseAnswer(Player player) {
        String playerName = player.getPlayerName().getName();
        OutputView.printDoYouWantOneMoreCard(playerName);
        Answer answer = new Answer(InputView.inputAnswer());
        if (answer.isYes()) {
            distributeCardToPlayer(player);
        }
    }

    private static void distributeCardToPlayer(Player player) {
        Deck.distributeCard(player);
        String playerName = player.getPlayerName().getName();
        String playerCardsInfo = convertUserToCards(player);
        OutputView.printCardsHeldByPlayer(playerName, playerCardsInfo);
        askWantMoreCardToPlayer(player);
    }

    private static void distributeCardToDealer(Dealer dealer) {
        if (dealer.hasSmallNumberLessThanRuleNumber()) {
            OutputView.printDealerGetOneMoreCard();
            Deck.distributeCard(dealer);
            distributeCardToDealer(dealer);
        }
    }

    private static void showDealerAndPlayersResult(Players players, Dealer dealer) {
        showDealerResult(dealer);
        showPlayersResult(players);
    }

    private static void showDealerResult(Dealer dealer) {
        String dealerCardsInfo = convertUserToCards(dealer);
        int dealerTotalCardNumber = dealer.calculateTotalCardNumber();
        OutputView.printDealerResult(dealerCardsInfo, dealerTotalCardNumber);
    }

    private static void showPlayersResult(Players players) {
        for (Player player : players.getPlayers()) {
            String playerName = player.getPlayerName().getName();
            String playerCardsInfo = convertUserToCards(player);
            int totalCardNumber = player.calculateTotalCardNumber();
            OutputView.printPlayerResult(playerName, playerCardsInfo, totalCardNumber);
        }
    }

    private static String convertUserToCards(User user) {
        return user.getCards().stream()
                .map(card -> card.getType().getTypeName() + card.getSymbol().getSymbolName())
                .collect(joining(SEPARATOR));
    }

    private static void showDealerAndPlayersProfit(Players players, Dealer dealer) {
        PlayerProfitStatistics playerProfitStatistics = new PlayerProfitStatistics(players.producePlayersProfitStatistics(dealer));
        showDealerProfit(dealer, playerProfitStatistics);
        showPlayersProfit(players, playerProfitStatistics);
    }

    private static void showDealerProfit(Dealer dealer, PlayerProfitStatistics playerProfitStatistics) {
        Profit dealerProfit = dealer.getDealerProfitFromPlayerProfitStatistics(playerProfitStatistics);
        printDealerProfit(dealerProfit.getAmount());
    }

    private static void showPlayersProfit(Players players, PlayerProfitStatistics playerProfitStatistics) {
        for (Player player : players.getPlayers()) {
            String playerName = player.getPlayerName().getName();
            Profit playerProfit = playerProfitStatistics.getPlayerProfit(player);
            printPlayerProfit(playerName, playerProfit.getAmount());
        }
    }

}
