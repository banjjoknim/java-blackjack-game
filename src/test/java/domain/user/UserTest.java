package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @DisplayName("유저의 손패에 카드를 추가하는 기능을 테스트한다.")
    @Test
    void 유저의_손패에_카드가_추가된다() {
        // given
        Card card = new Card(Symbol.HEART, Type.ACE);

        // when
        User user = new Player(new Name("name"), new BettingMoney(new BigDecimal("1000")));
        user.draw(card);

        // then
        assertThat(user.getHand().getState().getCards()).hasSize(1);
    }

}
