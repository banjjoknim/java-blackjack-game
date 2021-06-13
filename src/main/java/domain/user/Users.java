package domain.user;

import domain.card.Deck;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Users {

    private List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public void receiveCards(Deck deck) {
        users.forEach(deck::distributeCard);
    }

    public Players getPlayers() {
        List<Player> players = users.stream()
                .filter(user -> user instanceof Player)
                .map(user -> (Player) user)
                .collect(toList());
        return new Players(players);
    }

    public Dealer getDealer() {
        return users.stream()
                .filter(user -> user instanceof Dealer)
                .map(user -> (Dealer) user)
                .findFirst()
                .get();
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
