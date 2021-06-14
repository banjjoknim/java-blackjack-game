package view;

import domain.user.Player;
import domain.user.UserName;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class InputView {
    private static final String SEPARATOR = ",";

    private static Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> inputNames() {
        OutputView.printPleaseInputNames();
        return Arrays.stream(scanner.nextLine().split(SEPARATOR))
                .collect(toList());
    }

    public static BigDecimal inputAmount(UserName userName) {
        OutputView.printAskHowMuchBettingToPlayer(userName);
        return new BigDecimal(scanner.next());
    }

    public static String inputAnswer(Player player) {
        OutputView.printDoYouWantOneMoreCard(player);
        return scanner.next();
    }

}
