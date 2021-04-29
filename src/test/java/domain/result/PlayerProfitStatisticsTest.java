package domain.result;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    @DisplayName("PlayerProfitStatistics 의 플레이어들의 총 수익 계산 기능을 테스트한다.")
    @Test
    void calculateTotalPlayerProfitsTest() {
        // given
        Players players = new Players(playerList);
        Map<Player, Profit> statistics = players.producePlayersProfitStatistics(dealer);

        // when
        PlayerProfitStatistics playerProfitStatistics = new PlayerProfitStatistics(statistics);

        // then
        assertThat(playerProfitStatistics.calculateTotalPlayerProfits()).isEqualTo(new Profit(new BigDecimal("500")));
    }

    @DisplayName("플레이어의 수익 통계로부터 딜러의 수익을 계산하는 기능을 테스트한다.")
    @Test
    void getDealerProfitFromPlayerProfitStatisticsTest() {
        // given
        Dealer dealer = new Dealer();
        Map<Player, Profit> statistics = new HashMap<>();
        Player player = new Player(new PlayerName("player"), new BettingMoney(new BigDecimal("1000")));
        Profit playerProfit = new Profit(new BigDecimal("1000"));
        statistics.put(player, playerProfit);
        PlayerProfitStatistics playerProfitStatistics = new PlayerProfitStatistics(statistics);

        // when
        Profit dealerProfit = playerProfitStatistics.calculateDealerProfit();

        // then
        assertThat(dealerProfit).isEqualTo(new Profit(new BigDecimal("-1000")));
    }

    @DisplayName("PlayerProfitStatistics 의 플레이어의 수익을 얻는 기능을 테스트한다.")
    @Test
    void getPlayerProfitTest() {
        // given
        Players players = new Players(playerList);
        Map<Player, Profit> statistics = players.producePlayersProfitStatistics(dealer);

        // when
        PlayerProfitStatistics playerProfitStatistics = new PlayerProfitStatistics(statistics);

        // then
        assertAll(
                () -> assertThat(playerProfitStatistics.getPlayerProfit(player1)).isEqualTo(new Profit(new BigDecimal("1500"))),
                () -> assertThat(playerProfitStatistics.getPlayerProfit(player2)).isEqualTo(new Profit(new BigDecimal("-1000")))
        );
    }
}
