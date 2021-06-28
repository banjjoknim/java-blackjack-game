package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.state.Blackjack;
import domain.user.state.Stay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UserProfitsTest {
    private static final Card SPADE_QUEEN = Card.of(Symbol.SPADE, Type.QUEEN);
    private static final Card SPADE_KING = Card.of(Symbol.SPADE, Type.KING);
    private static final Card SPADE_ACE = Card.of(Symbol.SPADE, Type.ACE);

    private static List<User> userList;
    private static final Player player1 = new Player(new Name("name1"), new BettingMoney(BigDecimal.valueOf(1000)),
            new Stay(Arrays.asList(SPADE_QUEEN, SPADE_KING)));
    private static final Player player2 = new Player(new Name("name2"), new BettingMoney(BigDecimal.valueOf(1000)),
            new Blackjack(Arrays.asList(SPADE_QUEEN, SPADE_ACE)));
    private static final Dealer dealer = new Dealer(new Stay(Arrays.asList(Card.of(Symbol.SPADE, Type.QUEEN), Card.of(Symbol.SPADE, Type.KING))));

    @BeforeEach
    void setUp() {
        userList = new ArrayList<>();
        userList.add(player1);
        userList.add(player2);
        userList.add(dealer);
    }

    @DisplayName("유저의 수익 결과로부터 유저의 수익을 얻는 기능을 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideUser")
    void 유저의_수익_결과로부터_유저의_수익_결과를_얻을_수_있다(User user, Profit expected) {
        // given
        Users users = new Users(userList);
        UserProfits userProfits = users.produceUserProfits();

        // when
        Profit actual = userProfits.getUserProfit(user);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideUser() {
        return Stream.of(
                Arguments.of(player1, new Profit(BigDecimal.ZERO)),
                Arguments.of(player2, new Profit(BigDecimal.valueOf(1500))),
                Arguments.of(dealer, new Profit(BigDecimal.valueOf(-1500)))
        );
    }

}
