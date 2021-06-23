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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    private static final Card HEART_KING = Card.of(Symbol.HEART, Type.KING);
    private static final Card HEART_QUEEN = Card.of(Symbol.HEART, Type.QUEEN);
    private static final Card HEART_ACE = Card.of(Symbol.HEART, Type.ACE);
    private static final Card HEART_NINE = Card.of(Symbol.HEART, Type.NINE);

    @DisplayName("유저가 카드를 뽑는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndCount")
    void 유저가_카드를_뽑으면_가진_카드의_수가_증가하고_상태가_변한다(List<Card> cards, int count, Class expected) {
        // given

        // when
        User user = new Player(new Name("name"), new BettingMoney(new BigDecimal("1000")));
        cards.forEach(user::draw);
        List<Card> actualCards = user.getState().getCards();
        State actualState = user.getState();

        // then
        assertThat(actualCards).hasSize(count);
        assertThat(actualState).isInstanceOf(expected);
    }

    private static Stream<Arguments> provideCardsAndCount() {
        return Stream.of(
                Arguments.of(Arrays.asList(HEART_KING, HEART_ACE), 2, Blackjack.class),
                Arguments.of(Arrays.asList(HEART_KING, HEART_QUEEN, HEART_ACE), 3, Stay.class),
                Arguments.of(Arrays.asList(HEART_KING, HEART_QUEEN, HEART_NINE), 3, Bust.class),
                Arguments.of(Arrays.asList(HEART_KING, HEART_NINE), 2, Wait.class)
        );
    }

}
