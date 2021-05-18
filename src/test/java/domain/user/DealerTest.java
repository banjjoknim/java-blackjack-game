package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("딜러의 카드 숫자 합이 규칙에 명시된 숫자보다 클 경우, Stay 여부를 테스트한다.")
    @Test
    void isStayWhenHasNotSmallNumberLessThanRuleNumberTest() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.SPADE, Type.KING));
        dealer.addCard(new Card(Symbol.HEART, Type.KING));

        // when
        boolean isStay = dealer.isStay();

        // then
        assertThat(isStay).isTrue();
    }

    @DisplayName("딜러의 카드 숫자 합이 규칙에 명시된 숫자보다 작을 경우, Stay 여부를 테스트한다.")
    @Test
    void isStayWhenHasSmallNumberLessThanRuleNumberTest() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.SPADE, Type.KING));
        dealer.addCard(new Card(Symbol.HEART, Type.THREE));

        // when
        boolean isStay = dealer.isStay();

        // then
        assertThat(isStay).isFalse();
    }

    @DisplayName("딜러의 카드 추가 기능을 테스트한다.")
    @Test
    void addCardTest() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.addCard(new Card(Symbol.DIAMOND, Type.ACE));

        // then
        assertThat(dealer.getCards()).hasSize(1);
    }

    @DisplayName("딜러의 카드 합 계산 기능을 테스트한다.")
    @Test
    void calculateSumOfCardNumbersTest() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.SPADE, Type.KING));
        dealer.addCard(new Card(Symbol.HEART, Type.KING));
        dealer.addCard(new Card(Symbol.CLUB, Type.KING));

        // when
        int totalCardNumber = dealer.calculateTotalCardNumber();

        // then
        assertThat(totalCardNumber).isEqualTo(30);

    }

    @DisplayName("딜러의 상태 결정 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndStatus")
    void determineStatusTest(Card firstCard, Card secondCard, Card thirdCard, Status status) {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(firstCard);
        dealer.addCard(secondCard);
        if (thirdCard != null) {
            dealer.addCard(thirdCard);
        }

        // when
        Status playerStatus = dealer.getStatus();

        // then
        assertThat(playerStatus).isEqualTo(status);
    }

    private static Stream<Arguments> provideCardsAndStatus() {
        return Stream.of(
                Arguments.of(new Card(Symbol.SPADE, Type.TWO), new Card(Symbol.HEART, Type.KING), new Card(Symbol.HEART, Type.KING), Status.BUST),
                Arguments.of(new Card(Symbol.SPADE, Type.ACE), new Card(Symbol.HEART, Type.KING), null, Status.BLACK_JACK),
                Arguments.of(new Card(Symbol.SPADE, Type.ACE), new Card(Symbol.HEART, Type.KING), new Card(Symbol.HEART, Type.KING), Status.SURVIVAL)
        );
    }

}
