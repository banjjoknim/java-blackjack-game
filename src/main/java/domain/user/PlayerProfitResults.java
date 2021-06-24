package domain.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlayerProfitResults {

    private Map<Player, Profit> playerProfitResults;

    public PlayerProfitResults(Map<Player, Profit> playerProfitResults) {
        this.playerProfitResults = new HashMap<>(playerProfitResults);
    }

    public Profit produceDealerProfit() {
        return playerProfitResults.values().stream()
                .reduce(Profit.ZERO, Profit::add)
                .negate();
    }

    public Map<Player, Profit> getPlayerProfitResults() {
        return Collections.unmodifiableMap(playerProfitResults);
    }
}
