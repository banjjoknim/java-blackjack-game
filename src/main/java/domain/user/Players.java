package domain.user;

import domain.card.Deck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class Players {

    private List<Player> players;

    public Players(List<Player> players) {
        validateDuplicatePlayerName(players);
        this.players = new ArrayList<>(players);
    }

    private void validateDuplicatePlayerName(List<Player> players) {
        int playerCount = players.size();
        int deduplicatedPlayerNameCount = players.stream()
                .map(Player::getPlayerName)
                .collect(toSet())
                .size();
        if (playerCount != deduplicatedPlayerNameCount) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    public void drawCardEachPlayer() {
        players.forEach(Deck::distributeCard);
    }

    public Map<Player, BigDecimal> producePlayersProfitStatistics(Dealer dealer) {
        return players.stream()
                .collect(toMap(player -> player,
                        player -> player.calculateFinalProfit(dealer)));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

}
