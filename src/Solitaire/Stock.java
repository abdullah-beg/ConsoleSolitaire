package Solitaire;

import java.util.ArrayList;

public class Stock extends Pile {

    public Stock(ArrayList<Card> remainingCards) {
        cards = remainingCards;

    }

    public void returnCardToStock(Card card, Pile pile) {

        card.setVisible(false);
        cards.add(card);
        pile.removeCardAtIndex(pile.getCardCount() - 1);

    }

}
