package domain.user;

import domain.card.Deck;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Users {

    private List<User> users;

    public Users(List<User> users) {
        validateDealerIsExists(users);
        this.users = users;
    }

    private void validateDealerIsExists(List<User> users) {
        boolean isExistsDealer = users.stream()
                .anyMatch(user -> user instanceof Dealer);
        if (!isExistsDealer) {
            throw new IllegalArgumentException("유저들에 딜러가 포함되어 있지 않습니다.");
        }
    }

    public void receiveCards(Deck deck) {
        users.forEach(deck::distributeCard);
    }

    public Player findWaitingPlayer() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .map(user -> (Player) user)
                .filter(User::isWaiting)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("대기중인 플레이어가 없습니다."));
    }

    public boolean hasWaitingPlayer() {
        return users.stream()
                .anyMatch(User::isWaiting);
    }

    public User findDealer() {
        return users.stream()
                .filter(user -> user instanceof Dealer)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("딜러가 존재하지 않습니다."));
    }

    public List<Player> findPlayers() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .map(user -> (Player) user)
                .collect(toList());
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
