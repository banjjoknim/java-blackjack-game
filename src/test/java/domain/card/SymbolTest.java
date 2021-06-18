package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SymbolTest {

    @DisplayName("Symbol 종류가 4개로 잘 초기화 되었는지 테스트 한다.")
    @Test
    void 카드_문양이_4개인지_테스트한다() {
        // given

        // when
        int symbolLength = Symbol.values().length;

        // then
        assertThat(symbolLength).isEqualTo(4);
    }

    @DisplayName("Symbol의 문양 이름이 잘 초기화 되었는지 테스트 한다.")
    @ParameterizedTest
    @MethodSource("provideSymbolAndName")
    void 카드_문양의_이름이_잘_초기화_되었는지_테스트한다(Symbol symbol, String name) {
        // given

        // when
        String symbolName = symbol.getName();

        // then
        assertThat(symbolName).isEqualTo(name);

    }

    private static Stream<Arguments> provideSymbolAndName() {
        return Stream.of(
                Arguments.of(Symbol.CLUB, "클로버"),
                Arguments.of(Symbol.DIAMOND, "다이아몬드"),
                Arguments.of(Symbol.HEART, "하트"),
                Arguments.of(Symbol.SPADE, "스페이드")
        );
    }

}
