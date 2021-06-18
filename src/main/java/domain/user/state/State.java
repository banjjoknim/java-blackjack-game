package domain.user.state;

import domain.card.Card;
import domain.card.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class State {
    protected static final int BLACKJACK = 21;
    private static final int ACE_IS_ELEVEN = 10;
    private static final int INITIAL_CARDS_SIZE = 2;

    protected List<Card> cards;

    public State(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int sum = cards.stream()
                .map(Card::getType)
                .mapToInt(Type::getScore)
                .sum();
        if (sum + ACE_IS_ELEVEN <= BLACKJACK && hasAceCard()) {
            sum = sum + ACE_IS_ELEVEN;
        }
        return sum;
    }

    private boolean hasAceCard() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isInitialCards() {
        return cards.size() == INITIAL_CARDS_SIZE;
    }

    public abstract State findNextState();

    public abstract boolean isEnded();

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
