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

        if (cards.size() > 0) {
            return cards.get(index);

        }

        return null;        

    }

    public void removeCardAtIndex(int index) {

        cards.remove(index);

    }

    public ArrayList<Card> getCardsInPile() {

        return cards;

    }

    public boolean isEmpty() {

        if (cards.size() == 0) {
            return true;

        }

        return false;

    }

    public Card getBottomCard() {

        return cards.get(cards.size() - 1);

    }

    public void removeCardFromBottom() {

        cards.remove(cards.size() - 1);

    }

}
