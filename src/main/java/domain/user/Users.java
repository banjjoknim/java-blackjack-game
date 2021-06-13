package domain.user;

import domain.card.Deck;

import java.util.Collections;
import java.util.List;

public class Users {

    private List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public void receiveCards(Deck deck) {
        users.forEach(deck::distributeCard);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
