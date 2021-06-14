package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @DisplayName("유저의 패에 카드를 추가하는 기능을 테스트한다.")
    @Test
    void 유저의_패에_카드가_추가된다() {
        // given
        Card card = new Card(Symbol.HEART, Type.ACE);

        // when
        User user = new Player(new Name("name"), new BettingMoney(new BigDecimal("1000")));
        user.addCard(card);

        // then
        assertThat(user.getCards()).hasSize(1);
    }

    @DisplayName("유저의 패로 총 점수를 계산하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndScore")
    void 유저의_패로_총_점수를_계산한다(List<Card> cards, int expected) {
        // given

        // when
        User user = new Player(new Name("name"), new BettingMoney(new BigDecimal("1000")));
        for (Card card : cards) {
            user.addCard(card);
        }
        int score = user.calculateScore();

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
