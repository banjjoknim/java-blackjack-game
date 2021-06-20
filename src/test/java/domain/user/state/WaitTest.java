package domain.user.state;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class WaitTest {

    private List<Card> cards;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>();
    }

    @DisplayName("대기 상태 생성을 테스트한다.")
    @Test
    void 대기_상태가_생성된다() {
        // given

        // when

        // then
        assertDoesNotThrow(() -> new Wait(cards));
    }

    @DisplayName("대기 상태일 때 다음 상태를 찾는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpected")
    void 대기_상태에서_다음_상태를_찾는다(List<Card> cards, Class expected) {
        // given
        State state = new Wait(this.cards);
        cards.forEach(state::add);

        // when
        State nextState = state.findNextState();

        assertThat(nextState).isInstanceOf(expected);
    }

    private static Stream<Arguments> provideCardsAndExpected() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                Card.of(Symbol.SPADE, Type.KING),
                                Card.of(Symbol.SPADE, Type.JACK)
                        ),
                        Wait.class),
                Arguments.of(
                        Arrays.asList(
                                Card.of(Symbol.SPADE, Type.KING),
                                Card.of(Symbol.SPADE, Type.ACE)
                        ),
                        Blackjack.class),
                Arguments.of(
                        Arrays.asList(
                                Card.of(Symbol.SPADE, Type.KING),
                                Card.of(Symbol.SPADE, Type.QUEEN),
                                Card.of(Symbol.SPADE, Type.TWO)
                        ),
                        Bust.class),
                Arguments.of(
                        Arrays.asList(
                                Card.of(Symbol.SPADE, Type.KING),
                                Card.of(Symbol.SPADE, Type.QUEEN),
                                Card.of(Symbol.SPADE, Type.ACE)
                        ),
                        Stay.class)
        );
    }

    @DisplayName("대기 상태일 때 차례가 끝난 상태인지 여부를 검사하는 기능을 테스트한다.")
    @Test
    void 대기_상태는_차례가_끝난_상태가_아니다() {
        // given
        State state = new Wait(cards);

        // when
        boolean isEnded = state.isEnded();

        // then
        assertThat(isEnded).isFalse();
    }

    @DisplayName("대기에서 스테이 상태를 반환할 경우를 테스트한다.")
    @Test
    void 대기_상태에서_스테이_상태를_반환한다() {
        // given
        State state = new Wait(cards);

        // when
        State actualState = state.toStay();

        // then
        assertThat(actualState).isInstanceOf(Stay.class);
    }
}
