package Solitaire;

public class Card {

    // Fields
    private String suit;
    private int number;
    private String colour;
    private boolean visible;

    /**
     * Constructor for card
     * @param number The card's value as an integer
     * @param suit The card's suit
     * @param colour The card's colour
     */
    public Card(int number, String suit, String colour) {

        this(number, suit, colour, false);

    }

    /**
     * Constructor for card
     * @param number The card's value as an integer
     * @param suit The card's suit
     * @param colour The card's colour
     * @param visible The card's visibility
     */
    public Card(int number, String suit, String colour, boolean visible) {

        this.number = number;
        this.suit = suit;
        this.colour = colour;
        this.visible = visible;

    }

    /**
     * Returns the card's representation. K, Q, J, A special cases.
     * @return String which represents the cards value.
     */
    public String getCardRepresentation() {

        if (number == 1) {
            return "A"; 
        
        } else if (number == 11) {
            return "J";
            
        } else if (number == 12) {
            return "Q";

        } else if (number == 13) {
            return "K";

        }

        return "" + number;

    }


    /**
     * Sets the card's visibility to bool
     * @param bool card visibility true/false
     */
    public void setVisible(boolean bool) {

        visible = bool;

    }



    // Accessor Methods (Getters)

    /**
     * Getter method for the card's suit.
     * @return The card's suit.
     */
    public String getCardSuit() {

        return suit;

    }

    /**
     * Getter method for the card's number.
     * @return The card's number.
     */
    public int getCardNumber() {

        return number;

    }

    /**
     * Getter method for the card's colour.
     * @return The card's colour.
     */
    public String getCardColour() {

        return colour;

    }

    /**
     * Getter method for the card's visibility.
     * @return The card's visibility (true/false).
     */
    public boolean getCardVisible() {

        return visible;

    }

    /**
     * Convert a card object to String format.
     * @return String which represents card.
     */
    @Override
    public String toString() {

        return "" + number + "-" + suit + "-" + colour + "-" + visible;
        // Example "10-@-black-false"

    }

}
