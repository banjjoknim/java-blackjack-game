package domain.user;

import domain.card.Deck;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Users {
    private static final int DEALER_COUNT = 1;

    private List<User> users;

    public Users(List<User> users) {
        validateUsers(users);
        this.users = users;
    }

    private void validateUsers(List<User> users) {
        validateDuplicateNames(users);
        validateIsExistsDealer(users);
    }

    private void validateDuplicateNames(List<User> users) {
        int playerCount = users.stream()
                .filter(User::isPlayer)
                .collect(toSet())
                .size();
        if (playerCount != users.size() - DEALER_COUNT) {
            throw new IllegalArgumentException("플레이어는 중복되는 이름을 가질 수 없습니다.");
        }
    }

    private void validateIsExistsDealer(List<User> users) {
        boolean isExistsDealer = users.stream()
                .anyMatch(User::isDealer);
        if (!isExistsDealer) {
            throw new IllegalArgumentException("유저들에 딜러가 포함되어 있지 않습니다.");
        }
    }

    public void receiveCards(Deck deck) {
        users.forEach(deck::distributeCard);
    }

    public boolean hasWaitingPlayer() {
        return users.stream()
                .filter(User::isPlayer)
                .anyMatch(User::isWait);
    }

    public Player findPlayerOfCurrentTurn() {
        return users.stream()
                .filter(User::isPlayer)
                .map(user -> (Player) user)
                .filter(User::isWait)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("대기중인 플레이어가 없습니다."));
    }

    public UserProfits produceUserProfits() {
        Map<User, Profit> userProfits = new HashMap<>();
        Dealer dealer = findDealer();
        findPlayers().forEach(player -> userProfits.put(player, player.produceProfit(dealer)));
        Profit dealerProfit = calculateDealerProfit(userProfits);
        userProfits.put(dealer, dealerProfit);
        return new UserProfits(userProfits);
    }

    private Profit calculateDealerProfit(Map<User, Profit> playerProfitResults) {
        return playerProfitResults.values().stream()
                .reduce(Profit.ZERO, Profit::add)
                .negate();
    }

    public Dealer findDealer() {
        return users.stream()
                .filter(User::isDealer)
                .map(user -> (Dealer) user)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("딜러가 존재하지 않습니다."));
    }

    public List<Player> findPlayers() {
        return users.stream()
                .filter(User::isPlayer)
                .map(user -> (Player) user)
                .collect(toList());
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
