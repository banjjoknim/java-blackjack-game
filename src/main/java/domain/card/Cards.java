package domain.card;

import domain.user.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int INITIAL_CARDS_SIZE = 2;
    private static final int BLACK_JACK = 21;
    private static final int ACE_AS_ELEVEN = 10;

    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public Status getStatus() {
        int totalCardNumber = calculateTotalCardNumber();
        boolean canBlackJack = cards.size() == INITIAL_CARDS_SIZE;
        return Status.determineStatus(totalCardNumber, canBlackJack);
    }

    public int calculateTotalCardNumber() {
        int totalCardNumber = cards.stream()
                .map(Card::getType)
                .mapToInt(Type::getNumber)
                .sum();
        if (hasAceTypeCard()) {
            totalCardNumber = determineAceNumberIsOneOrEleven(totalCardNumber);
        }
        return totalCardNumber;
    }

    private boolean hasAceTypeCard() {
        return cards.stream()
                .anyMatch(Card::isAceType);
    }

    private int determineAceNumberIsOneOrEleven(int totalCardNumber) {
        if (totalCardNumber + ACE_AS_ELEVEN <= BLACK_JACK) {
            return totalCardNumber + ACE_AS_ELEVEN;
        }
        return totalCardNumber;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
