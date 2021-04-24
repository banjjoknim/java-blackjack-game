package view;

import domain.user.PlayerName;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class InputView {
    private static final String SEPARATOR = ",";

    private static Scanner scanner = new Scanner(System.in);

    public static List<String> inputNames() {
        OutputView.pleaseInputNames();
        return Arrays.stream(scanner.nextLine().split(SEPARATOR))
                .collect(toList());
    }

    public static BigDecimal inputBettingMoneyAmount() {
        return new BigDecimal(scanner.next());
    }

}
