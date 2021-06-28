package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.state.*;
import org.junit.jupiter.api.BeforeEach;
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
import static org.junit.jupiter.api.Assertions.assertAll;

class UserTest {
    private static final Card HEART_KING = Card.of(Symbol.HEART, Type.KING);
    private static final Card HEART_QUEEN = Card.of(Symbol.HEART, Type.QUEEN);
    private static final Card HEART_ACE = Card.of(Symbol.HEART, Type.ACE);
    private static final Card HEART_NINE = Card.of(Symbol.HEART, Type.NINE);

    private User user;

    @BeforeEach
    void setUp() {
        user = new Player(new Name("name"), new BettingMoney(BigDecimal.valueOf(1000)));
    }

    @DisplayName("유저가 카드를 뽑는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndSizeAndState")
    void 유저가_카드를_뽑으면_가진_카드의_수가_증가하고_상태가_변한다(List<Card> cards, int expectedSize, Class expectedState) {
        // given

        // when
        cards.forEach(user::draw);
        List<Card> actualCards = user.getState().getCards();
        State actualState = user.getState();

        // then
        assertAll(
                () -> assertThat(actualCards).hasSize(expectedSize),
                () -> assertThat(actualState).isInstanceOf(expectedState)
        );
    }

    private static Stream<Arguments> provideCardsAndSizeAndState() {
        return Stream.of(
                Arguments.of(Arrays.asList(HEART_KING, HEART_ACE), 2, Blackjack.class),
                Arguments.of(Arrays.asList(HEART_KING, HEART_QUEEN, HEART_ACE), 3, Stay.class),
                Arguments.of(Arrays.asList(HEART_KING, HEART_QUEEN, HEART_NINE), 3, Bust.class),
                Arguments.of(Arrays.asList(HEART_KING, HEART_NINE), 2, Wait.class)
        );
    }

    @DisplayName("유저의 점수를 얻는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndScore")
    void 유저의_점수를_얻는다(List<Card> cards, Score expectedScore) {
        // given

        // when
        cards.forEach(user::draw);
        Score actualScore = user.getScore();

        // then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> provideCardsAndScore() {
        return Stream.of(
                Arguments.of(Arrays.asList(HEART_KING, HEART_ACE), new Score(21)),
                Arguments.of(Arrays.asList(HEART_KING, HEART_QUEEN, HEART_ACE), new Score(21)),
                Arguments.of(Arrays.asList(HEART_KING, HEART_QUEEN, HEART_NINE), new Score(29)),
                Arguments.of(Arrays.asList(HEART_KING, HEART_NINE), new Score(19))
        );
    }

}
