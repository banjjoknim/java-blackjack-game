package domain.result;

import domain.user.Player;
import domain.user.Profit;

import java.util.Map;

import static domain.user.Profit.ZERO_PROFIT;

public class PlayerProfitStatistics {

    private Map<Player, Profit> playerProfitStatistics;

    public PlayerProfitStatistics(Map<Player, Profit> playerProfitStatistics) {
        this.playerProfitStatistics = playerProfitStatistics;
    }

    public Profit calculateDealerProfit() {
        return calculateTotalPlayerProfits().negate();
    }

    public Profit calculateTotalPlayerProfits() {
        return playerProfitStatistics.values().stream()
                .reduce(ZERO_PROFIT, Profit::addProfit);
    }

    public Profit getPlayerProfit(Player player) {
        return playerProfitStatistics.get(player);
    }

}
