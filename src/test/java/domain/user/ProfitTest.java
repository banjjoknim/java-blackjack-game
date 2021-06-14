package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProfitTest {

    @DisplayName("Profit 생성을 테스트 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "2000", "0", "-2000"})
    void createProfitTest(String input) {
        // given
        BigDecimal amount = new BigDecimal(input);

        // when

        // then
        assertDoesNotThrow(() -> new Profit(amount));
    }

    @DisplayName("Profit 의 덧셈 기능을 테스트 한다.")
    @Test
    void addProfitTest() {
        // given
        Profit profit = new Profit(new BigDecimal("1000"));

        // when
        Profit addedProfit = profit.addProfit(new Profit(new BigDecimal("500")));

        // then
        assertThat(addedProfit.getAmount()).isEqualTo(new Profit(new BigDecimal("1500")).getAmount());
    }

    @DisplayName("Profit 의 부호 반전 기능을 테스트 한다.")
    @Test
    void negateTest() {
        // given
        Profit profit = new Profit(new BigDecimal("1000"));

        // when
        Profit negatedProfit = profit.negate();

        // then
        assertThat(negatedProfit.getAmount()).isEqualTo(new Profit(new BigDecimal("-1000")).getAmount());
    }
}
