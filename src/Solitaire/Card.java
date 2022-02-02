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


    public void setVisible(boolean bool) {

        visible = bool;

    }



    /*
        Accessor Methods
    */

    public String getCardSuit() {

        return suit;

    }

    public int getCardNumber() {

        return number;

    }

    public String getCardColour() {

        return colour;

    }

    public boolean getCardVisibility() {

        return visible;

    }
}
