package controller;

import domain.blackjack.BlackjackGame;
import domain.card.Deck;
import domain.user.*;
import view.InputView;
import view.ResultView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlackjackController {

    public void run() {
        Users users = inputUsers();
        BlackjackGame blackjackGame = new BlackjackGame(users, new Deck());
        runDealPhase(blackjackGame);
        runPlayersPhase(blackjackGame);
        runDealerPhase(blackjackGame);
        ResultView.printResult(blackjackGame);
    }

    private void runDealPhase(BlackjackGame blackjackGame) {
        blackjackGame.proceedDealPhase();
        ResultView.printDealPhaseInformation(blackjackGame);
    }

    private void runPlayersPhase(BlackjackGame blackjackGame) {
        while (blackjackGame.isPlayersPhase()) {
            Player playerOfCurrentTurn = blackjackGame.getPlayerOfCurrentTurn();
            runPlayerPhase(blackjackGame, playerOfCurrentTurn);
        }
    }

    private void runPlayerPhase(BlackjackGame blackjackGame, Player player) {
        while (player.isWait()) {
            runChoiceAnswerPhase(blackjackGame, player);
        }
    }

    private void runChoiceAnswerPhase(BlackjackGame blackjackGame, Player player) {
        if (InputView.inputAnswer(player)) {
            blackjackGame.proceedPlayerHitPhase();
            ResultView.printPlayerInformation(player);
            return;
        }
        player.stay();
    }

    private void runDealerPhase(BlackjackGame blackjackGame) {
        while (blackjackGame.isDealerPhase()) {
            blackjackGame.proceedDealerHitPhase();
            ResultView.printDealerHasHit();
        }
    }

    private Users inputUsers() {
        List<Name> names = inputNames();
        List<User> users = new ArrayList<>();
        users.add(new Dealer());
        names.stream()
                .map(name -> new Player(name, inputBettingMoney(name)))
                .forEach(users::add);
        return new Users(users);
    }

    private List<Name> inputNames() {
        return InputView.inputNames().stream()
                .map(Name::new)
                .collect(toList());
    }

    private BettingMoney inputBettingMoney(Name name) {
        BigDecimal amount = InputView.inputBettingMoneyAmount(name);
        return new BettingMoney(amount);
    }
}
