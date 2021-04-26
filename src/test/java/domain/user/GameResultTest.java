package domain.user;

import domain.result.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameResultTest {

    @DisplayName("GameResult 의 입력값에 따른 결과 도출 기능을 테스트한다.")
    @Test
    void getGameResultTest() {
        // given
        int winValue = 1;
        int drawValue = 0;
        int loseValue = -1;

        // when

        // then
        assertAll(
                () -> assertThat(GameResult.getGameResult(winValue, true)).isEqualTo(GameResult.WIN_WITH_BLACK_JACK),
                () -> assertThat(GameResult.getGameResult(winValue, false)).isEqualTo(GameResult.WIN),
                () -> assertThat(GameResult.getGameResult(drawValue, true)).isEqualTo(GameResult.DRAW_WITH_BLACK_JACK),
                () -> assertThat(GameResult.getGameResult(drawValue, false)).isEqualTo(GameResult.DRAW),
                () -> assertThat(GameResult.getGameResult(loseValue, false)).isEqualTo(GameResult.LOSE)
        );
    }

    @DisplayName("GameResult 에 존재하지 않는 결과를 도출할때 예외 처리를 테스트한다.")
    @ParameterizedTest
    @ValueSource(ints = {-3, 3, 4, 5})
    void getGameResultWithNotExistValueTest(int gameResultValue) {
        // given

        // when

        // then
        assertAll(
                () -> assertThatThrownBy(() -> GameResult.getGameResult(gameResultValue, true))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("게임 결과는 승, 무, 패 중에서 하나여야 합니다.")
        );
    }

    @DisplayName("GameResult 의 배당률과 배팅 금액으로 결과 수익 계산 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideGameResultAndDividendRate")
    void multiplyDividendRateTest(GameResult gameResult, BigDecimal dividendRate) {
        // given
        BigDecimal amount = new BigDecimal(1000);

        // when
        BigDecimal multipliedByDividendRateAmount = gameResult.multiplyDividendRate(amount);

        // then
        assertThat(multipliedByDividendRateAmount).isEqualTo(amount.multiply(dividendRate));
    }

    private static Stream<Arguments> provideGameResultAndDividendRate() {
        return Stream.of(
                Arguments.of(GameResult.WIN_WITH_BLACK_JACK, new BigDecimal("1.5")),
                Arguments.of(GameResult.WIN, new BigDecimal("1")),
                Arguments.of(GameResult.DRAW_WITH_BLACK_JACK, new BigDecimal("0")),
                Arguments.of(GameResult.DRAW, new BigDecimal("0")),
                Arguments.of(GameResult.LOSE, new BigDecimal("-1"))
        );
    }

}