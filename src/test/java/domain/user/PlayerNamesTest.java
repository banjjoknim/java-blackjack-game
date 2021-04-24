package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayerNamesTest {

    @DisplayName("중복되지 않은 이름으로 PlayerNames 생성을 테스트한다.")
    @Test
    void createPlayerNamesWithNotDuplicatedNames() {
        // given
        List<PlayerName> names = Arrays.asList(new PlayerName("player1"), new PlayerName("player2"));

        // when
        PlayerNames playerNames = new PlayerNames(names);

        // then
        assertAll(
                () -> assertThat(playerNames).isInstanceOf(PlayerNames.class),
                () -> assertThat(playerNames.getPlayerNames()).hasSize(2)
        );
    }

    @DisplayName("중복되는 이름으로 PlayerNames 생성을 테스트한다.")
    @Test
    void createPlayerNamesWithDuplicatedNames() {
        // given
        List<PlayerName> names = Arrays.asList(new PlayerName("player1"), new PlayerName("player1"));

        // when

        // then
        assertThatThrownBy(() -> new PlayerNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
    }
    
}