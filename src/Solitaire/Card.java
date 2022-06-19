package Solitaire;

public class Card {

    private String suit;
    private int number;
    private String colour;
    private boolean visible;


    public Card(int number, String suit, String colour) {
        this(number, suit, colour, false);

    }

    public Card(int number, String suit, String colour, boolean visible) {
        this.number = number;
        this.suit = suit;
        this.colour = colour;
        this.visible = visible;

    }

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


    public void setVisible(boolean bool) {

        visible = bool;

    }



    // Accessor Methods (Getters)

    public String getCardSuit() {

        return suit;

    }

    public int getCardNumber() {

        return number;

    }

    public String getCardColour() {

        return colour;

    }

    public boolean getCardVisible() {

        return visible;

    }
}
