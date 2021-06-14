package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

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
}
