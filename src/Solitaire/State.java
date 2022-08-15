package Solitaire;

import java.util.ArrayList;

public class State {

    private ArrayList<String> waste, stock;
    private ArrayList<ArrayList<String>> foundations;
    private ArrayList<ArrayList<String>> piles;

    /**
     * Constructor for State
     * @param waste The Waste pile.
     * @param stock The Stock pile.
     * @param foundations The Foundation piles.
     * @param piles The Table piles.
     */
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
        
    }

    /**
     * Converts the cards in stored Waste, into a new Waste object.
     * @return new Waste object with cards.
     */
    public Waste convertWaste() {

        return new Waste(convertStringToCard(this.waste));

    }

    /**
     * Converts the cards in stored Stock, into a new Stock object.
     * @return new Stock object with cards.
     */
    public Stock convertStock() {

        return new Stock(convertStringToCard(this.stock));

    }

    /**
     * Converts the cards in the stored Foundations, into a new Foundation array.
     * @return new Foundation array with cards.
     */
    public Foundation[] convertFoundations() {

        Foundation[] foundationPiles = new Foundation[4];
        for (int index = 0; index < 4; index++) {
            foundationPiles[index] = new Foundation(convertStringToCard(this.foundations.get(index)));

        }

        return foundationPiles;

    }

    /**
     * Convert the cards in the stored Table Piles, into a new Pile array.
     * @return new Pile array with cards.
     */
    public Pile[] convertTablePiles() {

        Pile[] tablePiles = new Pile[7];
        for (int index = 0; index < 7; index++) {
            tablePiles[index] = new Pile(convertStringToCard(this.piles.get(index)));

        }

        return tablePiles;

    }

    /**
     * Convert an ArrayList of Cards into an ArrayList of String.
     * @param cards The cards to be converted.
     * @return ArrayList of cards in String format.
     */
    public ArrayList<String> convertCardToString(ArrayList<Card> cards) {

        ArrayList<String> output = new ArrayList<>();

        for (Card c : cards) {
            output.add(c.toString());
            // Example "10-@-black-false"

        }

        return output;

    }

    /**
     * Convert an ArrayList of Strings to an ArrayList of Cards.
     * @param cardsString The Strings to be converted.
     * @return ArrayList of Cards in Card format.
     */
    public ArrayList<Card> convertStringToCard(ArrayList<String> cardsString) {

        ArrayList<Card> cards = new ArrayList<>();

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

}
