package domain.result;

import domain.user.Profit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class EarningRateTest {

    @DisplayName("게임 결과에 해당하는 수익률을 찾는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideGameResultAndExpected")
    void 입력에_해당하는_게임_결과를_찾는다(GameResult gameResult, EarningRate expected) {
        // given

        // when
        EarningRate earningRate = EarningRate.from(gameResult);

        // then
        assertThat(earningRate).isEqualTo(expected);
    }

    private static Stream<Arguments> provideGameResultAndExpected() {
        return Stream.of(
                Arguments.of(GameResult.WIN, EarningRate.ONE),
                Arguments.of(GameResult.DRAW, EarningRate.ZERO),
                Arguments.of(GameResult.LOSE, EarningRate.MINUS_ONE)
        );
    }

    @DisplayName("수익률과 입력받은 금액으로 수익을 계산하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideEarningRateAndAmount")
    void 수익률과_입력받은_금액으로_수익을_계산한다(EarningRate earningRate, BigDecimal amount, Profit expected) {
        // given

        // when
        Profit profit = earningRate.calculateProfit(amount);

        // then
        assertThat(profit).isEqualTo(expected);
    }

    private static Stream<Arguments> provideEarningRateAndAmount() {
        return Stream.of(
                Arguments.of(EarningRate.ONE_POINT_FIVE, BigDecimal.valueOf(1000), new Profit(BigDecimal.valueOf(1500))),
                Arguments.of(EarningRate.ONE, BigDecimal.valueOf(1000), new Profit(BigDecimal.valueOf(1000))),
                Arguments.of(EarningRate.ZERO, BigDecimal.valueOf(1000), new Profit(BigDecimal.valueOf(0))),
                Arguments.of(EarningRate.MINUS_ONE, BigDecimal.valueOf(1000), new Profit(BigDecimal.valueOf(-1000)))
        );
    }
}
