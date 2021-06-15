package domain.blackjack;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.*;
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

class BlackjackGameTest {

    private List<User> userList = new ArrayList<>();
    private Deck deck;
    private Users users;

    @BeforeEach
    void setUp() {
        userList.add(new Player(new Name("name"), new BettingMoney(new BigDecimal("1000"))));
        userList.add(new Player(new Name("name"), new BettingMoney(new BigDecimal("1000"))));
        userList.add(new Dealer());
        deck = new Deck();
        users = new Users(userList);
    }

    @DisplayName("블랙잭 게임 생성을 테스트한다.")
    @Test
    void 블랙잭_게임을_생성한다() {
        // given


        // when

        // then
        assertDoesNotThrow(() -> new BlackjackGame(users, deck));
    }

    @DisplayName("유저들에게 카드를 나누어주는 단계가 잘 수행되는지 테스트한다.")
    @Test
    void 유저들에게_카드를_나누는_단계가_수행되면_모든_유저가_2장의_카드를_갖는다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(users, deck);

        // when
        blackjackGame.proceedDealPhase();

        // then
        assertAll(
                () -> assertThat(userList.get(0).getHand().getCards()).hasSize(2),
                () -> assertThat(userList.get(1).getHand().getCards()).hasSize(2),
                () -> assertThat(userList.get(2).getHand().getCards()).hasSize(2)
        );
    }

    @DisplayName("블랙잭 게임에서 대기중인 플레이어가 있을 때 대기중인 플레이어의 존재 여부 판단 기능을 테스트한다.")
    @Test
    void 대기중인_플레이어가_존재한다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(users, deck);

        // when
        boolean isPlayersPhase = blackjackGame.isPlayersPhase();

        // then
        assertThat(isPlayersPhase).isTrue();
    }

    @DisplayName("블랙잭 게임에서 대기중인 플레이어가 있을 때 대기중인 플레이어의 존재 여부 판단 기능을 테스트한다.")
    @Test
    void 대기중인_플레이어가_존재하지_않는다() {
        // given
        List<Card> cards = Arrays.asList(new Card(Symbol.SPADE, Type.ACE), new Card(Symbol.SPADE, Type.KING));
        User user1 = users.getUsers().get(0);
        User user2 = users.getUsers().get(1);
        cards.forEach(user1::draw);
        cards.forEach(user2::draw);
        BlackjackGame blackjackGame = new BlackjackGame(users, deck);

        // when
        boolean isPlayersPhase = blackjackGame.isPlayersPhase();

        // then
        assertThat(isPlayersPhase).isFalse();
    }

    @DisplayName("블랙잭 게임에서 대기중인 플레이어가 있을 때 대기중인 플레이어를 찾는 기능을 테스트한다.")
    @Test
    void 대기중인_플레이어가_존재할때_대기중인_플레이어를_찾는다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(users, deck);

        // when

        // then
        assertDoesNotThrow(blackjackGame::getWaitingPlayer);
    }

    @DisplayName("블랙잭 게임에서 대기중인 플레이어가 없을 때 대기중인 플레이어를 찾는 기능을 테스트한다.")
    @Test
    void 대기중인_플레이어가_없을_때_대기중인_플레이어를_찾으면_예외가_발생한다() {
        // given
        List<Card> cards = Arrays.asList(new Card(Symbol.SPADE, Type.ACE), new Card(Symbol.SPADE, Type.KING));
        User user1 = userList.get(0);
        User user2 = userList.get(1);
        cards.forEach(user1::draw);
        cards.forEach(user2::draw);
        BlackjackGame blackjackGame = new BlackjackGame(users, deck);

        // when

        // then
        assertThatThrownBy(blackjackGame::getWaitingPlayer)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("플레이어가 카드덱으로부터 카드를 뽑는 과정이 잘 수행되는지 테스트한다.")
    @Test
    void 플레이어가_카드덱으로부터_카드를_뽑는_과정을_수행한다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(users, deck);
        Player player = blackjackGame.getWaitingPlayer();

        // when
        blackjackGame.proceedHitPhase(player);

        // then
        assertThat(player.getHand().getCards()).hasSize(1);
    }
}
