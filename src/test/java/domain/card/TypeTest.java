package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

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
        boolean hasTenNumber = type.hasTenNumber();

        // then
        assertThat(hasTenNumber).isTrue();
    }

    @DisplayName("Type 이 숫자 10을 가지지 않는 경우를 테스트 한다.")
    @ParameterizedTest
    @EnumSource(value = Type.class, mode = EnumSource.Mode.EXCLUDE, names = {"KING", "QUEEN", "JACK", "TEN"})
    void typeHasNotTenNumberTest(Type type) {
        // given

        // when
        boolean hasTenNumber = type.hasTenNumber();

        // then
        assertThat(hasTenNumber).isFalse();
    }
}
