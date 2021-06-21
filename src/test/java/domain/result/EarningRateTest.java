package domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
}
