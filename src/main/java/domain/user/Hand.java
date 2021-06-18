package domain.user;

import domain.card.Card;
import domain.user.state.State;
import domain.user.state.Wait;

import java.util.ArrayList;

public class Hand { // 영문을 의역하면 `Hand`는 '손패' 라는 의미도 갖는다고 함.

    private State state = new Wait(new ArrayList<>());

    public void addCard(Card card) {
        state.add(card);
    }

    public boolean hasEnded() {
        return state.isEnded();
    }

    public void update() {
        state = state.findNextState();
    }

    public State getState() {
        return state;
    }

}
