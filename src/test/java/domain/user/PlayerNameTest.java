package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayerNameTest {

    @DisplayName("올바른 입력값으로 PlayerName 생성을 테스트한다.")
    @ParameterizedTest
    @ValueSource(strings = {"player1", "player2", "player3"})
    void createPlayerNameWithCorrectInputTest(String name) {
        // given

        // when
        PlayerName playerName = new PlayerName(name);

        // then
        assertAll(
                () -> assertThat(playerName).isInstanceOf(PlayerName.class),
                () -> assertThat(playerName.getName()).isEqualTo(name)
        );
    }

    @DisplayName("공백으로 PlayerName 생성을 테스트한다.")
    @ParameterizedTest
    @EmptySource
    void createPlayerNameWithNotCorrectInputTest(String name) {
        // given

        // when

        // then
        assertThatThrownBy(() -> new PlayerName(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 공백일 수 없습니다.");
    }
}