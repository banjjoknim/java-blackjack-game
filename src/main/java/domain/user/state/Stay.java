package domain.user.state;

import domain.card.Card;
import domain.card.Cards;

public class Stay extends State {

    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("스테이 상태에서는 카드를 더 뽑을 수 없습니다.");
    }
}
