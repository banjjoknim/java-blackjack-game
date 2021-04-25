package domain.user;

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

}