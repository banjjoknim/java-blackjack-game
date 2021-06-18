package domain.user.state;

import static domain.user.Hand.BLACKJACK;

public class Wait extends State {

    @Override
    public State findNextState(int score, boolean isInitialCards) {
        if (score > BLACKJACK) {
            return new Bust();
        }
        if (score == BLACKJACK && isInitialCards) {
            return new Blackjack();
        }
        if (score == BLACKJACK) {
            return new Stay();
        }
        return new Wait();
    }

    @Override
    public boolean isEnded() {
        return false;
    }
}
