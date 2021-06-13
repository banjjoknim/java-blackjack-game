package domain.user;

import domain.card.Deck;
import domain.result.PlayerProfits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class Users {

    private List<User> users;

    public Users(List<User> users) {
        validateDuplicatePlayerName(users);
        this.users = users;
    }

    private void validateDuplicatePlayerName(List<User> users) {
        int userCount = users.size();
        int deduplicatedUserNameCount = users.stream()
                .map(User::getUserName)
                .collect(toSet())
                .size();
        if (userCount != deduplicatedUserNameCount) {
            throw new IllegalArgumentException("유저의 이름은 중복될 수 없습니다.");
        }
    }

    public void receiveCards(Deck deck) {
        users.forEach(deck::distributeCard);
    }

    public PlayerProfits producePlayerProfits() {
        Dealer dealer = getDealer();
        Map<Player, Profit> playerProfits = getPlayers().stream()
                .collect(toMap(player -> player, player -> player.calculateFinalProfit(dealer)));
        return new PlayerProfits(playerProfits);
    }

    public Dealer getDealer() {
        return users.stream()
                .filter(user -> user instanceof Dealer)
                .map(user -> (Dealer) user)
                .findFirst()
                .get();
    }

    public List<Player> getPlayers() {
        List<Player> players = users.stream()
                .filter(user -> user instanceof Player)
                .map(user -> (Player) user)
                .collect(toList());
        return new ArrayList<>(players);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
