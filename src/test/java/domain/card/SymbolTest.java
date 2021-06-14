package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SymbolTest {

    @DisplayName("Symbol 이 잘 초기화 되었는지 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideSymbolAndSymbolName")
    void getSymbolNameTest(Symbol symbol, String expectedResult) {
        // given

        // when
        String symbolName = symbol.getSymbolName();

        // then
        assertThat(symbolName).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideSymbolAndSymbolName() {
        return Stream.of(
                Arguments.of(Symbol.CLUB, "클로버"),
                Arguments.of(Symbol.DIAMOND, "다이아몬드"),
                Arguments.of(Symbol.SPADE, "스페이드"),
                Arguments.of(Symbol.HEART, "하트")
        );
    }
}
