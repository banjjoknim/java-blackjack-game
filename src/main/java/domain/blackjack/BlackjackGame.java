package domain.blackjack;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;

public class BlackjackGame {
    private static final int ZERO = 0;
    private static final int INITIAL_CARDS_SIZE = 2;

    private Users users;
    private Deck deck;

    public BlackjackGame(Users users, Deck deck) {
        this.users = users;
        this.deck = deck;
    }

    public void proceedDealPhase() {
        for (int i = ZERO; i < INITIAL_CARDS_SIZE; i++) {
            users.receiveCards(deck);
        }
    }

    public boolean isDealerPhase() {
        Dealer dealer = getDealer();
        return dealer.isWait() && !isPlayersPhase();
    }

    public Dealer getDealer() {
        return users.findDealer();
    }

    public boolean isPlayersPhase() {
        return users.hasWaitingPlayer();
    }

    public Player getWaitingPlayer() {
        return users.findWaitingPlayer();
    }

    public void proceedHitPhase(User user) {
        user.hit(deck);
    }
}
