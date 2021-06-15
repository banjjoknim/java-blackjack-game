package domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UsersTest {

    private List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        userList.add(new Player(new Name("name"), new BettingMoney(new BigDecimal("1000"))));
        userList.add(new Player(new Name("name"), new BettingMoney(new BigDecimal("1000"))));
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
        assertDoesNotThrow(() -> new Users(userList));
    }

    @DisplayName("유저들 중에서 딜러를 찾는 기능을 테스트한다.")
    @Test
    void 유저들_중에서_딜러를_찾는다() {
        userList.add(new Dealer());

        // when
        Users users = new Users(userList);
        User user = users.findDealer();

        // then
        assertThat(user).isInstanceOf(Dealer.class);
    }

}
