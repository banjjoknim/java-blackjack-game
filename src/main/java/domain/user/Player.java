package domain.user;

import domain.result.EarningRate;
import domain.result.GameResult;
import domain.user.state.State;
import domain.user.state.Wait;

import java.util.ArrayList;
import java.util.Objects;

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

    public Profit produceProfit(Dealer dealer) {
        GameResult gameResult = produceGameResult(dealer);
        EarningRate earningRate = EarningRate.from(gameResult);
        return bettingMoney.produceProfit(earningRate);
    }

    public GameResult produceGameResult(Dealer dealer) {
        return state.findResult(dealer.state);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
