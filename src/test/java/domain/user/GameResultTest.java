package domain.user;

import domain.result.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameResultTest {

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
                () -> assertThat(GameResult.getGameResult(winValue)).isEqualTo(GameResult.WIN),
                () -> assertThat(GameResult.getGameResult(drawValue)).isEqualTo(GameResult.DRAW),
                () -> assertThat(GameResult.getGameResult(loseValue)).isEqualTo(GameResult.LOSE)
        );
    }

    @DisplayName("GameResult 에 존재하지 않는 결과를 도출할때 예외 처리를 테스트한다.")
    @ParameterizedTest
    @ValueSource(ints = {-3, 3, 4, 5})
    void getGameResultWithNotExistValueTest(int gameResultValue) {
        // given

        // when

        // then
        assertThatThrownBy(() -> GameResult.getGameResult(gameResultValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 결과는 승, 무, 패 중에서 하나여야 합니다.");
    }
}