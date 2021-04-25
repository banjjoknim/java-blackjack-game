package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private static final int BLACK_JACK = 21;
    private static final int INITIAL_CARDS_SIZE = 2;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean determineIsBlackJack() {
        return calculateSumOfCardNumbers() == BLACK_JACK && cards.size() == INITIAL_CARDS_SIZE;
    }

    public boolean determineIsBust() {
        return calculateSumOfCardNumbers() > BLACK_JACK;
    }

    public int calculateSumOfCardNumbers() {
        return cards.stream()
                .mapToInt(card -> card.getType().getNumber())
                .sum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
