package domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class PlayerNames {

    private List<PlayerName> playerNames;

    public PlayerNames(List<PlayerName> playerNames) {
        validatePlayerNames(playerNames);
        this.playerNames = new ArrayList<>(playerNames);
    }

    private void validatePlayerNames(List<PlayerName> playerNames) {
        int playerNamesSize = playerNames.size();
        int deduplicatedPlayerNamesSize = new HashSet<>(playerNames).size();
        if (playerNamesSize != deduplicatedPlayerNamesSize) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    public List<PlayerName> getPlayerNames() {
        return Collections.unmodifiableList(playerNames);
    }

}
