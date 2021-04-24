package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    
    private final PlayerName name;
    private final BettingMoney bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(PlayerName name, BettingMoney bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public PlayerName getName() {
        return name;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}