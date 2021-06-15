package domain.user.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BlackjackTest {

    @DisplayName("블랙잭 상태 생성을 테스트한다.")
    @Test
    void 블랙잭_상태가_생성된다() {
        // given

        // when

        // then
        assertDoesNotThrow(Blackjack::new);
    }

    @DisplayName("블랙잭 상태일 때 다음 상태를 찾는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideScoreAndIsInitialCards")
    void 블랙잭_상태에서_다음_상태를_찾으면_예외가_발생한다(int score, boolean isInitialCards) {
        // given
        State state = new Blackjack();

        // when

        assertThatThrownBy(() -> state.findNextState(score, isInitialCards))
                .isInstanceOf(IllegalStateException.class);
    }

    private static Stream<Arguments> provideScoreAndIsInitialCards() {
        return Stream.of(
                Arguments.of(21, true),
                Arguments.of(21, false),
                Arguments.of(20, true),
                Arguments.of(20, false),
                Arguments.of(22, false)
        );
    }
}
