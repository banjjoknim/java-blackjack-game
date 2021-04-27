package domain.result;

import domain.user.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class PlayerProfitStatistics {
    private static final int SCALE = 0;

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

    public double getPlayerProfit(Player player) {
        return playerProfitStatistics.get(player)
                .setScale(SCALE, RoundingMode.HALF_EVEN)
                .doubleValue();
    }

}
