package view;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class InputView {
    public static final String YES = "y";
    public static final String NO = "n";
    private static final String SEPARATOR = ",";

    private static Scanner scanner = new Scanner(System.in);

    public static List<String> inputNames() {
        return Arrays.stream(scanner.nextLine().split(SEPARATOR))
                .collect(toList());
    }

    public static BigDecimal inputAmount() {
        return new BigDecimal(scanner.next());
    }

    public static String inputAnswer() {
        return scanner.next().toLowerCase();
    }

}
