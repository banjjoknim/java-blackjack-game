package view;

import domain.user.BettingMoney;
import domain.user.Name;
import domain.user.Player;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);
    private static final String SEPARATOR = ",";
    private static final String YES = "y";

    private InputView() {
    }

    public static List<String> inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요(쉼표 기준으로 분리).");
        return Arrays.asList(scanner.nextLine().split(SEPARATOR));
    }

    public static BigDecimal inputBettingMoneyAmount(Name name) {
        StringBuilder messageBuilder = new StringBuilder(name.getName());
        messageBuilder.append("의 배팅 금액은?");
        System.out.println(messageBuilder);
        return BigDecimal.valueOf(scanner.nextInt());
    }

    public static boolean inputAnswer(Player player) {
        StringBuilder questionBuilder = new StringBuilder(player.getName().getName());
        questionBuilder.append("는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        System.out.println(questionBuilder);
        return YES.equals(scanner.next());
    }
}
