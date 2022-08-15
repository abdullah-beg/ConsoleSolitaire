package Solitaire;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {
    
    private ArrayList<Card> cards;
    private Pile[] tablePiles;
    private Stock stockPile;
    private Waste wastePile;
    private Foundation[] foundationPiles;

    /**
     * Constructor for GameBoard. Creates new piles for each part of the table.
     */
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

    /**
     * Generates all the possible cards.
     */
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

    /**
     * Sets the card in the Table Piles.
     */
    private void prepareCardLayout() {

        for (int pile = 0; pile < 7; pile++) {
            for (int card = 0; card <= pile; card++) {
                getPile(pile).addCardToPile(cards.get(card));
                cards.remove(card);

            }

        }

    }

    /**
     * Shows the most bottom card on each pile.
     */
    public void showFrontCard() {

        for (int pile = 0; pile < 7; pile++) {
            Pile currentPile = getPile(pile);
            if (currentPile.getCardCount() > 0) {
                currentPile.getCardAtIndex(currentPile.getCardCount() - 1).setVisible(true);

            }

        }

    }

    /**
     * Getter for stock field.
     * @return Returns the stockPile field.
     */
    public Stock getStock() {

        return stockPile;

    }

    /**
     * Setter for the stockPile field.
     * @param stockPile The new stockPile field object.
     */
    public void setStock(Stock stockPile) {

        this.stockPile = stockPile;

    }

    /**
     * Getter for the wastePile field.
     * @return The wastePile field.
     */
    public Waste getWaste() {

        return wastePile;

    }

    /**
     * Setter for the wastePile field.
     * @param wastePile The new wastePile field object.
     */
    public void setWaste(Waste wastePile) {

        this.wastePile = wastePile;

    }

    /**
     * Get the table pile at the index.
     * @param index index of the pile. 0 - 6.
     * @return The pile at the given index.
     */
    public Pile getPile(int index) {

        return tablePiles[index];

    }

    /**
     * Get all the table piles as an array.
     * @return Array of all Table Piles.
     */
    public Pile[] getPiles() {

        return tablePiles;

    }

    /**
     * Setter for tablePiles.
     * @param tablePiles the new tablePile object.
     */
    public void setPiles(Pile[] tablePiles) {

        this.tablePiles = tablePiles;

    }

    /**
     * Get the foundation pile at the given index.
     * @param index The index of the pile. 0 - 3.
     * @return The foundation pile at the given index.
     */
    public Foundation getFoundation(int index) {

        return foundationPiles[index];

    }

    /**
     * Get all the foundations piles.
     * @return Array of the Foundation piles.
     */
    public Foundation[] getFoundations() {

        return foundationPiles;

    }

    /**
     * Setter for the foundationPiles field.
     * @param foundationPiles The new foundationPiles object.
     */
    public void setFoundations(Foundation[] foundationPiles) {

        this.foundationPiles = foundationPiles;

    }

    /**
     * Set the current state to the new state.
     * @param state The new State to be set to.
     */
    public void setState(State state) {

        setWaste(state.convertWaste());
        setStock(state.convertStock());
        setFoundations(state.convertFoundations());
        setPiles(state.convertTablePiles());

    }

}
