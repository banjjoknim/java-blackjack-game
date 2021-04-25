package domain.user;

import domain.card.Card;
import domain.card.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    private final List<Card> cards = new ArrayList<>();

    public void drawCard() {
        cards.add(Deck.giveCardOnTop());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
