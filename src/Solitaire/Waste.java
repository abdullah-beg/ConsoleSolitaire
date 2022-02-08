package Solitaire;

import java.util.ArrayList;

public class Waste extends Pile {

    private Card backCard;
    private Card midCard;
    private Card frontCard;
    
    public Waste() {

        cards = new ArrayList<>();

    }

    public void setCardOrder() {

        frontCard = null;
        midCard = null;
        backCard = null;

        if (cards.size() == 0) {
            return;
            

        } else if (cards.size() == 1) {
            frontCard = cards.get(0); 

        } else if (cards.size() == 2) {
            frontCard = cards.get(1);
            backCard = cards.get(0);

        } else {

            // If there are three or more cards inside of waste

            frontCard = cards.get(cards.size() - 1);
            midCard = cards.get(cards.size() - 2);
            backCard = cards.get(cards.size() - 3);

        }

    }

    public Card getFrontCard() {

        return frontCard;

    }

    public Card getMidCard() {

        return midCard;

    }

    public Card getBackCard() {

        return backCard;

    }

    public void takeCardFromStock(Card card) {

        card.setVisible(true);
        cards.add(card);

    }

}
