package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @DisplayName("이름이 공백일 경우 예외 처리를 테스트한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void 이름에_공백을_입력할_경우_예외가_발생한다(String input) {
        // given

        // when

        // then
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("올바른 이름을 입력했을 경우 Name 생성을 테스트한다.")
    @ParameterizedTest
    @ValueSource(strings = {"헬로", "안녕"})
    void 올바른_입력이면_Name이_생성된다(String input) {
        // given

        // when

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> new Name(input)),
                () -> assertThat(new Name(input).getName()).isEqualTo(input)
        );
    }
}
