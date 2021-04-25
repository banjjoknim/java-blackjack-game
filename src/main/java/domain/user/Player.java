package domain.user;

import domain.card.Card;
import domain.card.Cards;

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

    public void drawCard() {
        cards.add(Cards.removeCardOnTop());
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