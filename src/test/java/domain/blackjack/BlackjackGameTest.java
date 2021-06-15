package domain.blackjack;

import domain.card.Deck;
import domain.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
}
