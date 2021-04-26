package domain.result;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.GameResult;
import domain.user.BettingMoney;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameResultTest {

    private static Player player;
    private static Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player(new PlayerName("player"), new BettingMoney(new BigDecimal(1000)));
        player.addCard(new Card(Symbol.SPADE, Type.ACE));
        player.addCard(new Card(Symbol.SPADE, Type.KING));
        dealer = new Dealer();
        dealer.addCard(new Card(Symbol.DIAMOND, Type.JACK));
        dealer.addCard(new Card(Symbol.DIAMOND, Type.KING));
    }

    @DisplayName("GameResult 의 입력값에 따른 결과 도출 기능을 테스트한다.")
    @Test
    void getGameResultTest() {
        // given
        int winValue = 1;
        int drawValue = 0;
        int loseValue = -1;

        // when

        // then
        assertAll(
                () -> assertThat(GameResult.determineGameResult(winValue, player, dealer)).isEqualTo(GameResult.WIN_WITH_BLACK_JACK),
                () -> assertThat(GameResult.determineGameResult(winValue, player, dealer)).isEqualTo(GameResult.WIN),
                () -> assertThat(GameResult.determineGameResult(drawValue, player, dealer)).isEqualTo(GameResult.DRAW_WITH_BLACK_JACK),
                () -> assertThat(GameResult.determineGameResult(drawValue, player, dealer)).isEqualTo(GameResult.DRAW),
                () -> assertThat(GameResult.determineGameResult(loseValue, player, dealer)).isEqualTo(GameResult.LOSE)
        );
    }

    @DisplayName("GameResult 의 배당률과 배팅 금액으로 결과 수익 계산 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideGameResultAndDividendRate")
    void multiplyDividendRateTest(GameResult gameResult, BigDecimal dividendRate) {
        // given
        BigDecimal amount = new BigDecimal(1000);

        // when
        BigDecimal multipliedByDividendRateAmount = gameResult.multiplyDividendRate(amount);

        // then
        assertThat(multipliedByDividendRateAmount).isEqualTo(amount.multiply(dividendRate));
    }

    private static Stream<Arguments> provideGameResultAndDividendRate() {
        return Stream.of(
                Arguments.of(GameResult.WIN_WITH_BLACK_JACK, new BigDecimal("1.5")),
                Arguments.of(GameResult.WIN, new BigDecimal("1")),
                Arguments.of(GameResult.DRAW_WITH_BLACK_JACK, new BigDecimal("0")),
                Arguments.of(GameResult.DRAW, new BigDecimal("0")),
                Arguments.of(GameResult.LOSE, new BigDecimal("-1"))
        );
    }

}