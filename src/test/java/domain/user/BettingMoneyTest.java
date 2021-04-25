package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

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
    
}