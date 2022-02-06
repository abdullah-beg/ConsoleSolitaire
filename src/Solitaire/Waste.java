package Solitaire;

import java.util.ArrayList;

public class Waste extends Pile {

    private Card frontCard;
    
    public Waste() {

        cards = new ArrayList<>();

    }

    public void setFrontCard() {

        if (getCardCount() == 0) {
            frontCard = null;

        } else {
            frontCard = cards.get(getCardCount() - 1);

        }

    }

    public Card getFrontCard() {

        return frontCard;

    }

    public void takeCardFromStock(Card card) {

        card.setVisible(true);
        cards.add(card);

    }

}
