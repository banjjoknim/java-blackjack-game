package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayerTest {

    private Name name;
    private BettingMoney bettingMoney;

    @BeforeEach
    void setUp() {
        name = new Name("name");
        bettingMoney = new BettingMoney(new BigDecimal("1000"));
    }

    @DisplayName("플레이어 생성을 테스트한다.")
    @Test
    void 입력한_값으로_플레이어가_생성된다() {
        // given

        // when

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> new Player(name, bettingMoney)),
                () -> assertThat(new Player(name, bettingMoney).getName()).isEqualTo(name),
                () -> assertThat(new Player(name, bettingMoney).getBettingMoney()).isEqualTo(bettingMoney)
        );
    }

    @DisplayName("플레이어가 대기 상태인지 검증하는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpected")
    void 플레이어가_대기_상태인지_검증한다(List<Card> cards, boolean expected) {
        // given
        Player player = new Player(name, bettingMoney);
        cards.forEach(player::draw);

        // when
        boolean isWait = player.isWait();

        // then
        assertThat(isWait).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpected() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Card(Symbol.SPADE, Type.KING), new Card(Symbol.SPADE, Type.ACE)), false),
                Arguments.of(Arrays.asList(new Card(Symbol.SPADE, Type.KING), new Card(Symbol.SPADE, Type.QUEEN)), true),
                Arguments.of(Arrays.asList(new Card(Symbol.SPADE, Type.KING), new Card(Symbol.SPADE, Type.FIVE)), true),
                Arguments.of(Arrays.asList(new Card(Symbol.SPADE, Type.KING), new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.SPADE, Type.THREE)), false),
                Arguments.of(Arrays.asList(new Card(Symbol.SPADE, Type.KING), new Card(Symbol.SPADE, Type.SEVEN)), true),
                Arguments.of(Arrays.asList(new Card(Symbol.SPADE, Type.KING), new Card(Symbol.SPADE, Type.SEVEN),
                        new Card(Symbol.HEART, Type.FOUR)), false)
        );
    }

}
