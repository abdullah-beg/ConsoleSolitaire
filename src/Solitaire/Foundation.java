package Solitaire;

import java.util.ArrayList;

public class Foundation extends Pile {

    private String suit;

    public Foundation() {
        cards = new ArrayList<>();
        suit = "";

    }

    public void setSuit(String suit) {

        this.suit = suit;

    }

    public String getSuit() {

        return suit;

    }

}
