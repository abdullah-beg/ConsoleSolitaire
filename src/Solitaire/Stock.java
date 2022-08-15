package Solitaire;

import java.util.ArrayList;

public class Stock extends Pile {

    /**
     * Constructor for Stock.
     */
    public Stock() {

        cards = new ArrayList<>();

    }

    /**
     * Constructor for Stock which takes a list of cards.
     * @param cards The cards to be added to the Stock.
     */
    public Stock(ArrayList<Card> cards) {

        this.cards = cards;

    }

    /**
     * Setter for the cards in the stock.
     * @param remainingCards The cards that are in the stock.
     */
    public void setStock(ArrayList<Card> remainingCards) {

        cards = remainingCards;

    }

    /**
     * Returning a card into a stock. It is now hidden.
     * @param card The Card to be added to the Stock.
     */
    public void returnCardToStock(Card card) {

        card.setVisible(false);
        cards.add(card);

    }

}
