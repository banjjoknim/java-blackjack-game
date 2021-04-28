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

class AnswerTest {

    @DisplayName("올바른 입력으로 Answer 생성을 테스트한다.")
    @ParameterizedTest
    @ValueSource(strings = {"y", "n"})
    void createAnswerTest(String inputAnswer) {
        // given

        // when
        Answer answer = new Answer(inputAnswer);

        // then
        assertAll(
                () -> assertThat(answer).isInstanceOf(Answer.class),
                () -> assertThat(answer.getAnswer()).isEqualTo(inputAnswer)
        );
    }

    @DisplayName("잘못된 입력으로 Answer 생성시 예외 처리를 테스트한다.")
    @ParameterizedTest
    @ValueSource(strings = {"hello", "world"})
    void validateAnswerTest(String inputAnswer) {
        // given

        // when

        // then
        assertThatThrownBy(() -> new Answer(inputAnswer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("대답으로는 'y' 또는 'n' 만 입력할 수 있습니다.");
    }

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
