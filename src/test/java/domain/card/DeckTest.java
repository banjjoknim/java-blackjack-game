package domain.card;

import domain.user.BettingMoney;
import domain.user.Name;
import domain.user.Player;
import domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DeckTest {

    @DisplayName("유저에게 카드를 나눠주는 기능을 테스트한다.")
    @Test
    void 유저에게_카드를_나눠준다() {
        // given
        Deck deck = new Deck();

        // when
        User user = new Player(new Name("name"), new BettingMoney(new BigDecimal("1000")));
        deck.distributeCard(user);

        // then
        assertAll(
                ()->assertThat(user.getHand().getState().getCards()).hasSize(1),
                ()->assertThat(deck.getCards()).hasSize(51)
        );
    }
}
