package Solitaire;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private ArrayList<Card> cards;
    private GameBoard game;
    private Parser parser;

    private Main() {

        parser = new Parser();

        cards = new ArrayList<Card>();
        generateCards();
        game = new GameBoard();

        Collections.shuffle(cards); // List of cards is shuffled
        prepareCardLayout(); // Place randomised cards in piles, such that you have 1 2 3 4 5 6 7 cards
        game.getStock().setStock(cards); // Remaining cards put into stock
        showFrontCard(); // Shows the bottom most card in each pile

    }

    /* 
        This method generates all the cards. Numbered from 1 to 13, and with suit A, B, C or D
    */
    private void generateCards() {

        String suits = "ABCD";
        String colour = "black";

        for (int suit = 0; suit < 4; suit++) {
            for (int card = 1; card < 14; card++) {
                if (suit == 2 || suit == 3) {
                    colour = "red";
                    
                }
                cards.add(new Card(suits.substring(suit, suit + 1), card, colour));

            }

        }

    }

    private void prepareCardLayout() {

        for (int pile = 0; pile < 7; pile++) {
            for (int card = 0; card <= pile; card++) {
                game.getPile(pile).addCardToPile(cards.get(card));
                cards.remove(card);

            }

        }

    }

    private void showFrontCard() {

        for (int pile = 0; pile < 7; pile++) {
            Pile currentPile = game.getPile(pile);
            currentPile.getCardAtIndex(currentPile.getCardCount() - 1).setVisible(true);

        }

    }

    private boolean gameFinished() {

        for (int pile = 0; pile < 7; pile++) {
            for (Card card : game.getPile(pile).getCardsInPile()) {
                if (!card.getCardVisible()) {
                    return false;

                }

            }

        }

        return true;

    }

    public static void main(String[] args) {

        Main main = new Main();

        while (!main.gameFinished()) {

            Command command = main.parser.getUserCommand();

            if (command.validateCommand()) {

                System.out.println("valid");

            }


        }

    }

}