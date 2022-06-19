package Solitaire;

import java.util.ArrayList;

public class State {
    
    private ArrayList<String> waste, stock;
    private ArrayList<ArrayList<String>> foundations;
    private ArrayList<ArrayList<String>> piles;

    public State(Waste waste, Stock stock, Foundation[] foundations, Pile[] piles) {

        this.waste = convertListToString(waste.getCardsInPile());
        this.stock = convertListToString(stock.getCardsInPile());

        this.foundations = new ArrayList<>();
        for (Foundation f : foundations) {
            this.foundations.add(convertListToString(f.getCardsInPile()));

        }

        this.piles = new ArrayList<>();
        for (Pile p : piles) {
            this.piles.add(convertListToString(p.getCardsInPile()));

        }

    }

    public ArrayList<String> convertListToString(ArrayList<Card> cards) {

        ArrayList<String> output = new ArrayList<>();

        for (Card c : cards) {
            output.add(c.getCardNumber() + "-" + c.getCardSuit() + "-" + c.getCardColour() + "-" + c.getCardVisible());
            // Example "10-@-black-false"

        }

        return output;

    }

    public ArrayList<Card> convertStringToCard(ArrayList<String> cardsString) {

        ArrayList<Card> cards = new ArrayList<>();

        for (String s : cardsString) {
            String[] split = s.split(" ");

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
