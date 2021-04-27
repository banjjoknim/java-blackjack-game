package domain.result;

import domain.user.Player;

import java.math.BigDecimal;
import java.util.Map;

public class PlayerProfitStatistics {

    private Map<Player, BigDecimal> playerProfitStatistics;

    public PlayerProfitStatistics(Map<Player, BigDecimal> playerProfitStatistics) {
        this.playerProfitStatistics = playerProfitStatistics;
    }

    public double calculateDealerProfit() {
        return playerProfitStatistics.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .negate()
                .doubleValue();
    }

    public BigDecimal getPlayerProfit(Player player) {
        return playerProfitStatistics.get(player);
    }

}
