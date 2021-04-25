package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @DisplayName("유저의 카드 추가 기능을 테스트한다.")
    @Test
    void addCardTest() {
        // given
        User user = new User();

        // when
        user.addCard(new Card(Symbol.DIAMOND, Type.ACE));

        // then
        assertThat(user.getCards()).hasSize(1);
    }

    @DisplayName("유저의 카드 합 계산 기능을 테스트한다.")
    @Test
    void calculateSumOfCardNumbersTest() {
        // given
        User user = new User();
        user.addCard(new Card(Symbol.SPADE, Type.KING));
        user.addCard(new Card(Symbol.HEART, Type.KING));
        user.addCard(new Card(Symbol.CLUB, Type.KING));

        // when
        int sumOfCardNumbers = user.calculateSumOfCardNumbers();

        // then
        assertThat(sumOfCardNumbers).isEqualTo(30);

    }

    @DisplayName("유저의 카드 합이 블랙잭인 경우를 테스트한다.")
    @Test
    void calculateSumOfCardNumbersWhenBlackJackTest() {
        // given
        User user = new User();
        user.addCard(new Card(Symbol.SPADE, Type.ACE));
        user.addCard(new Card(Symbol.SPADE, Type.KING));

        // when
        int sumOfCardNumbers = user.calculateSumOfCardNumbers();

        // then
        assertThat(sumOfCardNumbers).isEqualTo(21);
    }

    @DisplayName("유저의 카드 합이 21이하일 경우 Bust 되었는지 판단하는 기능을 테스트한다.")
    @Test
    void determineIsBustWhenSumLessThanBlackJackTest() {
        // given
        User user = new User();

        // when
        boolean isBust = user.isBust();

        // then
        assertThat(isBust).isFalse();
    }

    @DisplayName("유저의 카드 합이 21을 초과할 경우 Bust 되었는지 판단하는 기능을 테스트한다.")
    @Test
    void determineIsBustWhenSumExceededBlackJackTest() {
        // given
        User user = new User();
        user.addCard(new Card(Symbol.SPADE, Type.KING));
        user.addCard(new Card(Symbol.HEART, Type.KING));
        user.addCard(new Card(Symbol.CLUB, Type.KING));

        // when
        boolean isBust = user.isBust();

        // then
        assertThat(isBust).isTrue();
    }

}