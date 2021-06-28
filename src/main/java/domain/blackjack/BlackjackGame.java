package domain.blackjack;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.UserProfits;
import domain.user.Users;

import java.util.List;

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
        Dealer dealer = users.findDealer();
        return dealer.isWait() && !isPlayersPhase();
    }

    public boolean isPlayersPhase() {
        return users.hasWaitingPlayer();
    }

    public void proceedPlayerHitPhase() {
        users.findPlayerOfCurrentTurn().hit(deck);
    }

    public void proceedDealerHitPhase() {
        users.findDealer().hit(deck);
    }

    public Player getPlayerOfCurrentTurn() {
        return users.findPlayerOfCurrentTurn();
    }

    public Dealer getDealer() {
        return users.findDealer();
    }

    public List<Player> getPlayers() {
        return users.findPlayers();
    }

    public UserProfits getUserProfits() {
        return users.produceUserProfits();
    }
}
