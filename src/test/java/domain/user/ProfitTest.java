package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {

    @DisplayName("수익 생성을 테스트한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    void 수익이_생성된다(int amount) {
        // given
        Profit profit = new Profit(BigDecimal.valueOf(amount));

        // when
        BigDecimal actual = profit.getAmount();

        // then
        assertThat(actual).isEqualTo(BigDecimal.valueOf(amount));
    }

    @DisplayName("수익을 더하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideProfit")
    void 두_수익을_더하면_두_수익의_합계를_액수로_갖는_수익이_생성된다(Profit providedProfit, Profit expected) {
        // given
        Profit profit = new Profit(BigDecimal.valueOf(1000));

        // when
        Profit actual = profit.add(providedProfit);


        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideProfit() {
        return Stream.of(
                Arguments.of(new Profit(BigDecimal.valueOf(1000)), new Profit(BigDecimal.valueOf(2000))),
                Arguments.of(new Profit(BigDecimal.valueOf(-1000)), new Profit(BigDecimal.valueOf(0)))
        );
    }

    @DisplayName("수익의 부호를 반전시키는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideAmount")
    void 수익의_부호를_반전시키면_해당_수익과_액수의_양은_같고_반대부호를_가진_수익이_생성된다(BigDecimal amount, Profit expected) {
        // given
        Profit profit = new Profit(amount);

        // when
        Profit actual = profit.negate();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAmount() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(1000), new Profit(BigDecimal.valueOf(-1000))),
                Arguments.of(BigDecimal.valueOf(-1000), new Profit(BigDecimal.valueOf(1000))),
                Arguments.of(BigDecimal.ZERO, new Profit(BigDecimal.ZERO))
        );
    }

}
