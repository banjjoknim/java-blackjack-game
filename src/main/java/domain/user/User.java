package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User implements JudgeAble {
    private static final int INITIAL_CARDS_SIZE = 2;
    private static final int BLACK_JACK = 21;
    private static final int ACE_AS_ELEVEN = 10;

    private final List<Card> cards = new ArrayList<>();
    private boolean isStay;

    public void addCard(Card card) {
        cards.add(card);
    }

    public void hit(Deck deck) {
        deck.distributeCard(this);
    }

    public boolean isStay() {
        return isStay;
    }

    public void stay() {
        isStay = true;
    }

    public Status determineStatus() {
        if (isBlackJack()) {
            return Status.BLACK_JACK;
        }
        if (isBust()) {
            return Status.BUST;
        }
        return Status.SURVIVAL;
    }

    public int calculateTotalCardNumber() {
        int totalCardNumber = cards.stream()
                .map(Card::getType)
                .mapToInt(Type::getNumber)
                .sum();
        if (hasAceTypeCard()) {
            return considerAceTypeCard(totalCardNumber);
        }
        return totalCardNumber;
    }

    private boolean hasAceTypeCard() {
        return cards.stream()
                .anyMatch(Card::isAceType);
    }

    private int considerAceTypeCard(int totalCardNumber) {
        if (isBlackJack()) {
            return BLACK_JACK;
        }
        return determineAceNumberIsOneOrEleven(totalCardNumber);
    }

    private int determineAceNumberIsOneOrEleven(int totalCardNumber) {
        if (totalCardNumber + ACE_AS_ELEVEN < BLACK_JACK) {
            return totalCardNumber + ACE_AS_ELEVEN;
        }
        return totalCardNumber;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public boolean isBust() {
        return calculateTotalCardNumber() > BLACK_JACK;
    }

    @Override
    public boolean isBlackJack() {
        return hasAceTypeCard() && hasTenNumberTypeCard() && cards.size() == INITIAL_CARDS_SIZE;
    }

    private boolean hasTenNumberTypeCard() {
        return cards.stream()
                .anyMatch(Card::isTenNumberType);
    }

}
