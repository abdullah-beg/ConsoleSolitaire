package Solitaire;

import java.util.ArrayList;

public class Pile {
    
    protected ArrayList<Card> cards;

    /**
     * Constructor for Pile
     */
    public Pile() {

        cards = new ArrayList<>();

    }

    /**
     * Constructor for Pile with a given list of cards.
     * @param cards The cards within the pile.
     */
    public Pile(ArrayList<Card> cards) {


        this.cards = cards;

    }

    /**
     * Get the number of cards in the pile.
     * @return Number of cards in the pile.
     */
    protected int getCardCount() {

        return cards.size();

    }

    /**
     * Add a card to a pile.
     * @param card The card to be added.
     */
    protected void addCardToPile(Card card) {

        cards.add(card);

    }

    /**
     * Get the card at an index inside a pile.
     * @param index The index inside the pile.
     * @return The card at the given index. Null if size is 0.
     */
    protected Card getCardAtIndex(int index) {

        if (cards.size() > 0) {
            return cards.get(index);

        }

        return null;        

    }

    /**
     * Remove the card at the given index.
     * @param index The index of which card to remove.
     */
    protected void removeCardAtIndex(int index) {

        cards.remove(index);

    }

    /**
     * Getter for the cards field.
     * @return The cards in the pile as an ArrayList.
     */
    protected ArrayList<Card> getCardsInPile() {

        return cards;

    }

    /**
     * Check whether a pile is empty.
     * @return boolean dictating whether the pile is empty.
     */
    protected boolean isEmpty() {

        return cards.size() == 0;

    }

    /**
     * Get the card at the bottom of the pile.
     * @return The card at the bottom of the pile. Null if the size is 0.
     */
    protected Card getBottomCard() {

        if (cards.size() > 0) {
            return cards.get(cards.size() - 1);

        }

        return null;        

    }

    /**
     * Remove the card from the bottom of the pile.
     */
    protected void removeCardFromBottom() {

        cards.remove(cards.size() - 1);

    }

    /**
     * Get all the visible cards in the Pile.
     * @return The visible cards in the pile.
     */
    protected ArrayList<Card> getVisibleCards() {

        ArrayList<Card> visibleCards = new ArrayList<>();

        for (Card card : cards) {
            if (card.getCardVisible()) {
                visibleCards.add(card);

            }

        }

        return visibleCards;

    }

    /**
     * Get all the hidden cards in the pile.
     * @return The hidden cards in the pile.
     */
    protected ArrayList<Card> getHiddenCards() {

        ArrayList<Card> hiddenCards = new ArrayList<>();

        for (Card card : cards) {
            if (!card.getCardVisible()) {
                hiddenCards.add(card);

            }

        }

        return hiddenCards;

    }

}
