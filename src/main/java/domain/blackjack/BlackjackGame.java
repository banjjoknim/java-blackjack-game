package domain.blackjack;

import domain.card.Deck;
import domain.user.Users;

public class BlackjackGame {

    private Users users;
    private Deck deck;

    public BlackjackGame(Users users, Deck deck) {
        this.users = users;
        this.deck = deck;
    }
}
