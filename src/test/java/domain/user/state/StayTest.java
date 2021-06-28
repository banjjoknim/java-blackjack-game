package domain.user.state;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.GameResult;
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

class StayTest {

    private List<Card> cards = new ArrayList<>();
    
    @DisplayName("스테이 상태 생성을 테스트한다.")
    @Test
    void 스테이_상태가_생성된다() {
        // given

        // when

        // then
        assertDoesNotThrow(() -> new Stay(cards));
    }

    @DisplayName("스테이 상태일 때 다음 상태를 찾는 기능을 테스트한다.")
    @Test
    void 스테이_상태에서_다음_상태를_찾으면_예외가_발생한다() {
        // given
        State state = new Stay(cards);

        // when

        assertThatThrownBy(state::findNextState)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("스테이 상태일 때 차례가 끝난 상태인지 여부를 검사하는 기능을 테스트한다.")
    @Test
    void 스테이_상태는_차례가_끝난_상태이다() {
        // given
        State state = new Stay(cards);

        // when
        boolean isEnded = state.isEnded();

        // then
        assertThat(isEnded).isTrue();
    }

    @DisplayName("차례가 끝난 상태에서 스테이 상태를 반환할 경우를 테스트한다.")
    @Test
    void 스테이_상태에서_스테이_상태를_반환하려고_할때_예외가_발생한다() {
        // given
        State state = new Stay(cards);

        // when

        // then
        assertThatThrownBy(state::toStay).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("현재 상태가 블랙잭 상태인지 아닌지 검사하는 기능을 테스트한다.")
    @Test
    void 스테이_상태는_블랙잭_상태가_아니다() {
        // given
        State state = new Stay(new ArrayList<>());

        // when
        boolean isBlackjack = state.isBlackjack();

        // then
        assertThat(isBlackjack).isFalse();
    }

    @DisplayName("현재 상태가 버스트 상태인지 아닌지 검사하는 기능을 테스트한다.")
    @Test
    void 스테이_상태는_버스트_상태가_아니다() {
        // given
        State state = new Stay(new ArrayList<>());

        // when
        boolean isBust = state.isBust();

        // then
        assertThat(isBust).isFalse();
    }

    @DisplayName("현재 상태가 스테이 상태인지 아닌지 검사하는 기능을 테스트한다.")
    @Test
    void 스테이_상태는_스테이_상태이다() {
        // given
        State state = new Stay(new ArrayList<>());

        // when
        boolean isStay = state.isStay();

        // then
        assertThat(isStay).isTrue();
    }

    @DisplayName("스테이 상태일 때 결과를 찾는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideStateAndGameResult")
    void 스테이_상태일_때_결과를_결정한다(State state, GameResult expected) {
        // given
        State stay = new Stay(Arrays.asList(Card.of(Symbol.HEART, Type.KING), Card.of(Symbol.HEART, Type.EIGHT)));

        // when
        GameResult gameResult = stay.findResult(state);

        // then
        assertThat(gameResult).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStateAndGameResult() {
        return Stream.of(
                Arguments.of(new Bust(new ArrayList<>()), GameResult.WIN),
                Arguments.of(new Blackjack(new ArrayList<>()), GameResult.LOSE),
                Arguments.of(new Stay(Arrays.asList(Card.of(Symbol.SPADE, Type.KING), Card.of(Symbol.SPADE, Type.SEVEN))),
                        GameResult.WIN),
                Arguments.of(new Stay(Arrays.asList(Card.of(Symbol.SPADE, Type.KING), Card.of(Symbol.SPADE, Type.EIGHT))),
                        GameResult.DRAW),
                Arguments.of(new Stay(Arrays.asList(Card.of(Symbol.SPADE, Type.KING), Card.of(Symbol.SPADE, Type.NINE))),
                        GameResult.LOSE)
        );
    }
}
