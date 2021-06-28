package domain.user.state;

import domain.card.Card;
import domain.card.Type;
import domain.result.GameResult;
import domain.user.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class State {
    protected static final int BLACKJACK_SCORE = 21;
    private static final int ACE_IS_ELEVEN = 10;
    private static final int INITIAL_CARDS_SIZE = 2;

    protected List<Card> cards;

    public State(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public abstract GameResult findResult(State state);

    protected abstract boolean isBlackjack();

    protected abstract boolean isBust();

    protected abstract boolean isStay();

    public abstract State toStay();

    public void add(Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        int sum = cards.stream()
                .map(Card::getType)
                .mapToInt(Type::getScore)
                .sum();
        if (sum + ACE_IS_ELEVEN <= BLACKJACK_SCORE && hasAceCard()) {
            sum = sum + ACE_IS_ELEVEN;
        }
        return new Score(sum);
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
