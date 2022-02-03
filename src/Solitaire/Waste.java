package Solitaire;

import java.util.ArrayList;

public class Waste extends Pile {
    
    public Waste() {

        cards = new ArrayList<>();

    }

    public void takeCardFromStock(Card card) {

        card.setVisible(true);
        cards.add(card);

    }

}
