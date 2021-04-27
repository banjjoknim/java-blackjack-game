package domain.user;

import domain.card.Card;
import domain.card.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {
    private static final int ACE_AS_ELEVEN = 10;
    private static final int BLACK_JACK = 21;
    private static final int INITIAL_CARDS_SIZE = 2;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackJack() {
        return hasAceTypeCard() && hasTenNumberTypeCard() && cards.size() == INITIAL_CARDS_SIZE;
    }

    private boolean hasAceTypeCard() {
        return cards.stream()
                .anyMatch(Card::isAceType);
    }

    private boolean hasTenNumberTypeCard() {
        return cards.stream()
                .anyMatch(Card::isTenNumberType);
    }

    public boolean isBust() {
        return calculateTotalCardNumber() > BLACK_JACK;
    }

    public int calculateTotalCardNumber() {
        int totalCardNumber = cards.stream()
                .map(Card::getType)
                .mapToInt(Type::getNumber)
                .sum();
        if (isBlackJack()) {
            return BLACK_JACK;
        }
        if (hasAceTypeCard() && totalCardNumber + ACE_AS_ELEVEN < BLACK_JACK) {
            return totalCardNumber + ACE_AS_ELEVEN;
        }
        return totalCardNumber;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
