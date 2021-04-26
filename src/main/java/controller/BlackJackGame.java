package controller;

import domain.card.Deck;
import domain.user.*;
import view.InputView;
import view.OutputView;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlackJackGame {
    private static final int ZERO = 0;
    private static final int NUMBER_OF_CARDS_BY_RULE = 2;

    public static void main(String[] args) {
        Players players = inputPlayers();
        Dealer dealer = new Dealer();
        Deck.initializeDeck();
        OutputView.printHandedOutTwoCardsToPlayers(players);
        distributeCard(players, dealer);
        OutputView.printCards(players, dealer);
        askWantMoreCardToPlayers(players);
        while (dealer.hasSmallNumberLessThanRuleNumber()) {
            OutputView.printDealerGetOneMoreCard();
            Deck.distributeCard(dealer);
        }
        OutputView.printResults(players, dealer);
        OutputView.printFinalProfits(players.producePlayersFinalProfits(dealer));
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

    private static void distributeCard(Players players, Dealer dealer) {
        for (int i = ZERO; i < NUMBER_OF_CARDS_BY_RULE; i++) {
            players.drawCardsEachOther();
            Deck.distributeCard(dealer);
        }
    }

    private static void askWantMoreCardToPlayers(Players players) {
        players.getPlayers().forEach(BlackJackGame::chooseAnswer);
    }

    private static void chooseAnswer(Player player) {
        boolean isBust = player.isBust();
        boolean isBlackJack = player.isBlackJack();
        while (!isBust || isBlackJack) {
            OutputView.printDoYouWantOneMoreCard(player);
            String answer = InputView.inputAnswer();
            if (answer.equals("n")) {
                break;
            }
            Deck.distributeCard(player);
            OutputView.printCardsHeldByPlayer(player);
            isBust = player.isBust();
        }
    }

}