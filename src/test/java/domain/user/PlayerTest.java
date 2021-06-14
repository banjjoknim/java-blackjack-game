package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayerTest {

    @DisplayName("플레이어 생성을 테스트한다.")
    @Test
    void 입력한_값으로_플레이어가_생성된다() {
        // given
        Name name = new Name("name");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal("1000"));

        // when

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> new Player(name, bettingMoney)),
                () -> assertThat(new Player(name, bettingMoney).getName()).isEqualTo(name),
                () -> assertThat(new Player(name, bettingMoney).getBettingMoney()).isEqualTo(bettingMoney)
        );
    }

}
