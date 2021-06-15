package domain.user;

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
}
