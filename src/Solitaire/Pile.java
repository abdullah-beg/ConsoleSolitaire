package Solitaire;

import java.util.ArrayList;

public class Pile {
    
    protected ArrayList<Card> cards;

    public Pile() {

        cards = new ArrayList<Card>();

    }

    public int getCardCount() {

        return cards.size();

    }

    public void addCardToPile(Card card) {

        cards.add(card);

    }

    public void removeCardFromPile(Card card) {

        

    }

    public Card getCardAtIndex(int index) {

        return cards.get(index);

    }

}
