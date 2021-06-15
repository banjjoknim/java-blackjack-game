package domain.user.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BustTest {

    @DisplayName("버스트 상태 생성을 테스트한다.")
    @Test
    void 버스트_상태가_생성된다() {
        // given

        // when

        // then
        assertDoesNotThrow(Bust::new);
    }
}
