package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class TypeTest {

    @DisplayName("Type 이 ACE 일 경우, Type 이 ACE 인지 판단하는 기능을 테스트 한다.")
    @ParameterizedTest
    @EnumSource(value = Type.class, mode = EnumSource.Mode.INCLUDE, names = {"ACE"})
    void typeIsAceTest(Type type) {
        // given

        // when
        boolean isAce = type.isAce();

        // then
        assertThat(isAce).isTrue();
    }

    @DisplayName("Type 이 ACE 가 아닐 경우, Type 이 ACE 인지 판단하는 기능을 테스트 한다.")
    @ParameterizedTest
    @EnumSource(value = Type.class, mode = EnumSource.Mode.EXCLUDE, names = {"ACE"})
    void typeIsNotAceTest(Type type) {
        // given

        // when
        boolean isAce = type.isAce();

        // then
        assertThat(isAce).isFalse();
    }

    @DisplayName("Type 이 숫자 10을 가지는 경우를 테스트 한다.")
    @ParameterizedTest
    @EnumSource(value = Type.class, mode = EnumSource.Mode.INCLUDE, names = {"KING", "QUEEN", "JACK", "TEN"})
    void typeHasTenNumberTest(Type type) {
        // given

        // when
        boolean isTenNumber = type.isTenNumber();

        // then
        assertThat(isTenNumber).isTrue();
    }

    @DisplayName("Type 이 숫자 10을 가지지 않는 경우를 테스트 한다.")
    @ParameterizedTest
    @EnumSource(value = Type.class, mode = EnumSource.Mode.EXCLUDE, names = {"KING", "QUEEN", "JACK", "TEN"})
    void typeHasNotTenNumberTest(Type type) {
        // given

        // when
        boolean isTenNumber = type.isTenNumber();

        // then
        assertThat(isTenNumber).isFalse();
    }

    @DisplayName("Type 이 잘 초기화 되었는지 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideTypeAndTypeNameAndTypeNumber")
    void getTypeTest(Type type, String expectedTypeName, int expectedTypeNumber) {
        // given

        // when
        String typeName = type.getTypeName();
        int typeNumber = type.getNumber();

        // then
        assertAll(
                () -> assertThat(typeName).isEqualTo(expectedTypeName),
                () -> assertThat(typeNumber).isEqualTo(expectedTypeNumber)
        );
    }

    private static Stream<Arguments> provideTypeAndTypeNameAndTypeNumber() {
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
