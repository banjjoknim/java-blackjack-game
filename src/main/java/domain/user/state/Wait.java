package domain.user.state;

import domain.card.Card;

import java.util.List;

public class Wait extends State {

    public Wait(List<Card> cards) {
        super(cards);
    }

    @Override
    public State findNextState() {
        int score = super.calculateScore();
        boolean isInitialCards = super.isInitialCards();
        if (score > BLACKJACK) {
            return new Bust(cards);
        }
        if (score == BLACKJACK && isInitialCards) {
            return new Blackjack(cards);
        }
        if (score == BLACKJACK) {
            return new Stay(cards);
        }
        return new Wait(cards);
    }

    @Override
    public boolean isEnded() {
        return false;
    }
}
