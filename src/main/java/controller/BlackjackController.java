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
        List<Name> names = InputView.inputNames().stream()
                .map(Name::new)
                .collect(toList());
        List<User> users = new ArrayList<>();
        users.add(new Dealer());
        for (Name name : names) {
            BigDecimal amount = InputView.inputBettingMoneyAmount(name);
            users.add(new Player(name, new BettingMoney(amount)));
        }

        BlackjackGame blackjackGame = new BlackjackGame(new Users(users), new Deck());
        blackjackGame.proceedDealPhase();
        ResultView.printDealPhaseInformation(blackjackGame);

        while (blackjackGame.isPlayersPhase()) {
            Player playerOfCurrentTurn = blackjackGame.getPlayerOfCurrentTurn();
            while (playerOfCurrentTurn.isWait()) {
                if (InputView.inputAnswer(playerOfCurrentTurn)) {
                    blackjackGame.proceedPlayerHitPhase();
                    ResultView.printPlayerInformation(playerOfCurrentTurn);
                    continue;
                }
                playerOfCurrentTurn.stay();
            }
        }

        while (blackjackGame.isDealerPhase()) {
            blackjackGame.proceedDealerHitPhase();
            ResultView.printDealerHasHit();
        }
        ResultView.printAllUserInformationAndScore(blackjackGame);

        ResultView.printAllUserProfitResult(blackjackGame);
    }
}
