package domain.user.state;

import domain.card.Card;
import domain.card.Cards;

public class Survival extends State {

    public Survival(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        super.cards.addCard(card);
        return super.determineState();
    }
}
