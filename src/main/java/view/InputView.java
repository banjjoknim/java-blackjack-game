package view;

import domain.user.PlayerName;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static List<String> inputNames() {
        OutputView.pleaseInputNames();
        return Arrays.stream(scanner.nextLine().split(","))
                .collect(toList());
    }

    public static double inputBettingMoneyAmount(PlayerName playerName) {
        OutputView.askHowMuchBettingToPlayer(playerName);
        return scanner.nextDouble();
    }

}
