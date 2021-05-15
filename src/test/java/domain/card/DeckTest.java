package domain.card;

import domain.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @DisplayName("Deck 생성시 초기 카드 적재 기능을 테스트한다.")
    @ParameterizedTest
    @EnumSource(Symbol.class)
    void initializeDeckTest(Symbol symbol) {
        // given

        // when
        List<Card> cards = deck.getCards();
        long symbolCardCounts = cards.stream()
                .filter(card -> card.getSymbol().equals(symbol))
                .count();

        // then
        assertAll(
                () -> assertThat(cards).hasSize(52),
                () -> assertThat(symbolCardCounts).isEqualTo(Type.values().length)
        );
    }

    @DisplayName("Deck 의 플레이어들과 딜러에게 카드를 나누어주는 기능을 테스트한다.")
    @Test
    void distributeCardsToPlayersAndDealerTest() {
        // given
        Player player = new Player(new PlayerName("player"), new BettingMoney(new BigDecimal(1000)));
        List<Player> playerList = new ArrayList<>();
        playerList.add(player);
        Players players = new Players(playerList);
        Dealer dealer = new Dealer();

        // when
        deck.distributeCardsToPlayersAndDealer(players, dealer);

        // then
        assertAll(
                () -> assertThat(player.getCards()).hasSize(2),
                () -> assertThat(dealer.getCards()).hasSize(2)
        );

    }

    @DisplayName("Deck 의 카드를 나누어주는 기능을 테스트한다.")
    @Test
    void distributeCardTest() {
        // given
        Player player = new Player(new PlayerName("player"), new BettingMoney(new BigDecimal(1000)));

        // when
        deck.distributeCard(player);

        // then
        assertThat(player.getCards()).hasSize(1);
        assertThat(deck.getCards().size()).isEqualTo(51);
    }

}
