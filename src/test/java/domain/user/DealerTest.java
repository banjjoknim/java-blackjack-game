package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

    @DisplayName("딜러가 대기 상태인지 검증하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpected")
    void 딜러가_대기_상태인지_검증한다(List<Card> cards, boolean expected) {
        // given
        Dealer dealer = new Dealer();
        cards.forEach(dealer::draw);

        // when
        boolean isWait = dealer.isWait();

        // then
        assertThat(isWait).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpected() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Card(Symbol.SPADE, Type.KING), new Card(Symbol.SPADE, Type.ACE)), false),
                Arguments.of(Arrays.asList(new Card(Symbol.SPADE, Type.KING), new Card(Symbol.SPADE, Type.QUEEN)), false),
                Arguments.of(Arrays.asList(new Card(Symbol.SPADE, Type.KING), new Card(Symbol.SPADE, Type.FIVE)), true),
                Arguments.of(Arrays.asList(new Card(Symbol.SPADE, Type.KING), new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.SPADE, Type.THREE)), false),
                Arguments.of(Arrays.asList(new Card(Symbol.SPADE, Type.KING), new Card(Symbol.SPADE, Type.SEVEN)), false)
        );
    }
}
