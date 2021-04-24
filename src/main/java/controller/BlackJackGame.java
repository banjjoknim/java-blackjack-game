package controller;

import domain.user.PlayerName;
import domain.user.PlayerNames;
import view.InputView;
import view.OutputView;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlackJackGame {

    public static void main(String[] args) {
        PlayerNames playerNames = createPlayerNamesFromInputNames();
    }

    private static PlayerNames createPlayerNamesFromInputNames() {
        OutputView.pleaseInputNames();
        List<PlayerName> playerNames = convertInputNamesIntoPlayerNames(InputView.inputNames());
        return new PlayerNames(playerNames);
    }

    private static List<PlayerName> convertInputNamesIntoPlayerNames(List<String> playerNames) {
        return playerNames.stream()
                .map(String::trim)
                .map(PlayerName::new)
                .collect(toList());
    }

}