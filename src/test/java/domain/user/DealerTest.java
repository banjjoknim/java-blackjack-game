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
        dealer.draw(new Card(Symbol.SPADE, Type.KING));
        dealer.draw(new Card(Symbol.HEART, Type.KING));

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
        dealer.draw(new Card(Symbol.SPADE, Type.KING));
        dealer.draw(new Card(Symbol.HEART, Type.THREE));

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
        dealer.draw(new Card(Symbol.DIAMOND, Type.ACE));

        // then
        assertThat(dealer.getState().getCards().getCards()).hasSize(1);
    }

}
