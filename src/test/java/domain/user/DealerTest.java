package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.PlayerProfitStatistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("딜러의 카드 숫자 합이 규칙에 명시된 숫자보다 크거나 같은지 검증하는 기능을 테스트한다.")
    @Test
    void hasSmallNumberLessThanRuleNumberTest() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.SPADE, Type.KING));
        dealer.addCard(new Card(Symbol.HEART, Type.KING));

        // when
        boolean hasSmallNumberLessThanRuleNumber = dealer.hasSmallNumberLessThanRuleNumber();

        // then
        assertThat(hasSmallNumberLessThanRuleNumber).isFalse();
    }

    @DisplayName("딜러의 카드 추가 기능을 테스트한다.")
    @Test
    void addCardTest() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.addCard(new Card(Symbol.DIAMOND, Type.ACE));

        // then
        assertThat(dealer.getCards()).hasSize(1);
    }

    @DisplayName("딜러의 카드 합 계산 기능을 테스트한다.")
    @Test
    void calculateSumOfCardNumbersTest() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.SPADE, Type.KING));
        dealer.addCard(new Card(Symbol.HEART, Type.KING));
        dealer.addCard(new Card(Symbol.CLUB, Type.KING));

        // when
        int totalCardNumber = dealer.calculateTotalCardNumber();

        // then
        assertThat(totalCardNumber).isEqualTo(30);

    }

    @DisplayName("딜러의 카드 조합이 블랙잭인 경우를 테스트한다.")
    @Test
    void isBlackJackTest() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.SPADE, Type.ACE));
        dealer.addCard(new Card(Symbol.SPADE, Type.KING));

        // when

        // then
        assertThat(dealer.isBlackJack()).isTrue();
    }

    @DisplayName("딜러의 카드 합이 21이하일 경우 Bust 되었는지 판단하는 기능을 테스트한다.")
    @Test
    void determineIsBustWhenSumLessThanBlackJackTest() {
        // given
        Dealer dealer = new Dealer();

        // when
        boolean isBust = dealer.isBust();

        // then
        assertThat(isBust).isFalse();
    }

    @DisplayName("딜러의 카드 합이 21을 초과할 경우 Bust 되었는지 판단하는 기능을 테스트한다.")
    @Test
    void determineIsBustWhenSumExceededBlackJackTest() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.SPADE, Type.KING));
        dealer.addCard(new Card(Symbol.HEART, Type.KING));
        dealer.addCard(new Card(Symbol.CLUB, Type.KING));

        // when
        boolean isBust = dealer.isBust();

        // then
        assertThat(isBust).isTrue();
    }

    @DisplayName("플레이어의 수익 통계로부터 딜러의 수익을 계산하는 기능을 테스트한다.")
    @Test
    void getDealerProfitFromPlayerProfitStatisticsTest() {
        // given
        Dealer dealer = new Dealer();
        Map<Player, Profit> statistics = new HashMap<>();
        Player player = new Player(new PlayerName("player"), new BettingMoney(new BigDecimal("1000")));
        Profit playerProfit = new Profit(new BigDecimal("1000"));
        statistics.put(player, playerProfit);
        PlayerProfitStatistics playerProfitStatistics = new PlayerProfitStatistics(statistics);

        // when
        Profit dealerProfit = dealer.getDealerProfitFromPlayerProfitStatistics(playerProfitStatistics);

        // then
        assertThat(dealerProfit).isEqualTo(new Profit(new BigDecimal("-1000")));
    }

}