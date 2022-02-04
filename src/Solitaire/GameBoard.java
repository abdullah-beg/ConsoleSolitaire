package Solitaire;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {
    
    private ArrayList<Card> cards;
    private ArrayList<Pile> piles;
    private Stock stock;
    private Waste waste;

    public GameBoard() {

        cards = new ArrayList<>();
        piles = new ArrayList<>();
        waste = new Waste();
        stock = new Stock();

        for(int i = 0; i < 7; i++) {
            piles.add(new Pile());

        }

        generateCards();
        Collections.shuffle(cards);
        prepareCardLayout();
        showFrontCard();
        stock.setStock(cards);

    }

    private void generateCards() {

        String suits = "ABCD";
        String colour = "black";

        for (int suit = 0; suit < 4; suit++) {
            for (int card = 1; card < 14; card++) {
                if (suit >= 2) {
                    colour = "red";
                    
                }

                cards.add(new Card(suits.substring(suit, suit + 1), card, colour));

            }

        }

    }

    private void prepareCardLayout() {

        for (int pile = 0; pile < 7; pile++) {
            for (int card = 0; card <= pile; card++) {
                getPile(pile).addCardToPile(cards.get(card));
                cards.remove(card);

            }

        }

    }

    private void showFrontCard() {

        for (int pile = 0; pile < 7; pile++) {
            Pile currentPile = getPile(pile);
            currentPile.getCardAtIndex(currentPile.getCardCount() - 1).setVisible(true);

        }

    }

    public Stock getStock() {

        return stock;

    }

    public Waste getWaste() {

        return waste;

    }

    public Pile getPile(int index) {

        return piles.get(index);

    }

}
