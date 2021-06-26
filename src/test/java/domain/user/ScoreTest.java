package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    @DisplayName("점수의 대소 비교 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideScore")
    void 입력된_값과_점수를_비교할_수_있다(int providedScore, boolean igBiggerExpected, boolean isSameExpected, boolean isSmallerExpected) {
        // given
        Score score = new Score(20);

        // when
        boolean isBigger = score.isBiggerThan(providedScore);
        boolean isSame = score.isSame(providedScore);
        boolean isSmaller = score.isSmallerThan(providedScore);

        // then
        assertAll(
                () -> assertThat(isBigger).isEqualTo(igBiggerExpected),
                () -> assertThat(isSame).isEqualTo(isSameExpected),
                () -> assertThat(isSmaller).isEqualTo(isSmallerExpected)
        );
    }

    private static Stream<Arguments> provideScore() {
        return Stream.of(
                Arguments.of(19, true, false, false),
                Arguments.of(20, false, true, false),
                Arguments.of(21, false, false, true)
        );
    }
}