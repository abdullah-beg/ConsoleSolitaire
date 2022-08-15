package Solitaire;

import java.util.ArrayList;

public class Waste extends Pile {

    private Card backCard;
    private Card midCard;
    private Card frontCard;

    /**
     * Constructor for Waste.
     */
    public Waste() {

        cards = new ArrayList<>();

    }

    /**
     * Constructor for Waste with given cards.
     * @param cards Cards to be added to the waste.
     */
    public Waste(ArrayList<Card> cards) {

        this.cards = cards;
        setCardOrder();

    }

    /**
     * Set the order of the cards in the waste.
     */
    public void setCardOrder() {

        frontCard = null;
        midCard = null;
        backCard = null;

        if (cards.size() == 0) {
            return;
            

        } else if (cards.size() == 1) {
            frontCard = cards.get(0); 

        } else if (cards.size() == 2) {
            frontCard = cards.get(1);
            backCard = cards.get(0);

        } else {

            // If there are three or more cards inside of waste

            frontCard = cards.get(cards.size() - 1);
            midCard = cards.get(cards.size() - 2);
            backCard = cards.get(cards.size() - 3);

        }

    }

    /**
     * Get the card at the front of the waste.
     * @return The card at the front of the waste.
     */
    public Card getFrontCard() {

        return frontCard;

    }

    /**
     * Get the card in the middle of the waste.
     * @return The card in the middle of the waste.
     */
    public Card getMidCard() {

        return midCard;

    }

    /**
     * Get the card at the back of the waste.
     * @return The card in the back of the waste.
     */
    public Card getBackCard() {

        return backCard;

    }

    /**
     * Remove a card from the stock, so it now becomes visible.
     * @param card The card to be removed.
     */
    public void takeCardFromStock(Card card) {

        card.setVisible(true);
        cards.add(card);

    }

}
