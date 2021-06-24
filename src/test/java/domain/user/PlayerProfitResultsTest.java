package domain.user;

import domain.card.Card;
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
import static org.junit.jupiter.api.Assertions.*;

class PlayerProfitResultsTest {
    private static final Card SPADE_QUEEN = Card.of(Symbol.SPADE, Type.QUEEN);
    private static final Card SPADE_KING = Card.of(Symbol.SPADE, Type.KING);
    private static final Card SPADE_ACE = Card.of(Symbol.SPADE, Type.ACE);

    private List<User> userList = new ArrayList<>();
    private Player player1;
    private Player player2;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player1 = new Player(new Name("name1"), new BettingMoney(BigDecimal.valueOf(1000)),
                new Stay(Arrays.asList(SPADE_QUEEN, SPADE_KING)));
        player2 = new Player(new Name("name2"), new BettingMoney(BigDecimal.valueOf(1000)),
                new Blackjack(Arrays.asList(SPADE_QUEEN, SPADE_ACE)));
        dealer = new Dealer(new Stay(Arrays.asList(Card.of(Symbol.SPADE, Type.QUEEN), Card.of(Symbol.SPADE, Type.KING))));
        userList.add(player1);
        userList.add(player2);
        userList.add(dealer);
    }

    @DisplayName("플레이어의 수익 결과로부터 딜러의 수익을 계산하는 기능을 테스트한다.")
    @Test
    void 플레이어의_수익으로_딜러의_수익을_생성한다() {
        // given
        Users users = new Users(userList);
        PlayerProfitResults playerProfitResults = users.producePlayerProfits();

        // when
        Profit actual = playerProfitResults.produceDealerProfit();

        // then
        assertThat(actual).isEqualTo(new Profit(BigDecimal.valueOf(-1500)));
    }
}
