package domain.user.state;

import domain.card.Card;

import java.util.List;

public abstract class Ended extends State {

    public Ended(List<Card> cards) {
        super(cards);
    }

    @Override
    public State findNextState() {
        throw new IllegalStateException("차례가 끝났습니다.");
    }
}
