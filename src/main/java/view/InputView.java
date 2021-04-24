package view;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class InputView {
    private static final String SEPARATOR = ",";

    private static Scanner scanner = new Scanner(System.in);

    public static List<String> inputNames() {
        return Arrays.stream(scanner.nextLine().split(SEPARATOR))
                .collect(toList());
    }

    public static BigDecimal inputAmount() {
        return new BigDecimal(scanner.next());
    }

}
