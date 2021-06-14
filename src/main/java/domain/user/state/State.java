package domain.user.state;

import domain.card.Cards;

public abstract class State {

    private Cards cards = new Cards();

    private static final int BLACK_JACK_NUMBER = 21;

    public State determineState() {
        boolean isInitialCards = cards.isInitialCards();
        int totalCardNumber = cards.calculateTotalCardNumber();
        if (totalCardNumber > BLACK_JACK_NUMBER) {
            return new Bust();
        }
        if (isInitialCards && totalCardNumber == BLACK_JACK_NUMBER) {
            return new BlackJack();
        }
        if (totalCardNumber < BLACK_JACK_NUMBER) {
            return new Survival();
        }
        throw new IllegalStateException("현재 상태는 정의되지 않은 상태입니다.");
    }
}
