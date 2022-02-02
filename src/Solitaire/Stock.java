package Solitaire;

import java.util.ArrayList;

public class Stock extends Pile {

    public Stock(ArrayList<Card> remainingCards) {
        cards = remainingCards;

    }

    // private void hideAllCards() {

    //     for (Card card : cards) {
    //         card.setVisible(false);

    //     }

    // }

}
