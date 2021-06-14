package domain.user;

import domain.card.Card;

import java.util.List;

public class Player extends User {

    private final String name;
    private final double bettingMoney;

    public Player(List<Card> cards, String name, double bettingMoney) {
        super(cards);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

}
