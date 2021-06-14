package domain.user.state;

import domain.card.Card;
import domain.card.Cards;

public class BlackJack extends State {
    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("블랙잭인 상태에서는 카드를 더 뽑을 수 없습니다.");
    }
}
