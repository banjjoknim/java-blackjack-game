package domain.card;

import domain.user.Hand;
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

    @DisplayName("손패의 총 점수를 계산하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndScore")
    void 손패의_총_점수를_계산한다(List<Card> cards, int expected) {
        // given

        // when
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.addCard(card);
        }
        int score = hand.calculateScore();

        // then
        assertThat(score).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndScore() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Card(Symbol.HEART, Type.SIX), new Card(Symbol.DIAMOND, Type.JACK)), 16),
                Arguments.of(Arrays.asList(new Card(Symbol.HEART, Type.ACE), new Card(Symbol.DIAMOND, Type.SIX)), 17),
                Arguments.of(Arrays.asList(new Card(Symbol.HEART, Type.EIGHT), new Card(Symbol.DIAMOND, Type.JACK)), 18),
                Arguments.of(Arrays.asList(new Card(Symbol.HEART, Type.NINE), new Card(Symbol.DIAMOND, Type.JACK)), 19),
                Arguments.of(Arrays.asList(new Card(Symbol.HEART, Type.TEN), new Card(Symbol.DIAMOND, Type.JACK)), 20),
                Arguments.of(Arrays.asList(new Card(Symbol.HEART, Type.KING), new Card(Symbol.DIAMOND, Type.JACK),
                        new Card(Symbol.SPADE, Type.ACE)), 21)
        );
    }
}
