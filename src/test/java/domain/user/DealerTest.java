package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DealerTest {

    @DisplayName("딜러 생성을 테스트한다.")
    @Test
    void 딜러가_생성된다() {
        // given

        // when

        // then
        assertAll(
                () -> assertDoesNotThrow(Dealer::new),
                () -> assertThat(new Dealer().getHand().getCards()).hasSize(0)
        );
    }
}
