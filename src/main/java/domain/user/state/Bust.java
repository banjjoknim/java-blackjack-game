package domain.user.state;

public class Bust extends State {
    @Override
    public State findNextState(int score, boolean isInitialCards) {
        throw new IllegalStateException("차례가 끝났습니다.");
    }
}
