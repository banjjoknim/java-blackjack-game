package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CardTest {

    @DisplayName("Symbol 과 Type 으로 Card 생성을 테스트 한다.")
    @ParameterizedTest
    @EnumSource(value = Symbol.class)
    void createCardTest(Symbol symbol) {
        // given
        Type[] types = Type.values();

        for (Type type : types) {

            // when
            Card card = new Card(symbol, type);

            // then
            assertAll(
                    () -> assertThat(card).isInstanceOf(Card.class),
                    () -> assertThat(card.getSymbol()).isEqualTo(symbol),
                    () -> assertThat(card.getType()).isEqualTo(type)
            );
        }
    }

    @DisplayName("Card 의 Type 이 ACE 인지 판단하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCardAndExpectedResult")
    void isAceTypeTest(Card card, boolean expectedResult) {
        // given

        // when
        boolean isAceType = card.isAceType();

        // then
        assertThat(isAceType).isEqualTo(expectedResult);

    }

    private static Stream<Arguments> provideCardAndExpectedResult() {
        return Stream.of(
                Arguments.of(new Card(Symbol.SPADE, Type.ACE), true),
                Arguments.of(new Card(Symbol.HEART, Type.ACE), true),
                Arguments.of(new Card(Symbol.DIAMOND, Type.ACE), true),
                Arguments.of(new Card(Symbol.CLUB, Type.ACE), true),
                Arguments.of(new Card(Symbol.DIAMOND, Type.KING), false),
                Arguments.of(new Card(Symbol.DIAMOND, Type.QUEEN), false),
                Arguments.of(new Card(Symbol.DIAMOND, Type.JACK), false),
                Arguments.of(new Card(Symbol.DIAMOND, Type.TEN), false)
        );
    }


}