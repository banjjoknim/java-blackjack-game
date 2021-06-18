package domain.user.state;

public abstract class State {

    public abstract State findNextState(int score, boolean isInitialCards);

    public abstract boolean isEnded();
}
