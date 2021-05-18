package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StatusTest {

    @DisplayName("Status 를 결정하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideTotalCardNumberAndCanBlackJackAndResult")
    void determineStatusTest(int totalCardNumber, boolean canBlackJack, Status result) {
        // given

        // when
        Status status = Status.determineStatus(totalCardNumber, canBlackJack);

        // then
        assertThat(status).isEqualTo(result);
    }

    private static Stream<Arguments> provideTotalCardNumberAndCanBlackJackAndResult() {
        return Stream.of(
                Arguments.of(21, true, Status.BLACK_JACK),
                Arguments.of(15, true, Status.SURVIVAL),
                Arguments.of(24, true, Status.BUST),
                Arguments.of(21, false, Status.SURVIVAL),
                Arguments.of(15, false, Status.SURVIVAL),
                Arguments.of(24, false, Status.BUST)
        );
    }

    @DisplayName("Status 가 Bust 인지 검증하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideStatusAndIsBustResult")
    void isBustTest(Status status, boolean result) {
        // given

        // when
        boolean isBust = status.isBust();

        // then
        assertThat(isBust).isEqualTo(result);
    }

    private static Stream<Arguments> provideStatusAndIsBustResult() {
        return Stream.of(
                Arguments.of(Status.BUST, true),
                Arguments.of(Status.BLACK_JACK, false),
                Arguments.of(Status.SURVIVAL, false)
        );
    }

    @DisplayName("Status 가 BlackJack 인지 검증하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideStatusAndIsBlackJackResult")
    void isBlackJackTest(Status status, boolean result) {
        // given

        // when
        boolean isBust = status.isBust();

        // then
        assertThat(isBust).isEqualTo(result);
    }

    private static Stream<Arguments> provideStatusAndIsBlackJackResult() {
        return Stream.of(
                Arguments.of(Status.BUST, true),
                Arguments.of(Status.BLACK_JACK, false),
                Arguments.of(Status.SURVIVAL, false)
        );
    }

    @DisplayName("Status 가 Survival 인지 검증하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideStatusAndIsSurvivalResult")
    void isSurvivalTest(Status status, boolean result) {
        // given

        // when
        boolean isBust = status.isSurvival();

        // then
        assertThat(isBust).isEqualTo(result);
    }

    private static Stream<Arguments> provideStatusAndIsSurvivalResult() {
        return Stream.of(
                Arguments.of(Status.BUST, false),
                Arguments.of(Status.BLACK_JACK, false),
                Arguments.of(Status.SURVIVAL, true)
        );
    }

}
