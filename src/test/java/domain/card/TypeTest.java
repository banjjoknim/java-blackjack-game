package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class TypeTest {

    @DisplayName("Type 갯수가 13개로 잘 초기화 되었는지 테스트 한다.")
    @Test
    void 카드의_타입_갯수가_13개이다() {
        // given
        Type[] types = Type.values();

        // when
        int typeLength = types.length;

        // then
        assertThat(typeLength).isEqualTo(13);
    }

    @DisplayName("Type 이름과 숫자가 잘 초기화 되었는지 테스트 한다.")
    @ParameterizedTest
    @MethodSource("provideTypeAndNameAndNumber")
    void 카드_타입의_이름과_숫자가_잘_초기화_되었는지_테스트_한다(Type type, String name, int number) {
        // given

        // when
        String typeName = type.getName();
        int typeNumber = type.getNumber();

        // then
        assertAll(
                () -> assertThat(typeName).isEqualTo(name),
                () -> assertThat(typeNumber).isEqualTo(number)
        );

    }

    private static Stream<Arguments> provideTypeAndNameAndNumber() {
        return Stream.of(
                Arguments.of(Type.ACE, "A", 1),
                Arguments.of(Type.TWO, "2", 2),
                Arguments.of(Type.THREE, "3", 3),
                Arguments.of(Type.FOUR, "4", 4),
                Arguments.of(Type.FIVE, "5", 5),
                Arguments.of(Type.SIX, "6", 6),
                Arguments.of(Type.SEVEN, "7", 7),
                Arguments.of(Type.EIGHT, "8", 8),
                Arguments.of(Type.NINE, "9", 9),
                Arguments.of(Type.TEN, "10", 10),
                Arguments.of(Type.JACK, "J", 10),
                Arguments.of(Type.QUEEN, "Q", 10),
                Arguments.of(Type.KING, "K", 10)
        );
    }
}
