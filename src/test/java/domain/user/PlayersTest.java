package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {

    private static List<Player> playerList;

    @BeforeEach
    void setUp() {
        Player player1 = new Player(new PlayerName("player1"), new BettingMoney(new BigDecimal(1000)));
        player1.addCard(new Card(Symbol.SPADE, Type.KING));
        player1.addCard(new Card(Symbol.SPADE, Type.ACE));
        Player player2 = new Player(new PlayerName("player2"), new BettingMoney(new BigDecimal(1000)));
        player2.addCard(new Card(Symbol.HEART, Type.KING));
        player2.addCard(new Card(Symbol.HEART, Type.SEVEN));
        playerList = Arrays.asList(player1, player2);
        Deck.initializeDeck();
    }

    @DisplayName("Players 생성을 테스트한다.")
    @Test
    void createPlayersTest() {
        // given

        // when
        Players players = new Players(playerList);

        // then
        assertAll(
                () -> assertThat(players).isInstanceOf(Players.class),
                () -> assertThat(players.getPlayers()).hasSize(2)
        );
    }

    @DisplayName("플레이어들 각각이 카드를 뽑는 기능을 테스트한다.")
    @Test
    void drawCardsEachOtherTest() {
        // given
        Players players = new Players(playerList);

        // when
        players.drawCardsEachOther();

        // then
        for (Player player : players.getPlayers()) {
            assertThat(player.getCards()).hasSize(3);
        }
    }

    @DisplayName("플레이어들 각각의 최종 수익 통계 내는 기능을 테스트한다.")
    @Test
    void producePlayersFinalProfitTest() {
        // given
        Players players = new Players(playerList);
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.SPADE, Type.JACK));
        dealer.addCard(new Card(Symbol.HEART, Type.QUEEN));

        // when
        Map<Player, BigDecimal> playersFinalProfits = players.producePlayersFinalProfits(dealer);

        // then
        assertAll(
                () -> assertThat(playersFinalProfits.size()).isEqualTo(2),
                () -> assertThat(playersFinalProfits.get(playerList.get(0))).isEqualTo(new BigDecimal("1500")),
                () -> assertThat(playersFinalProfits.get(playerList.get(1))).isEqualTo(new BigDecimal("-1000"))
        );
    }
}