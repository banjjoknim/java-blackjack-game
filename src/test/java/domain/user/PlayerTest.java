package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @DisplayName("플레이어 생성을 테스트한다.")
    @Test
    void createPlayerTest() {
        // given
        PlayerName playerName = new PlayerName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));

        // when
        Player player = new Player(playerName, bettingMoney);

        // then
        assertAll(
                () -> assertThat(player).isInstanceOf(Player.class),
                () -> assertThat(player.getBettingMoney()).isEqualTo(bettingMoney),
                () -> assertThat(player.getPlayerName()).isEqualTo(playerName)
        );
    }

    @DisplayName("플레이어의 카드 추가 기능을 테스트한다.")
    @Test
    void addCardTest() {
        // given
        PlayerName playerName = new PlayerName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));
        Player player = new Player(playerName, bettingMoney);

        // when
        player.addCard(new Card(Symbol.DIAMOND, Type.ACE));

        // then
        assertThat(player.getCards()).hasSize(1);
    }

    @DisplayName("플레이어의 카드 조합이 블랙잭인지 판단하는 기능을 테스트한다.")
    @Test
    void isBlackJackTest() {
        // given
        PlayerName playerName = new PlayerName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));
        Player player = new Player(playerName, bettingMoney);
        player.addCard(new Card(Symbol.SPADE, Type.ACE));
        player.addCard(new Card(Symbol.SPADE, Type.KING));

        // when

        // then
        assertThat(player.isBlackJack()).isTrue();
    }

    @DisplayName("플레이어의 카드 조합이 블랙잭이 아닌 경우 카드 합 계산 기능을 테스트한다.")
    @Test
    void calculateTotalCardNumberWhenIsNotBlackJackTest() {
        // given
        PlayerName playerName = new PlayerName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));
        Player player = new Player(playerName, bettingMoney);
        player.addCard(new Card(Symbol.SPADE, Type.KING));
        player.addCard(new Card(Symbol.HEART, Type.KING));
        player.addCard(new Card(Symbol.CLUB, Type.KING));

        // when
        int totalCardNumber = player.calculateTotalCardNumber();

        // then
        assertThat(totalCardNumber).isEqualTo(30);
    }

    @DisplayName("플레이어의 카드 조합이 블랙잭인 경우 카드 합 계산 기능을 테스트한다.")
    @Test
    void calculateTotalCardNumberWhenIsBlackJackTest() {
        // given
        PlayerName playerName = new PlayerName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));
        Player player = new Player(playerName, bettingMoney);
        player.addCard(new Card(Symbol.SPADE, Type.ACE));
        player.addCard(new Card(Symbol.SPADE, Type.KING));

        // when

        // then
        assertThat(player.calculateTotalCardNumber()).isEqualTo(21);
    }

    @DisplayName("플레이어의 카드 합이 21이하일 경우 Bust 되었는지 판단하는 기능을 테스트한다.")
    @Test
    void determineIsBustWhenSumLessThanBlackJackTest() {
        // given
        PlayerName playerName = new PlayerName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));
        Player player = new Player(playerName, bettingMoney);

        // when
        boolean isBust = player.isBust();

        // then
        assertThat(isBust).isFalse();
    }

    @DisplayName("플레이어의 카드 합이 21을 초과할 경우 Bust 되었는지 판단하는 기능을 테스트한다.")
    @Test
    void determineIsBustWhenSumExceededBlackJackTest() {
        // given
        PlayerName playerName = new PlayerName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));
        Player player = new Player(playerName, bettingMoney);
        player.addCard(new Card(Symbol.SPADE, Type.KING));
        player.addCard(new Card(Symbol.HEART, Type.KING));
        player.addCard(new Card(Symbol.CLUB, Type.KING));

        // when
        boolean isBust = player.isBust();

        // then
        assertThat(isBust).isTrue();
    }

    @DisplayName("플레이어의 게임 결과 결정 기능을 테스트한다.")
    @Test
    void determineWinOrDrawOrLoseTest() {
        // given
        PlayerName playerName = new PlayerName("player");
        BettingMoney bettingMoney = new BettingMoney(new BigDecimal(1000));
        Player player = new Player(playerName, bettingMoney);
        player.addCard(new Card(Symbol.SPADE, Type.JACK));
        player.addCard(new Card(Symbol.SPADE, Type.ACE));
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.SPADE, Type.KING));
        dealer.addCard(new Card(Symbol.HEART, Type.KING));

        // when
        GameResult gameResult = player.determineWinOrDrawOrLose(dealer);

        // then
        assertThat(gameResult).isEqualTo(GameResult.WIN_WITH_BLACK_JACK);
    }

    @DisplayName("플레이어의 최종 수익 계산 기능을 테스트한다.")
    @Test
    void calculateFinalProfitTest() {
        // given
        Player player = new Player(new PlayerName("player"), new BettingMoney(new BigDecimal(1000)));
        player.addCard(new Card(Symbol.SPADE, Type.ACE));
        player.addCard(new Card(Symbol.HEART, Type.KING));
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.SPADE, Type.KING));
        dealer.addCard(new Card(Symbol.HEART, Type.KING));

        // when
        Profit finalProfit = player.calculateFinalProfit(dealer);

        // then
        assertThat(finalProfit).isEqualTo(new Profit(new BigDecimal("1500")));
    }

    @DisplayName("플레이어의 상태 결정 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndStatus")
    void determineStatusTest(Card firstCard, Card secondCard, Card thirdCard, Status status) {
        // given
        Player player = new Player(new PlayerName("player"), new BettingMoney(new BigDecimal(1000)));
        player.addCard(firstCard);
        player.addCard(secondCard);
        if (thirdCard != null) {
            player.addCard(thirdCard);
        }

        // when
        Status playerStatus = player.determineStatus();

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
