package view;

import domain.user.PlayerName;

public class OutputView {

    public static void pleaseInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void askHowMuchBettingToPlayer(PlayerName playerName) {
        System.out.println(playerName.getName() + "의 배팅 금액은?");
    }

}