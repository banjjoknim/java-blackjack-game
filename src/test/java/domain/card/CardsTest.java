package domain.card;

import domain.user.BettingMoney;
import domain.user.Player;
import domain.user.UserName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @DisplayName("Cards의 카드 합 계산 기능을 테스트한다.")
    @Test
    void calculateTotalCardNumberTest() {
        // given
        Cards cards = new Cards();
        cards.addCard(new Card(Symbol.SPADE, Type.KING));
        cards.addCard(new Card(Symbol.HEART, Type.KING));
        cards.addCard(new Card(Symbol.DIAMOND, Type.KING));

        // when
        int totalCardNumber = cards.calculateTotalCardNumber();

        // then
        assertThat(totalCardNumber).isEqualTo(30);

    }

}
