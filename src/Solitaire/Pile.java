package Solitaire;

import java.util.ArrayList;

public class Pile {
    
    protected ArrayList<Card> cards;

    public Pile() {

        cards = new ArrayList<Card>();

    }

    public Pile(ArrayList<Card> cards) {


        this.cards = cards;

    }

    protected int getCardCount() {

        return cards.size();

    }

    protected void addCardToPile(Card card) {

        cards.add(card);

    }

    protected Card getCardAtIndex(int index) {

        if (cards.size() > 0) {
            return cards.get(index);

        }

        return null;        

    }

    protected void removeCardAtIndex(int index) {

        cards.remove(index);

    }

    protected ArrayList<Card> getCardsInPile() {

        return cards;

    }

    protected boolean isEmpty() {

        if (cards.size() == 0) {
            return true;

        }

        return false;

    }

    protected Card getBottomCard() {

        if (cards.size() > 0) {
            return cards.get(cards.size() - 1);

        }

        return null;        

    }

    protected void removeCardFromBottom() {

        cards.remove(cards.size() - 1);

    }

    protected ArrayList<Card> getVisibleCards() {

        ArrayList<Card> visibleCards = new ArrayList<>();

        for (Card card : cards) {
            if (card.getCardVisible()) {
                visibleCards.add(card);

            }

        }

        return visibleCards;

    }

    protected ArrayList<Card> getHiddenCards() {

        ArrayList<Card> hiddenCards = new ArrayList<>();

        for (Card card : cards) {
            if (!card.getCardVisible()) {
                hiddenCards.add(card);

            }

        }

        return hiddenCards;

    }

    protected void removeVisibleCards() {

        for (Card card : cards) {
            if (card.getCardVisible()) {
                cards.remove(card);

            }

        }

    }

}
