package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private static final int INITIAL_SUM_OF_CARD_NUMBERS = 0;
    private static final int INITIAL_CARDS_SIZE = 2;
    private static final int TEN = 10;
    private static final int ACE_AND_TEN = 11;
    private static final int BLACK_JACK = 21;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackJack() {
        return calculateSumOfCardNumbers() == BLACK_JACK && cards.size() == INITIAL_CARDS_SIZE;
    }

    public boolean isBust() {
        return calculateSumOfCardNumbers() > BLACK_JACK;
    }

    public int calculateSumOfCardNumbers() {
        int sumOfCardNumbers = INITIAL_SUM_OF_CARD_NUMBERS;
        for (Card card : cards) {
            sumOfCardNumbers = sumOfCardNumbers + card.getType().getNumber();
        }
        if (cards.size() == INITIAL_CARDS_SIZE && sumOfCardNumbers == ACE_AND_TEN) {
            sumOfCardNumbers = sumOfCardNumbers + TEN;
        }
        return sumOfCardNumbers;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
