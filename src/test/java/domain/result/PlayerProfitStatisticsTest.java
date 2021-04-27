package domain.result;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.*;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerProfitStatisticsTest {

    private static List<Player> playerList;
    private static Player player1;
    private static Player player2;
    private static Dealer dealer = new Dealer();

    @BeforeAll
    static void setUp() {
        player1 = new Player(new PlayerName("player1"), new BettingMoney(new BigDecimal(1000)));
        player1.addCard(new Card(Symbol.SPADE, Type.KING));
        player1.addCard(new Card(Symbol.SPADE, Type.ACE));
        player2 = new Player(new PlayerName("player2"), new BettingMoney(new BigDecimal(1000)));
        player2.addCard(new Card(Symbol.HEART, Type.KING));
        player2.addCard(new Card(Symbol.HEART, Type.SEVEN));
        playerList = new ArrayList<>(Arrays.asList(player1, player2));
        dealer.addCard(new Card(Symbol.SPADE, Type.JACK));
        dealer.addCard(new Card(Symbol.HEART, Type.QUEEN));
    }

    @DisplayName("PlayerProfitStatistics 의 딜러의 수익 계산 기능을 테스트한다.")
    @Test
    void calculateDealerProfit() {
        // given
        Players players = new Players(playerList);
        Map<Player, BigDecimal> statistics = players.producePlayersProfitStatistics(dealer);

        // when
        PlayerProfitStatistics playerProfitStatistics = new PlayerProfitStatistics(statistics);

        // then
        assertThat(playerProfitStatistics.calculateDealerProfit()).isEqualTo(-500);
    }

    @DisplayName("PlayerProfitStatistics 의 플레이어의 수익을 얻는 기능을 테스트한다.")
    @Test
    void getPlayerProfit() {
        // given
        Players players = new Players(playerList);
        Map<Player, BigDecimal> statistics = players.producePlayersProfitStatistics(dealer);

        // when
        PlayerProfitStatistics playerProfitStatistics = new PlayerProfitStatistics(statistics);

        // then
        assertAll(
                () -> assertThat(playerProfitStatistics.getPlayerProfit(player1)).isEqualTo(1500),
                () -> assertThat(playerProfitStatistics.getPlayerProfit(player2)).isEqualTo(-1000)
        );
    }
}