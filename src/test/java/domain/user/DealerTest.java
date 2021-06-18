package domain.user;

import domain.card.Card;
import domain.card.Deck;
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

    @DisplayName("딜러가 카드덱으로부터 카드를 뽑는 기능을 테스트한다.")
    @Test
    void 딜러가_카드덱으로부터_카드를_뽑는다() {
        // given
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        // when
        dealer.hit(deck);

        // then
        assertAll(
                () -> assertThat(dealer.getHand().getCards()).hasSize(1),
                () -> assertThat(deck.getCards()).hasSize(51)
        );
    }

    @DisplayName("딜러가 플레이어인지 여부를 검사하는 기능을 테스트한다.")
    @Test
    void 딜러는_딜러이다() {
        // given
        Dealer dealer = new Dealer();

        // when
        boolean isPlayer = dealer.isPlayer();

        // then
        assertThat(isPlayer).isFalse();
    }

    @DisplayName("딜러가 딜러인지 여부를 검사하는 기능을 테스트한다.")
    @Test
    void 플레이어는_딜러가_아니다() {
        // given
        Dealer dealer = new Dealer();

        // when
        boolean isDealer = dealer.isDealer();

        // then
        assertThat(isDealer).isTrue();
    }
}
