package domain.user.state;

public class Blackjack extends Ended {
    @Override
    public boolean isEnded() {
        return true;
    }
}
