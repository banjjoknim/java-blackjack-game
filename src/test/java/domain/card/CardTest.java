package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

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

}