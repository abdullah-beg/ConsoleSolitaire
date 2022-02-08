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

        if (cards.size() > 0) {
            return cards.get(cards.size() - 1);

        }

        return null;        

    }

    public void removeCardFromBottom() {

        cards.remove(cards.size() - 1);

    }

    public ArrayList<Card> getVisibleCards() {

        ArrayList<Card> visibleCards = new ArrayList<>();

        for (Card card : cards) {
            if (card.getCardVisible()) {
                visibleCards.add(card);

            }

        }

        return visibleCards;

    }

    public ArrayList<Card> getHiddenCards() {

        ArrayList<Card> hiddenCards = new ArrayList<>();

        for (Card card : cards) {
            if (!card.getCardVisible()) {
                hiddenCards.add(card);

            }

        }

        return hiddenCards;

    }

    public void removeVisibleCards() {

        for (Card card : cards) {
            if (card.getCardVisible()) {
                cards.remove(card);

            }

        }

    }

}
