package Solitaire;

import java.util.ArrayList;

public class Foundation extends Pile {

    private String suit;

    public Foundation() {
        cards = new ArrayList<>();
        suit = "";

    }

    public Foundation(ArrayList<Card> cards) {

        if (cards.size() != 0) {
            this.cards = cards;
            setSuit(cards.get(0).getCardSuit());
    
        } else {
            cards = new ArrayList<>();
            suit = "";

        }

    }

    public void setSuit(String suit) {

        this.suit = suit;

    }

    public String getSuit() {

        return suit;

    }

}
