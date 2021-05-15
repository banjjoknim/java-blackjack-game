package domain.game;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import view.InputView;
import view.OutputView;

public class BlackJackGame {

    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void proceedDistributeCardsToUsers() {
        deck.distributeCardsToPlayersAndDealer(players, dealer);
    }

    public void proceedPlayersTurn() {
        players.getPlayers()
                .forEach(player -> chooseAnswer(player));
    }

    private void chooseAnswer(Player player) {
        while (!player.isBust() && !player.isBlackJack()) {
            OutputView.printDoYouWantOneMoreCard(player);
            if (InputView.inputAnswer()) {
                player.hit(deck);
                OutputView.printCardsHeldByPlayer(player);
                continue;
            }
            break;
        }
    }

    public void proceedDealerTurn() {
        while (!dealer.isStay()) {
            dealer.hit(deck);
            OutputView.printDealerGetOneMoreCard();
        }
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }
}
