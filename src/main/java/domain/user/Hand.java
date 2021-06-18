package domain.user;

import domain.card.Card;
import domain.card.Type;
import domain.user.state.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand { // 영문을 의역하면 `Hand`는 '손패' 라는 의미도 갖는다고 함.
    public static final int BLACKJACK = 21;
    private static final int ACE_IS_ELEVEN = 10;
    private static final int INITIAL_CARDS_SIZE = 2;

    private State state = new Wait();
    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean hasEnded() {
        return state.isEnded();
    }

    public void changeState() {
        int score = calculateScore();
        boolean isInitialCards = isInitialCards();
        state = state.findNextState(score, isInitialCards);
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

    private boolean isInitialCards() {
        return cards.size() == INITIAL_CARDS_SIZE;
    }

    public State getState() {
        return state;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
