package domain.user;

import domain.card.Deck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Players {

    private List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public void drawCardsEachOther() {
        players.forEach(Deck::distributeCard);
    }

    public Map<Player, BigDecimal> producePlayersFinalProfits(Dealer dealer) {
        return players.stream()
                .collect(toMap(player -> player,
                        player -> player.calculateFinalProfit(dealer)));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

}
