package domain.user;

import domain.result.EarningRate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

    private static final BettingMoney THOUSAND = new BettingMoney(BigDecimal.valueOf(1000));

    @DisplayName("배팅 금액이 0과 같거나 작을 경우 예외 처리를 테스트한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1"})
    void 배팅_금액이_0과_같거나_작을_경우_예외가_발생한다(String input) {
        // given
        BigDecimal amount = new BigDecimal(input);

        // when

        // then
        assertThatThrownBy(() -> new BettingMoney(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("올바른 배팅 금액을 입력했을 경우 BettingMoney 생성을 테스트한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "100", "1000"})
    void 올바른_배팅_금액을_입력했을_경우_BettingMoney가_생성된다(String input) {
        // given
        BigDecimal amount = new BigDecimal(input);

        // when
        BettingMoney bettingMoney = new BettingMoney(amount);

        // then
        assertThat(bettingMoney.getAmount()).isEqualTo(amount);
    }

    @DisplayName("수익률과 배팅금으로 수익을 계산하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideBettingMoneyAndEarningRate")
    void 수익률과_배팅금으로_수익을_계산한다(BettingMoney bettingMoney, EarningRate earningRate, Profit expected) {
        // given

        // when
        Profit profit = bettingMoney.produceProfit(earningRate);

        // then
        assertThat(profit).isEqualTo(expected);
    }

    private static Stream<Arguments> provideBettingMoneyAndEarningRate() {
        return Stream.of(
                Arguments.of(THOUSAND, EarningRate.ONE_POINT_FIVE, new Profit(BigDecimal.valueOf(1500))),
                Arguments.of(THOUSAND, EarningRate.ONE, new Profit(BigDecimal.valueOf(1000))),
                Arguments.of(THOUSAND, EarningRate.ZERO, new Profit(BigDecimal.valueOf(0))),
                Arguments.of(THOUSAND, EarningRate.MINUS_ONE, new Profit(BigDecimal.valueOf(-1000)))
        );
    }

}
