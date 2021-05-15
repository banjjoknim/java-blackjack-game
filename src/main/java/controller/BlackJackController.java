package controller;

import domain.card.Deck;
import domain.game.BlackJackGame;
import domain.user.*;
import view.InputView;
import view.OutputView;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlackJackController {

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(inputPlayers(), new Dealer(), new Deck());
        blackJackGame.proceedDistributeCardsToUsers();
        OutputView.printDistributeCardsMessageAndCardsOfAllUsers(blackJackGame);
        blackJackGame.proceedPlayersTurn();
        blackJackGame.proceedDealerTurn();
        OutputView.printDealerAndPlayersResult(blackJackGame);
        OutputView.printDealerAndPlayersProfit(blackJackGame);
    }

    private static Players inputPlayers() {
        List<PlayerName> playerNames = inputPlayerNames();
        List<Player> players = playerNames.stream()
                .map(BlackJackController::convertNamesAndBettingMoneyIntoPlayers)
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

}
