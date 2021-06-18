package domain.user;

import domain.card.Card;
import domain.card.Deck;
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

    @DisplayName("플레이어가 카드덱으로부터 카드를 뽑는 기능을 테스트한다.")
    @Test
    void 플레이어가_카드덱으로부터_카드를_뽑는다() {
        // given
        Player player = new Player(name, bettingMoney);
        Deck deck = new Deck();

        // when
        player.hit(deck);

        // then
        assertAll(
                () -> assertThat(player.getHand().getState().getCards()).hasSize(1),
                () -> assertThat(deck.getCards()).hasSize(51)
        );
    }

    @DisplayName("플레이어가 플레이어인지 여부를 검사하는 기능을 테스트한다.")
    @Test
    void 플레이어는_플레이어이다() {
        // given
        Player player = new Player(name, bettingMoney);

        // when
        boolean isPlayer = player.isPlayer();

        // then
        assertThat(isPlayer).isTrue();
    }

    @DisplayName("플레이어가 딜러인지 여부를 검사하는 기능을 테스트한다.")
    @Test
    void 플레이어는_딜러가_아니다() {
        // given
        Player player = new Player(name, bettingMoney);

        // when
        boolean isDealer = player.isDealer();

        // then
        assertThat(isDealer).isFalse();
    }

}
