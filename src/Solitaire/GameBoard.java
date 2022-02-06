package Solitaire;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {
    
    private ArrayList<Card> cards;
    private ArrayList<Pile> tablePiles;
    private Stock stockPile;
    private Waste wastePile;
    private ArrayList<Foundation> foundationPiles;

    public GameBoard() {

        cards = new ArrayList<>();
        tablePiles = new ArrayList<>();
        wastePile = new Waste();
        stockPile = new Stock();
        foundationPiles = new ArrayList<>();

        for(int i = 0; i < 7; i++) {
            tablePiles.add(new Pile());

        }

        for(int i = 0; i < 4; i++) {
            foundationPiles.add(new Foundation());

        }

        generateCards();
        Collections.shuffle(cards);
        prepareCardLayout();
        showFrontCard();
        stockPile.setStock(cards);

    }

    private void generateCards() {

        String suits = "AAAA";
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

    public void showFrontCard() {

        for (int pile = 0; pile < 7; pile++) {
            Pile currentPile = getPile(pile);
            if (currentPile.getCardCount() > 0) {
                currentPile.getCardAtIndex(currentPile.getCardCount() - 1).setVisible(true);
                System.out.println("Number: " + currentPile.getCardAtIndex(currentPile.getCardCount() - 1).getCardNumber() + "     Colour: " + currentPile.getCardAtIndex(currentPile.getCardCount() - 1).getCardColour());

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

        return tablePiles.get(index);

    }

    public Foundation getFoundation(int index) {

        return foundationPiles.get(index);

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
