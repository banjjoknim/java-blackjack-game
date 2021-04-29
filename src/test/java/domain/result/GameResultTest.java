package domain.result;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    private static Player player;
    private static Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player(new PlayerName("player"), new BettingMoney(new BigDecimal(1000)));
        player.addCard(new Card(Symbol.SPADE, Type.KING));
        dealer = new Dealer();
        dealer.addCard(new Card(Symbol.DIAMOND, Type.KING));
    }

    @DisplayName("GameResult 의 결과 결정 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideGameResultValueAndPlayerCardAndDealerCardAndGameResult")
    void determineGameResultTest(int gameResultValue, Card playerCard, Card dealerCard, GameResult gameResult) {
        // given
        player.addCard(playerCard);
        dealer.addCard(dealerCard);
        Status playerStatus = player.determineStatus();
        Status dealerStatus = dealer.determineStatus();

        // when

        // then
        assertThat(GameResult.determineWinOrDrawOrLose(gameResultValue, playerStatus, dealerStatus)).isEqualTo(gameResult);
    }

    private static Stream<Arguments> provideGameResultValueAndPlayerCardAndDealerCardAndGameResult() {
        return Stream.of(
                Arguments.of(1, new Card(Symbol.DIAMOND, Type.ACE), new Card(Symbol.CLUB, Type.EIGHT), GameResult.WIN_WITH_BLACK_JACK),
                Arguments.of(1, new Card(Symbol.DIAMOND, Type.JACK), new Card(Symbol.CLUB, Type.EIGHT), GameResult.WIN),
                Arguments.of(0, new Card(Symbol.DIAMOND, Type.ACE), new Card(Symbol.CLUB, Type.ACE), GameResult.DRAW_WITH_BLACK_JACK),
                Arguments.of(0, new Card(Symbol.DIAMOND, Type.EIGHT), new Card(Symbol.CLUB, Type.EIGHT), GameResult.DRAW),
                Arguments.of(-1, new Card(Symbol.DIAMOND, Type.SEVEN), new Card(Symbol.CLUB, Type.EIGHT), GameResult.LOSE)
        );
    }

    @DisplayName("GameResult 의 배당률과 배팅 금액으로 결과 수익 계산 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideGameResultAndExpectedAmount")
    void multiplyDividendRateTest(GameResult gameResult, BigDecimal expectedAmount) {
        // given
        BigDecimal amount = new BigDecimal(1000);

        // when
        BigDecimal multipliedByDividendRateAmount = gameResult.multiplyDividendRate(amount);

        // then
        assertThat(multipliedByDividendRateAmount).isEqualTo(expectedAmount);
    }

    private static Stream<Arguments> provideGameResultAndExpectedAmount() {
        return Stream.of(
                Arguments.of(GameResult.WIN_WITH_BLACK_JACK, new BigDecimal("1500")),
                Arguments.of(GameResult.WIN, new BigDecimal("1000")),
                Arguments.of(GameResult.DRAW_WITH_BLACK_JACK, new BigDecimal("0")),
                Arguments.of(GameResult.DRAW, new BigDecimal("0")),
                Arguments.of(GameResult.LOSE, new BigDecimal("-1000"))
        );
    }

}
