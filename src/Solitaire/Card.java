package Solitaire;

public class Card {

    private String suit;
    private int number;
    private String colour;
    private boolean visible;


    public Card(String suit, int number, String colour) {
        this.colour = colour;
        this.suit = suit;
        this.number = number;
        visible = false;

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
