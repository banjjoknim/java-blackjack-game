package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("Dealer 의 카드 숫자 합이 규칙에 명시된 숫자보다 크거나 같은지 검증하는 기능을 테스트한다.")
    @Test
    void hasSmallNumberLessThanRuleNumberTest() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.SPADE, Type.KING));
        dealer.addCard(new Card(Symbol.HEART, Type.KING));

        // when
        boolean hasSmallNumberLessThanRuleNumber = dealer.hasSmallNumberLessThanRuleNumber();

        // then
        assertThat(hasSmallNumberLessThanRuleNumber).isFalse();
    }

}