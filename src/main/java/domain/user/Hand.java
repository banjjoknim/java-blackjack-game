package domain.user;

import domain.card.Card;
import domain.card.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand { // 영문을 의역하면 `Hand`는 '손패' 라는 의미도 갖는다고 함.
    private static final int BLACKJACK = 21;
    private static final int ACE_IS_ELEVEN = 10;

    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
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

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
