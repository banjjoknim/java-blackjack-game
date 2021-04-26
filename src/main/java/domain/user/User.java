package domain.user;

import domain.card.Card;
import domain.card.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    public static final int BLACK_JACK = 21;
    private static final int INITIAL_CARDS_SIZE = 2;
    private static final int ACE_AND_TEN = 11;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackJack() {
        return calculateTotalCardNumber() == BLACK_JACK && cards.size() == INITIAL_CARDS_SIZE;
    }

    public boolean isBust() {
        return calculateTotalCardNumber() > BLACK_JACK;
    }

    public int calculateTotalCardNumber() {
        int sumOfCardNumbers = cards.stream()
                .map(Card::getType)
                .mapToInt(Type::getNumber)
                .sum();
        if (cards.size() == INITIAL_CARDS_SIZE && sumOfCardNumbers == ACE_AND_TEN && hasAce()) {
            return BLACK_JACK;
        }
        return sumOfCardNumbers;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getType().equals(Type.ACE));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
