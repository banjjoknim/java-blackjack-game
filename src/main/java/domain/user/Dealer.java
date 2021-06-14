package domain.user;

import domain.card.Card;

import java.util.List;

public class Dealer extends User {

    public Dealer(List<Card> cards) {
        super(cards);
    }

}
