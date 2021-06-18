package domain.user.state;

public class Stay extends Ended {
    @Override
    public boolean isEnded() {
        return true;
    }
}
