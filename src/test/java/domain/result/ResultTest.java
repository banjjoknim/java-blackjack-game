package domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @DisplayName("입력값에 해당하는 게임 결과를 찾는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideValueAndExpected")
    void 입력에_해당하는_게임_결과를_찾는다(int value, Result expected) {
        // given

        // when
        Result result = Result.from(value);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideValueAndExpected() {
        return Stream.of(
                Arguments.of(-1, Result.LOSE),
                Arguments.of(0, Result.DRAW),
                Arguments.of(1, Result.WIN)
        );
    }
}
