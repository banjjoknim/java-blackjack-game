package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerTest {

    @DisplayName("Answer 의 답변 판별 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideInputAnswerAndBoolean")
    void answerIsYesTest(String inputAnswer, boolean expectedResult) {
        // given

        // when
        Answer answer = new Answer(inputAnswer);

        // then
        assertThat(answer.isYes()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideInputAnswerAndBoolean() {
        return Stream.of(
                Arguments.of("y", true),
                Arguments.of("n", false)
        );
    }

}