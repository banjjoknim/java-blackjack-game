package domain.user;

import domain.card.Deck;

public class Player extends User {

    private final Name name;
    private final BettingMoney bettingMoney;

    public Player(Name name, BettingMoney bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void hit(Deck deck) {
        deck.distributeCard(this);
    }

    @Override
    public boolean isWait() {
        return !super.hand.hasEnded();
    }

    public Name getName() {
        return name;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
