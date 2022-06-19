package Solitaire;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {
    
    private ArrayList<Card> cards;
    private Pile[] tablePiles;
    private Stock stockPile;
    private Waste wastePile;
    private Foundation[] foundationPiles;

    public GameBoard() {

        cards = new ArrayList<>();
        tablePiles = new Pile[7];
        wastePile = new Waste();
        stockPile = new Stock();
        foundationPiles = new Foundation[4];

        for(int i = 0; i < 7; i++) {
            tablePiles[i] = new Pile();

        }

        for(int i = 0; i < 4; i++) {
            foundationPiles[i] = new Foundation();

        }

        generateCards();
        Collections.shuffle(cards);
        prepareCardLayout();
        showFrontCard();
        stockPile.setStock(cards);

    }

    private void generateCards() {

        String suits = "#$%@";
        String colour = "black";

        for (int suit = 0; suit < 4; suit++) {
            for (int cardNumber = 1; cardNumber < 14; cardNumber++) {
                if (suit >= 2) {
                    colour = "red";
                    
                }

                cards.add(new Card(cardNumber, suits.substring(suit, suit + 1), colour));

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

    public void showFrontCard() {

        for (int pile = 0; pile < 7; pile++) {
            Pile currentPile = getPile(pile);
            if (currentPile.getCardCount() > 0) {
                currentPile.getCardAtIndex(currentPile.getCardCount() - 1).setVisible(true);

            }

        }

    }

    public Stock getStock() {

        return stockPile;

    }

    public Waste getWaste() {

        return wastePile;

    }

    public Pile getPile(int index) {

        return tablePiles[index];

    }

    public Pile[] getPiles() {

        return tablePiles;

    }

    public Foundation getFoundation(int index) {

        return foundationPiles[index];

    }

    public Foundation[] getFoundations() {

        return foundationPiles;

    }

    public Pile locatePile(String input) {

        String pile = input.substring(0,1);

        if (pile.equals("w")) {
            return getWaste();

        } else if (pile.equals("f")) {
            int index = Integer.parseInt(input.substring(1,2));
            return getFoundation(index - 1);
        
        }

        // If it has reached here, it means that its looking for a table pile
        
        int index = Integer.parseInt(input.substring(1,2));
        return getPile(index - 1);

    }

}
