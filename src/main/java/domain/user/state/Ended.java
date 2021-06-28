package domain.user.state;

import domain.card.Card;
import domain.result.GameResult;

import java.util.List;

public abstract class Ended extends State {

    public Ended(List<Card> cards) {
        super(cards);
    }

    public abstract GameResult findResult(State state);

    @Override
    public State toStay() {
        throw new IllegalStateException("차례가 끝난 상태이므로 스테이 상태를 반환할 수 없습니다.");
    }

    @Override
    public State findNextState() {
        throw new IllegalStateException("차례가 끝났습니다.");
    }

    @Override
    public boolean isEnded() {
        return true;
    }
}
