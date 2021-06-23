package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);
    private static final String SEPARATOR = ",";

    public static List<String> inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요(쉼표 기준으로 분리).");
        return Arrays.asList(scanner.nextLine().split(SEPARATOR));
    }
}
