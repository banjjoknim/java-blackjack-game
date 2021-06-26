package domain.user;

import domain.user.state.State;
import domain.user.state.Wait;

import java.util.ArrayList;

public class Dealer extends User {
    private static final int DEALER_RULE_SCORE = 17;

    public Dealer() {
        this(new Wait(new ArrayList<>()));
    }

    public Dealer(State state) {
        super(state);
    }

    @Override
    public boolean isWait() {
        if (this.state.calculateScore().isSmallerThan(DEALER_RULE_SCORE)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
