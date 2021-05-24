package view;

import domain.user.Player;
import domain.user.PlayerName;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class InputView {
    private static final String YES = "y";
    private static final String SEPARATOR = ",";

    private static Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> inputNames() {
        OutputView.printPleaseInputNames();
        return Arrays.stream(scanner.nextLine().split(SEPARATOR))
                .collect(toList());
    }

    public static BigDecimal inputAmount(PlayerName playerName) {
        OutputView.printAskHowMuchBettingToPlayer(playerName);
        return new BigDecimal(scanner.next());
    }

    public static boolean inputAnswer(Player player) {
        OutputView.printDoYouWantOneMoreCard(player);
        return !YES.equals(scanner.next().toLowerCase());
    }

}
