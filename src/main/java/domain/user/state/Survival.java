package domain.user.state;

import domain.card.Card;
import domain.card.Cards;

public class Survival extends State {
    private static final int BLACK_JACK_NUMBER = 21;

    public Survival(Cards cards) {
        super(cards);
    }

    @Override
    public void draw(Card card) {
        super.cards.addCard(card);
    }

    @Override
    public State determineState() {
        boolean isInitialCards = cards.isInitialCards();
        int totalCardNumber = cards.calculateTotalCardNumber();
        if (totalCardNumber > BLACK_JACK_NUMBER) {
            return new Bust(cards);
        }
        if (isInitialCards && totalCardNumber == BLACK_JACK_NUMBER) {
            return new BlackJack(cards);
        }
        if (totalCardNumber <= BLACK_JACK_NUMBER) {
            return new Survival(cards);
        }
        throw new IllegalStateException("현재 상태는 정의될 수 없는 상태입니다.");
    }
}
