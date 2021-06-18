package domain.user.state;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BustTest {

    private List<Card> cards = new ArrayList<>();

    @DisplayName("버스트 상태 생성을 테스트한다.")
    @Test
    void 버스트_상태가_생성된다() {
        // given

        // when

        // then
        assertDoesNotThrow(() -> new Bust(cards));
    }

    @DisplayName("버스트 상태일 때 다음 상태를 찾는 기능을 테스트한다.")
    @Test
    void 버스트_상태에서_다음_상태를_찾으면_예외가_발생한다() {
        // given
        State state = new Bust(cards);

        // when

        assertThatThrownBy(state::findNextState)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("버스트 상태일 때 차례가 끝난 상태인지 여부를 검사하는 기능을 테스트한다.")
    @Test
    void 버스트_상태는_차례가_끝난_상태이다() {
        // given
        State state = new Bust(cards);

        // when
        boolean isEnded = state.isEnded();

        // then
        assertThat(isEnded).isTrue();
    }
}
