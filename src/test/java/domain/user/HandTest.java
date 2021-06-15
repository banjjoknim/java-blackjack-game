package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    @DisplayName("손패에 카드를 추가하는 기능을 테스트한다.")
    @Test
    void 손패에_카드가_추가된다() {
        // given
        Card card = new Card(Symbol.SPADE, Type.ACE);
        Hand hand = new Hand();

        // when
        hand.addCard(card);

        // then
        assertThat(hand.getCards()).hasSize(1);
    }

    @DisplayName("손패의 상태를 결정하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpected")
    void 손패의_상태를_결정한다(List<Card> cards, Class expected) {
        // given
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.addCard(card);
        }

        // when
        hand.changeState();

        // then
        assertThat(hand.getState()).isInstanceOf(expected);
    }

    private static Stream<Arguments> provideCardsAndExpected() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Card(Symbol.DIAMOND, Type.SIX), new Card(Symbol.DIAMOND, Type.KING), new Card(Symbol.SPADE, Type.JACK)), Bust.class),
                Arguments.of(Arrays.asList(new Card(Symbol.DIAMOND, Type.ACE), new Card(Symbol.DIAMOND, Type.KING)), Blackjack.class),
                Arguments.of(Arrays.asList(new Card(Symbol.DIAMOND, Type.ACE), new Card(Symbol.DIAMOND, Type.KING), new Card(Symbol.SPADE, Type.JACK)), Stay.class),
                Arguments.of(Arrays.asList(new Card(Symbol.DIAMOND, Type.ACE), new Card(Symbol.DIAMOND, Type.SIX)), Wait.class)
        );
    }
}
