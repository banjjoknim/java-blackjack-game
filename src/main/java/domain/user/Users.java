package domain.user;

import domain.card.Deck;

import java.util.Collections;
import java.util.List;

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

    public boolean hasWaitingPlayer() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .anyMatch(User::isWait);
    }

    public Player findWaitingPlayer() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .map(user -> (Player) user)
                .filter(User::isWait)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("대기중인 플레이어가 없습니다."));
    }

    public Dealer findDealer() {
        return users.stream()
                .filter(user -> user instanceof Dealer)
                .map(user -> (Dealer) user)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("딜러가 존재하지 않습니다."));
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
