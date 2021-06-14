package domain.user.state;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BustTest {

    private Bust bust;

    @BeforeEach
    void setUp() {
        bust = new Bust(new Cards());
    }

    @DisplayName("버스트 상태에서 카드를 뽑을 경우 예외 발생을 테스트 한다.")
    @Test
    void drawTest() {
        // given

        // when

        // then
        assertThatThrownBy(() -> bust.draw(new Card(Symbol.DIAMOND, Type.KING)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("차례가 끝난 상태에서는 카드를 더 뽑을 수 없습니다.");
    }

    @DisplayName("버스트 상태에서 이후의 상태를 결정하려고 할 경우 예외 발생을 테스트 한다.")
    @Test
    void determineStateTest() {
        // given

        // when

        // then
        assertThatThrownBy(() -> bust.determineState())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("차례가 끝난 이후의 상태는 결정할 수 없습니다.");
    }
}
