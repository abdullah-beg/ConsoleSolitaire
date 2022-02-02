package Solitaire;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private ArrayList<Card> cards;
    private GameBoard game;
    private Stock stock;

    private Main() {
        game = new GameBoard();
        cards = new ArrayList<Card>();
        generateCards();

        Collections.shuffle(cards); // List of cards is shuffled
        prepareCardLayout(); // Place randomised cards in piles, such that you have 1 2 3 4 5 6 7 cards
        showFrontCard(); // Shows the bottom most card in each pile

        stock = new Stock(cards);    

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

    public static void main(String[] args) {

        Main main = new Main();

        for (Card card: main.cards) {

            System.out.println("Card Suit: " + card.getCardSuit() + "   Card Number: " + card.getCardNumber() + "   Visible: " + card.getCardVisibility());

        }

    }

}