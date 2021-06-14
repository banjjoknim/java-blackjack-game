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

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayerTest {

    @DisplayName("플레이어 생성을 테스트한다.")
    @Test
    void createPlayerTest() {
        // given
        UserName userName = new UserName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));

        // when
        Player player = new Player(userName, bettingMoney);

        // then
        assertAll(
                () -> assertThat(player.getBettingMoney()).isEqualTo(bettingMoney),
                () -> assertThat(player.getUserName()).isEqualTo(userName)
        );
    }

    @DisplayName("플레이어의 카드 추가 기능을 테스트한다.")
    @Test
    void drawTest() {
        // given
        UserName userName = new UserName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));
        Player player = new Player(userName, bettingMoney);

        // when
        player.draw(new Card(Symbol.DIAMOND, Type.ACE));

        // then
        assertThat(player.getState().getCards().getCards()).hasSize(1);
    }

    @DisplayName("플레이어의 카드 뽑기 기능을 테스트한다.")
    @Test
    void hitTest() {
        // given
        Deck deck = new Deck();
        UserName userName = new UserName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));
        Player player = new Player(userName, bettingMoney);

        // when
        player.hit(deck);

        // then
        assertThat(player.getState().getCards().getCards()).hasSize(1);
    }

    @DisplayName("플레이어가 자신의 턴을 수행하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideAnswerAndCardsSize")
    void proceedOwnTurnTest(String answer, int cardsSize) {
        // given
        UserName userName = new UserName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));
        Player player = new Player(userName, bettingMoney);
        Deck deck = new Deck();

        // when
        player.proceedOwnTurn(answer, deck);

        // then
        assertThat(player.getState().getCards().getCards()).hasSize(cardsSize);
    }

    private static Stream<Arguments> provideAnswerAndCardsSize() {
        return Stream.of(
                Arguments.of("y", 1),
                Arguments.of("n", 0)
        );
    }

    @DisplayName("플레이어의 최종 수익 계산 기능을 테스트한다.")
    @Test
    void calculateFinalProfitTest() {
        // given
        Player player = new Player(new UserName("player"), new BettingMoney(new BigDecimal(1000)));
        player.draw(new Card(Symbol.SPADE, Type.KING));
        player.draw(new Card(Symbol.HEART, Type.KING));
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Symbol.SPADE, Type.KING));
        dealer.draw(new Card(Symbol.HEART, Type.SIX));

        // when
        Profit finalProfit = player.calculateFinalProfit(dealer);

        // then
        assertThat(finalProfit).isEqualTo(new Profit(new BigDecimal("1000")));
    }

}
