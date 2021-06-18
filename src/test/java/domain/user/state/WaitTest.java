package domain.user.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class WaitTest {

    @DisplayName("대기 상태 생성을 테스트한다.")
    @Test
    void 대기_상태가_생성된다() {
        // given

        // when

        // then
        assertDoesNotThrow(Wait::new);
    }

    @DisplayName("대기 상태일 때 다음 상태를 찾는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideScoreAndIsInitialCardsAndExpected")
    void 대기_상태에서_다음_상태를_찾는다(int score, boolean isInitialCards, Class expected) {
        // given
        State state = new Wait();

        // when
        State nextState = state.findNextState(score, isInitialCards);

        assertThat(nextState).isInstanceOf(expected);

    }

    private static Stream<Arguments> provideScoreAndIsInitialCardsAndExpected() {
        return Stream.of(
                Arguments.of(21, true, Blackjack.class),
                Arguments.of(21, false, Stay.class),
                Arguments.of(20, true, Wait.class),
                Arguments.of(20, false, Wait.class),
                Arguments.of(22, false, Bust.class)
        );
    }

    @DisplayName("대기 상태일 때 차례가 끝난 상태인지 여부를 검사하는 기능을 테스트한다.")
    @Test
    void 대기_상태는_차례가_끝난_상태가_아니다() {
        // given
        State state = new Wait();

        // when
        boolean isEnded = state.isEnded();

        // then
        assertThat(isEnded).isFalse();
    }
}
