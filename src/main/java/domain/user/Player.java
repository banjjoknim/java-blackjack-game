package domain.user;

import domain.user.state.State;
import domain.user.state.Wait;

import java.util.ArrayList;

public class Player extends User {

    private final Name name;
    private final BettingMoney bettingMoney;

    public Player(Name name, BettingMoney bettingMoney) {
        this(name, bettingMoney, new Wait(new ArrayList<>()));
    }

    public Player(Name name, BettingMoney bettingMoney, State state) {
        super(state);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void stay() {
        this.state = state.toStay();
    }

    @Override
    public boolean isWait() {
        return !super.state.isEnded();
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    public Name getName() {
        return name;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
