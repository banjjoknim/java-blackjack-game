package domain.user.state;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SurvivalTest {

    private Survival survival;

    @BeforeEach
    void setUp() {
        survival = new Survival(new Cards());
    }

    @DisplayName("생존 상태에서 카드 뽑는 기능을 테스트 한다.")
    @Test
    void drawTest() {
        // given

        // when
        survival.draw(new Card(Symbol.DIAMOND, Type.KING));

        // then
        assertThat(survival.getCards().getCards()).hasSize(1);
    }

    @DisplayName("생존 상태에서 카드를 뽑고 난 이후의 상태 결정 기능을 테스트 한다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedState")
    void determineStateTest(List<Card> cards, Class expected) {
        // given
        cards.forEach(card -> survival.draw(card));

        // when
        State state = survival.determineState();

        // then
        assertThat(state).isInstanceOf(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedState() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new Card(Symbol.HEART, Type.SIX),
                                new Card(Symbol.HEART, Type.KING),
                                new Card(Symbol.HEART, Type.QUEEN)),
                        Bust.class
                ),
                Arguments.of(
                        Arrays.asList(
                                new Card(Symbol.HEART, Type.ACE),
                                new Card(Symbol.HEART, Type.KING)),
                        BlackJack.class
                ),
                Arguments.of(
                        Arrays.asList(new Card(Symbol.HEART, Type.SIX),
                                new Card(Symbol.HEART, Type.KING),
                                new Card(Symbol.HEART, Type.TWO)),
                        Survival.class
                )
        );
    }

}
