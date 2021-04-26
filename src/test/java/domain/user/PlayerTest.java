package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @DisplayName("Player 생성을 테스트한다.")
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

    @DisplayName("Player 의 게임 결과 결정 기능을 테스트한다.")
    @Test
    void determineGameResult() {
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
        GameResult gameResult = player.determineGameResult(dealer);

        // then
        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

}