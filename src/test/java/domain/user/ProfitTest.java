package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

}
