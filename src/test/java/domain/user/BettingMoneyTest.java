package domain.user;

import domain.result.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class BettingMoneyTest {

    @DisplayName("올바른 입력값으로 BettingMoney 생성을 테스트한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1000.0", "2000.0", "300.456"})
    void createBettingMoneyTest(String amount) {
        // given
        BigDecimal bettingMoneyAmount = new BigDecimal(amount);

        // when
        BettingMoney bettingMoney = new BettingMoney(bettingMoneyAmount);

        // then
        assertAll(
                () -> assertThat(bettingMoney).isInstanceOf(BettingMoney.class),
                () -> assertThat(bettingMoney.getAmount()).isEqualTo(bettingMoneyAmount)
        );
    }

    @DisplayName("잘못된 입력값으로 BettingMoney 생성을 테스트한다.")
    @ParameterizedTest
    @ValueSource(strings = {"-1111.0", "-2375", "-3557.895644", "0"})
    void createBettingMoneyWithNotCorrectInput(String amount) {
        // given
        BigDecimal bettingMoneyAmount = new BigDecimal(amount);

        // when

        // then
        assertThatThrownBy(() -> new BettingMoney(bettingMoneyAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 0보다 커야 합니다.");
    }

    @DisplayName("게임 결과에 따른 BettingMoney 의 수익 계산 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideGameResultAndDividendRate")
    void calculateProfitTest(GameResult gameResult, BigDecimal expectedProfit) {
        // given
        BigDecimal amount = new BigDecimal(1000);
        BettingMoney bettingMoney = new BettingMoney(amount);

        // when
        BigDecimal calculatedProfit = bettingMoney.calculateProfit(gameResult);

        // then
        assertThat(calculatedProfit).isEqualTo(expectedProfit);
    }

    private static Stream<Arguments> provideGameResultAndDividendRate() {
        return Stream.of(
                Arguments.of(GameResult.WIN_WITH_BLACK_JACK, new BigDecimal("1500")),
                Arguments.of(GameResult.WIN, new BigDecimal("1000")),
                Arguments.of(GameResult.DRAW_WITH_BLACK_JACK, new BigDecimal("0")),
                Arguments.of(GameResult.DRAW, new BigDecimal("0")),
                Arguments.of(GameResult.LOSE, new BigDecimal("-1000"))
        );
    }

}