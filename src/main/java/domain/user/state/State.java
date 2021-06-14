package domain.user.state;

import domain.card.Card;
import domain.card.Cards;

public abstract class State {

    protected Cards cards;

    public State(Cards cards) {
        this.cards = cards;
    }

    private static final int BLACK_JACK_NUMBER = 21;

    public abstract State draw(Card card);

    public State determineState() {
        boolean isInitialCards = cards.isInitialCards();
        int totalCardNumber = cards.calculateTotalCardNumber();
        if (totalCardNumber > BLACK_JACK_NUMBER) {
            return new Bust(cards);
        }
        if (isInitialCards && totalCardNumber == BLACK_JACK_NUMBER) {
            return new BlackJack(cards);
        }
        if (totalCardNumber < BLACK_JACK_NUMBER) {
            return new Survival(cards);
        }
        throw new IllegalStateException("현재 상태는 정의되지 않은 상태입니다.");
    }

    public int getTotalCardNumber() {
        return cards.calculateTotalCardNumber();
    }

    public Cards getCards() {
        return cards;
    }
}
