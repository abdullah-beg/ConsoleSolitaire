package Solitaire;

import java.util.ArrayList;

public class Waste extends Pile {
    
    public Waste() {

        cards = new ArrayList<>();

    }

    public void takeCardFromStock(Card card, Pile pile) {

        cards.add(pile.getCardAtIndex(0));
        pile.removeCardAtIndex(0);
        
    }

}
