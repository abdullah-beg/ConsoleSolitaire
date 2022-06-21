package Solitaire;

import java.util.ArrayList;

public class Stock extends Pile {

    public Stock() {

        cards = new ArrayList<>();

    }

    public Stock(ArrayList<Card> cards) {

        this.cards = cards;

    }

    public void setStock(ArrayList<Card> remainingCards) {

        cards = remainingCards;

    }

    public void returnCardToStock(Card card) {

        card.setVisible(false);
        cards.add(card);

    }

}
