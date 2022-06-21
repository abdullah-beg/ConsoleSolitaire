package Solitaire;

import java.util.ArrayList;

public class State {
    
    private ArrayList<String> waste, stock;
    private ArrayList<ArrayList<String>> foundations;
    private ArrayList<ArrayList<String>> piles;

    private Waste wastePile;
    private Stock stockPile;
    private Foundation[] foundationPiles;
    private Pile[] tablePiles;

    public State(Waste waste, Stock stock, Foundation[] foundations, Pile[] piles) {

        this.waste = convertCardToString(waste.getCardsInPile());
        this.stock = convertCardToString(stock.getCardsInPile());

        this.foundations = new ArrayList<>();
        for (Foundation f : foundations) {
            this.foundations.add(convertCardToString(f.getCardsInPile()));

        }

        this.piles = new ArrayList<>();
        for (Pile p : piles) {
            this.piles.add(convertCardToString(p.getCardsInPile()));

        }

        wastePile = new Waste(convertStringToCard(this.waste));
        stockPile = new Stock(convertStringToCard(this.stock));
        
        foundationPiles = new Foundation[4];
        for (int index = 0; index < 4; index++) {
            foundationPiles[index] = new Foundation(convertStringToCard(this.foundations.get(index)));

        }

        tablePiles = new Pile[7];
        for (int index = 0; index < 7; index++) {
            tablePiles[index] = new Pile(convertStringToCard(this.piles.get(index)));

        }
        
    }

    public ArrayList<String> convertCardToString(ArrayList<Card> cards) {

        ArrayList<String> output = new ArrayList<>();

        for (Card c : cards) {
            output.add(c.getCardNumber() + "-" + c.getCardSuit() + "-" + c.getCardColour() + "-" + c.getCardVisible());
            // Example "10-@-black-false"

        }

        return output;

    }

    public ArrayList<Card> convertStringToCard(ArrayList<String> cardsString) {

        ArrayList<Card> cards = new ArrayList<>();

        if (cardsString.size() == 0) {

            System.out.println("wow");

        }

        for (String s : cardsString) {
            String[] split = s.split("-");

            cards.add(new Card
            (
                Integer.parseInt(split[0]),
                split[1],
                split[2],
                Boolean.parseBoolean(split[3])
            ));

        }

        return cards;

    }

    public Waste getWastePile() {

        return wastePile;

    }

    public Stock getStockPile() {

        return stockPile;

    }

    public Foundation[] getFoundationPiles() {

        return foundationPiles;

    }

    public Pile[] getTablePiles() {

        return tablePiles;

    }

}
