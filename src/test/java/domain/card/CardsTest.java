package domain.card;

import domain.user.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @DisplayName("딜러의 카드 합 계산 기능을 테스트한다.")
    @Test
    void calculateSumOfCardNumbersTest() {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Symbol.SPADE, Type.KING));
        dealer.draw(new Card(Symbol.HEART, Type.KING));
        dealer.draw(new Card(Symbol.CLUB, Type.KING));

        // when
        int totalCardNumber = dealer.getCards().calculateTotalCardNumber();

        // then
        assertThat(totalCardNumber).isEqualTo(30);

    }

    @DisplayName("딜러의 상태 결정 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndStatus")
    void determineStatusTest(Card firstCard, Card secondCard, Card thirdCard, Status status) {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(firstCard);
        dealer.draw(secondCard);
        if (thirdCard != null) {
            dealer.draw(thirdCard);
        }

        // when
        Status playerStatus = dealer.getCards().getStatus();

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

    @DisplayName("플레이어의 카드 조합이 블랙잭이 아닌 경우 카드 합 계산 기능을 테스트한다.")
    @Test
    void calculateTotalCardNumberWhenIsNotBlackJackTest() {
        // given
        UserName userName = new UserName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));
        Player player = new Player(userName, bettingMoney);
        player.draw(new Card(Symbol.SPADE, Type.KING));
        player.draw(new Card(Symbol.HEART, Type.KING));
        player.draw(new Card(Symbol.CLUB, Type.KING));

        // when
        int totalCardNumber = player.getCards().calculateTotalCardNumber();

        // then
        assertThat(totalCardNumber).isEqualTo(30);
    }

    @DisplayName("플레이어의 카드 조합이 블랙잭인 경우 카드 합 계산 기능을 테스트한다.")
    @Test
    void calculateTotalCardNumberWhenIsBlackJackTest() {
        // given
        UserName userName = new UserName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));
        Player player = new Player(userName, bettingMoney);
        player.draw(new Card(Symbol.SPADE, Type.ACE));
        player.draw(new Card(Symbol.SPADE, Type.KING));

        // when
        int actual = player.getCards().calculateTotalCardNumber();

        // then
        assertThat(actual).isEqualTo(21);
    }

}