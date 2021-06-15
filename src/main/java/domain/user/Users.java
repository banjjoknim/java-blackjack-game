package domain.user;

import domain.card.Deck;

import java.util.ArrayList;
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

    public User findDealer() {
        return users.stream()
                .filter(user -> user instanceof Dealer)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("딜러가 존재하지 않습니다."));
    }

    public void receiveCards(Deck deck) {
        users.forEach(deck::distributeCard);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
