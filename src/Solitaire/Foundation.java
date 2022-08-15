package Solitaire;

import java.util.ArrayList;

public class Foundation extends Pile {

    // Foundations have a suit field for which cards they will hold
    private String suit;

    /**
     * Constructor for foundation. Empty list of cards. And no suit.
     */
    public Foundation() {
        cards = new ArrayList<>();
        suit = "";

    }

    /**
     * Constructor for Foundation, takes a list of cards.
     * Sets the suit to the cards its currently holding.
     * @param cards ArrayList of Card which are being added.
     */
    public Foundation(ArrayList<Card> cards) {

        if (cards.size() != 0) {
            this.cards = cards;
            setSuit(cards.get(0).getCardSuit());
    
        } else {
            cards = new ArrayList<>();
            suit = "";

        }

    }

    /**
     * Set the suit field to the string.
     * @param suit The suit to be set to, this pile will only hold this suit of card.
     */
    public void setSuit(String suit) {

        this.suit = suit;

    }

    /**
     * Get the suit of this pile.
     * @return String which represents the suit of the pile.
     */
    public String getSuit() {

        return suit;

    }

}
