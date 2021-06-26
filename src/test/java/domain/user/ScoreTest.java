package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @DisplayName("부적절한 값으로 점수를 생성하는 기능을 테스트한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -100, -1000})
    void 점수가_0보다_작을_경우_예외가_발생한다(int score) {
        // given

        // when

        // then
        assertThatThrownBy(() -> new Score(score)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("올바른 값으로 점수를 생성하는 기능을 테스트한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 100, 1000})
    void 점수가_0과_같거나_클_경우_점수가_생성된다(int score) {
        // given

        // when
        Score actual = new Score(score);

        // then
        assertThat(actual.getScore()).isEqualTo(score);
    }

}