package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.GameResult;
import domain.user.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayerTest {

    private static final Card SPADE_KING = Card.of(Symbol.SPADE, Type.KING);
    private static final Card SPADE_QUEEN = Card.of(Symbol.SPADE, Type.QUEEN);
    private static final Card SPADE_THREE = Card.of(Symbol.SPADE, Type.THREE);
    private static final Card SPADE_TWO = Card.of(Symbol.SPADE, Type.THREE);
    private static final Card SPADE_ACE = Card.of(Symbol.SPADE, Type.ACE);

    private static final Bust BUST_23 = new Bust(Arrays.asList(SPADE_KING, SPADE_QUEEN, SPADE_THREE));
    private static final Bust BUST_22 = new Bust(Arrays.asList(SPADE_KING, SPADE_QUEEN, SPADE_TWO));
    private static final Stay STAY_13 = new Stay(Arrays.asList(SPADE_KING, SPADE_THREE));
    private static final Stay STAY_20 = new Stay(Arrays.asList(SPADE_KING, SPADE_QUEEN));
    private static final Blackjack BLACKJACK = new Blackjack(Arrays.asList(SPADE_KING, SPADE_ACE));

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
                Arguments.of(Arrays.asList(Card.of(Symbol.SPADE, Type.KING), Card.of(Symbol.SPADE, Type.ACE)), false),
                Arguments.of(Arrays.asList(Card.of(Symbol.SPADE, Type.KING), Card.of(Symbol.SPADE, Type.QUEEN)), true),
                Arguments.of(Arrays.asList(Card.of(Symbol.SPADE, Type.KING), Card.of(Symbol.SPADE, Type.FIVE)), true),
                Arguments.of(Arrays.asList(Card.of(Symbol.SPADE, Type.KING), Card.of(Symbol.SPADE, Type.QUEEN),
                        Card.of(Symbol.SPADE, Type.THREE)), false),
                Arguments.of(Arrays.asList(Card.of(Symbol.SPADE, Type.KING), Card.of(Symbol.SPADE, Type.SEVEN)), true),
                Arguments.of(Arrays.asList(Card.of(Symbol.SPADE, Type.KING), Card.of(Symbol.SPADE, Type.SEVEN),
                        Card.of(Symbol.HEART, Type.FOUR)), false)
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
                () -> assertThat(player.getState().getCards()).hasSize(1),
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

    @DisplayName("플레이어가 대기 상태일 때, 스테이로 차례를 마치는 기능을 테스트한다.")
    @Test
    void 플레이어가_대기_상태일_때_스테이를_선택하면_스테이_상태가_된다() {
        // given
        Player player = new Player(name, bettingMoney, new Wait(new ArrayList<>()));

        // when
        player.stay();

        // then
        assertThat(player.getState()).isInstanceOf(Stay.class);
    }

    @DisplayName("플레이어의 차례가 끝난 상태일 때, 스테이로 차례를 마치는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideState")
    void 플레이어의_차례가_끝난_상태일_때_스테이를_선택하면_예외가_발생한다(State state) {
        // given
        Player player = new Player(name, bettingMoney, state);

        // when

        // then
        assertThatThrownBy(player::stay).isInstanceOf(IllegalStateException.class);
    }

    private static Stream<Arguments> provideState() {
        return Stream.of(
                Arguments.of(new Bust(new ArrayList<>())),
                Arguments.of(new Blackjack(new ArrayList<>())),
                Arguments.of(new Stay(new ArrayList<>()))
        );
    }

    @DisplayName("딜러와 플레이어의 상태로 플레이어의 게임 결과를 찾는 기능을 테스트한다")
    @ParameterizedTest
    @MethodSource("provideStates")
    void 딜러와_플레이어의_상태로_게임_결과를_찾는다(State dealerState, State playerState, GameResult result) {
        // given
        Dealer dealer = new Dealer(dealerState);
        Player player = new Player(name, bettingMoney, playerState);

        // when
        GameResult actual = player.produceGameResult(dealer);

        // then
        assertThat(actual).isEqualTo(result);
    }

    private static Stream<Arguments> provideStates() {
        return Stream.of(
                Arguments.of(BUST_23, BUST_22, GameResult.LOSE),
                Arguments.of(STAY_20, BUST_23, GameResult.LOSE),
                Arguments.of(BLACKJACK, BUST_23, GameResult.LOSE),
                Arguments.of(BUST_23, STAY_20, GameResult.WIN),
                Arguments.of(STAY_20, STAY_20, GameResult.DRAW),
                Arguments.of(STAY_13, STAY_20, GameResult.WIN),
                Arguments.of(BLACKJACK, STAY_20, GameResult.LOSE),
                Arguments.of(BUST_23, BLACKJACK, GameResult.BLACKJACK),
                Arguments.of(STAY_20, BLACKJACK, GameResult.BLACKJACK),
                Arguments.of(BLACKJACK, BLACKJACK, GameResult.DRAW)
        );
    }

}
