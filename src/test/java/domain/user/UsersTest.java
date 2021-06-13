package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.PlayerProfits;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UsersTest {

    private List<User> userList;
    private Player player1 = new Player(new UserName("player1"), new BettingMoney(new BigDecimal(1000)));
    private Player player2 = new Player(new UserName("player2"), new BettingMoney(new BigDecimal(1000)));
    private Dealer dealer = new Dealer();

    @BeforeEach
    void setUp() {
        userList = new ArrayList<>();
        userList.add(player1);
        userList.add(player2);
    }

    @DisplayName("중복되는 이름으로 Users 생성시 예외 처리를 테스트한다.")
    @Test
    void createPlayersWithDuplicateNameTest() {
        // given
        userList.add(dealer);
        userList.add(new Player(new UserName("딜러"), new BettingMoney(new BigDecimal(1000))));

        // when

        // then
        assertThatThrownBy(() -> new Users(userList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저의 이름은 중복될 수 없습니다.");
    }

    @DisplayName("Users 생성을 테스트한다.")
    @Test
    void createPlayersTest() {
        // given
        userList.add(dealer);

        // when

        // then
        assertDoesNotThrow(() -> new Users(userList));
    }

    @DisplayName("유저들 각각이 카드를 뽑는 기능을 테스트한다.")
    @Test
    void receiveCardsTest() {
        // given
        userList.add(dealer);
        Users users = new Users(userList);
        Deck deck = new Deck();

        // when
        users.receiveCards(deck);

        // then
        for (User user : users.getUsers()) {
            assertThat(user.getCards()).hasSize(1);
        }
    }

    @DisplayName("플레이어들 각각의 최종 수익 통계 내는 기능을 테스트한다.")
    @Test
    void producePlayersFinalProfitTest() {
        // given
        userList.add(dealer);
        Users users = new Users(userList);
        player1.addCard(new Card(Symbol.SPADE, Type.ACE));
        player1.addCard(new Card(Symbol.SPADE, Type.KING));
        player2.addCard(new Card(Symbol.CLUB, Type.QUEEN));
        player2.addCard(new Card(Symbol.SPADE, Type.SIX));
        dealer.addCard(new Card(Symbol.SPADE, Type.JACK));
        dealer.addCard(new Card(Symbol.HEART, Type.QUEEN));

        // when
        PlayerProfits playerProfits = users.producePlayerProfits();

        // then
        assertAll(
                () -> assertThat(playerProfits.getPlayerProfits()).hasSize(2),
                () -> assertThat(playerProfits.getPlayerProfit(player1)).isEqualTo(new Profit(new BigDecimal("1500"))),
                () -> assertThat(playerProfits.getPlayerProfit(player2)).isEqualTo(new Profit(new BigDecimal("-1000")))
        );
    }

    @DisplayName("딜러를 찾는 기능을 테스트 한다.")
    @Test
    void findDealerTest() {
        // given
        userList.add(dealer);
        Users users = new Users(userList);


        // when

        // then
        assertDoesNotThrow(() -> users.findDealer());
    }

    @DisplayName("딜러가 존재하지 않을 때 예외 처리를 테스트 한다.")
    @Test
    void findDealerWhenNotExistsDealerTest() {
        // given
        Users users = new Users(userList);

        // when

        // then
        assertThatThrownBy(() -> users.findDealer())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러가 존재하지 않습니다.");
    }

    @DisplayName("플레이어를 찾는 기능을 테스트 한다.")
    @Test
    void findPlayersTest() {
        // given
        Users users = new Users(userList);

        // when
        List<Player> players = users.findPlayers();

        // then
        assertThat(players).hasSize(2);
    }

}
