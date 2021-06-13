package domain.result;

import domain.user.Player;
import domain.user.Profit;

import java.util.Collections;
import java.util.Map;

import static domain.user.Profit.ZERO;

public class PlayerProfits {

    private Map<Player, Profit> playerProfits;

    public PlayerProfits(Map<Player, Profit> playerProfits) {
        this.playerProfits = playerProfits;
    }

    public Profit calculateDealerProfit() {
        return playerProfits.values().stream()
                .reduce(ZERO, Profit::addProfit)
                .negate();
    }

    public Profit getPlayerProfit(Player player) {
        return playerProfits.get(player);
    }

    public Map<Player, Profit> getPlayerProfits() {
        return Collections.unmodifiableMap(playerProfits);
    }
}
