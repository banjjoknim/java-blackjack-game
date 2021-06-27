package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.state.Blackjack;
import domain.user.state.Stay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UsersTest {
    private static final Card SPADE_QUEEN = Card.of(Symbol.SPADE, Type.QUEEN);
    private static final Card SPADE_KING = Card.of(Symbol.SPADE, Type.KING);
    private static final Card SPADE_ACE = Card.of(Symbol.SPADE, Type.ACE);

    private List<User> userList = new ArrayList<>();
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        player1 = new Player(new Name("name1"), new BettingMoney(new BigDecimal("1000")));
        player2 = new Player(new Name("name2"), new BettingMoney(new BigDecimal("1000")));
        userList.add(player1);
        userList.add(player2);
    }

    @DisplayName("딜러 없이 유저들을 생성하는 기능을 테스트한다.")
    @Test
    void 딜러가_없는데_유저들을_생성하면_예외가_발생한다() {
        // given

        // when

        // then
        assertThatThrownBy(() -> new Users(userList))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("딜러와 함께 유저들을 생성하는 기능을 테스트한다.")
    @Test
    void 딜러와_함께_유저들이_생성된다() {
        // given
        userList.add(new Dealer());

        // when

        // then
        assertAll(
                () -> assertDoesNotThrow(() -> new Users(userList)),
                () -> assertThat(new Users(userList).getUsers()).hasSize(3)
        );
    }

    @DisplayName("딜러와 유저들이 각각 덱으로부터 카드를 나눠받는 기능을 테스트한다.")
    @Test
    void 딜러와_유저들이_각각_카드를_받는다() {
        // given
        userList.add(new Dealer());
        Deck deck = new Deck();

        // when
        Users users = new Users(userList);
        users.receiveCards(deck);

        // then
        List<User> list = users.getUsers();
        assertAll(
                () -> assertThat(list.get(0).getState().getCards()).hasSize(1),
                () -> assertThat(list.get(1).getState().getCards()).hasSize(1),
                () -> assertThat(list.get(2).getState().getCards()).hasSize(1)
        );
    }

    @DisplayName("유저들 중에서 대기중인 플레이어가 존재할 때 대기중인 플레이어의 존재 유무를 판단하는 기능을 테스트한다.")
    @Test
    void 대기중인_플레이어가_존재한다() {
        // given
        userList.add(new Dealer());
        Users users = new Users(userList);

        // when
        boolean hasWaitingPlayer = users.hasWaitingPlayer();

        // then
        assertThat(hasWaitingPlayer).isTrue();
    }

    @DisplayName("유저들 중에서 대기중인 플레이어가 존재하지 않을 때 대기중인 플레이어의 존재 유무를 판단하는 기능을 테스트한다.")
    @Test
    void 대기중인_플레이어가_존재하지_않는다() {
        // given
        userList.add(new Dealer());
        List<Card> cards = Arrays.asList(Card.of(Symbol.SPADE, Type.ACE), Card.of(Symbol.SPADE, Type.KING));
        User user1 = userList.get(0);
        User user2 = userList.get(1);
        cards.forEach(user1::draw);
        cards.forEach(user2::draw);
        Users users = new Users(userList);

        // when
        boolean hasWaitingPlayer = users.hasWaitingPlayer();

        // then
        assertThat(hasWaitingPlayer).isFalse();
    }

    @DisplayName("대기중인 플레이어가 존재할 때 유저들 중에서 대기중인 플레이어를 찾는 기능을 테스트한다.")
    @Test
    void 대기중인_플레이어가_존재할_때_대기중인_플레이어를_찾는다() {
        // given
        userList.add(new Dealer());
        Users users = new Users(userList);

        // when

        // then
        assertDoesNotThrow(users::findPlayerOfCurrentTurn);
    }

    @DisplayName("대기중인 플레이어가 존재하지 않을 때 유저들 중에서 대기중인 플레이어를 찾는 기능을 테스트한다.")
    @Test
    void 대기중인_플레이어가_존재하지_않을_때_대기중인_플레이어를_찾으면_예외가_발생한다() {
        // given
        userList.add(new Dealer());
        List<Card> cards = Arrays.asList(Card.of(Symbol.SPADE, Type.ACE), Card.of(Symbol.SPADE, Type.KING));
        User user1 = userList.get(0);
        User user2 = userList.get(1);
        cards.forEach(user1::draw);
        cards.forEach(user2::draw);
        Users users = new Users(userList);

        // when

        // then
        assertThatThrownBy(users::findPlayerOfCurrentTurn)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("유저들 중에서 딜러를 찾는 기능을 테스트한다.")
    @Test
    void 유저들_중에서_딜러를_찾는다() {
        // given
        userList.add(new Dealer());
        Users users = new Users(userList);

        // when

        // then
        assertDoesNotThrow(users::findDealer);
    }

    @DisplayName("유저들 중에서 플레이어들을 찾는 기능을 테스트한다.")
    @Test
    void 유저들_중에서_플레이어들을_찾는다() {
        // given
        userList.add(new Dealer());
        Users users = new Users(userList);

        // when
        List<Player> players = users.findPlayers();

        // then
        assertThat(players).hasSize(2);
    }

    @DisplayName("게임 결과에 따른 유저들 각각의 이익을 저장한 결과를 얻는 기능을 테스트한다.")
    @Test
    void 유저들로부터_각각의_유저에_대한_수익을_저장한_결과를_얻는다() {
        // given
        userList.clear();
        player1 = new Player(new Name("name1"), new BettingMoney(BigDecimal.valueOf(1000)),
                new Stay(Arrays.asList(SPADE_QUEEN, SPADE_KING)));
        player2 = new Player(new Name("name2"), new BettingMoney(BigDecimal.valueOf(1000)),
                new Blackjack(Arrays.asList(SPADE_QUEEN, SPADE_ACE)));
        Dealer dealer = new Dealer(new Stay(Arrays.asList(Card.of(Symbol.SPADE, Type.QUEEN), Card.of(Symbol.SPADE, Type.KING))));
        userList.add(player1);
        userList.add(player2);
        userList.add(dealer);
        Users users = new Users(userList);

        // when
        PlayerProfitResults playerProfitResults = users.producePlayerProfits();

        // then
        assertAll(
                () -> assertThat(playerProfitResults.getPlayerProfitResults()).hasSize(2),
                () -> assertThat(playerProfitResults.getPlayerProfitResults().get(player1)).isEqualTo(new Profit(BigDecimal.valueOf(0))),
                () -> assertThat(playerProfitResults.getPlayerProfitResults().get(player2)).isEqualTo(new Profit(BigDecimal.valueOf(1500)))
        );
    }
}
