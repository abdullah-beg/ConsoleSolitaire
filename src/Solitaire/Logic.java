package Solitaire;

import java.util.ArrayList;

public class Logic {
    
    public Logic() {

    }

    public boolean foundationMoveLogic(Pile pile, Foundation foundation) {

        Card card1 = pile.getCardAtIndex(pile.getCardCount() - 1);
        Card card2 = foundation.getCardAtIndex(foundation.getCardCount() - 1);
        

        if (foundation.isEmpty() == true) {
            if (card1.getCardNumber() == 1) {
                foundation.setSuit(card1.getCardSuit());
                return true;

            }

        } else {
            if (card1.getCardNumber() - 1 == card2.getCardNumber() && card1.getCardSuit().equals(card2.getCardSuit())) {
                return true;

            }

        }

        return false;

    }

    public boolean validMove(Pile p1, Pile p2) {

        Card card1 = p1.getCardAtIndex(p1.getCardCount() - 1);
        Card card2 = p2.getCardAtIndex(p2.getCardCount() - 1);

        if (p1.isEmpty() == false) {
            if (p2.isEmpty() == false) {
                if (card1.getCardNumber() + 1 == card2.getCardNumber() && card1.getCardColour() != card2.getCardColour()) {
                    return true;

                }

            } else {
                if (card1.getCardNumber() == 13) {
                    return true;

                }

            }

        }

        return false;

    }

    public void cycleStock(Stock stock, Waste waste) {

        if (!stock.isEmpty()) {
            
            waste.takeCardFromStock(stock.getCardAtIndex(0));
            stock.removeCardAtIndex(0);
            waste.setFrontCard();


            System.out.println("Number of cards in stock: " + stock.getCardCount() + "Front card in waste: " + waste.getFrontCard().getCardNumber());


        } else {

            for (Card card : waste.getCardsInPile()) {
                stock.returnCardToStock(card);

            }

            waste.getCardsInPile().clear();

        }


    }

    /*
        If we are moving a card from Waste to Table
    */  
    public boolean moveLogic(Waste waste, Pile pile) {

        Card card1 = waste.getFrontCard();
        Card card2 = pile.getBottomCard();

        if (!waste.isEmpty()) {
            if (pile.isEmpty()) {
                if (card1.getCardNumber() == 13) {
                    return true;

                }

            } else {
                // Table pile is not empty
                if (card1.getCardNumber() + 1 == card2.getCardNumber() && sameColour(card1, card2) == false) {
                    return true;

                }

            }

        }

        return false;

    }

    /*
        If we are moving a card from Waste to Foundation
    */
    public boolean moveLogic(Waste waste, Foundation foundation) {

        Card card1 = waste.getFrontCard();
        Card card2 = foundation.getBottomCard();

        if (!waste.isEmpty()) {
            if (foundation.isEmpty()) {
                if (card1.getCardNumber() == 1) {
                    foundation.setSuit(card1.getCardSuit());
                    return true;
                }

            } else {
                // Foundation is not empty
                if (card1.getCardNumber() - 1 == card2.getCardNumber() && card1.getCardSuit().equals(card2.getCardSuit())) {
                    return true;

                }

            }

        }

        return false;

    }

    /*
        If we are moving a Card from Foundation to Table
    */
    public boolean moveLogic(Foundation foundation, Pile pile) {

        Card card1 = foundation.getBottomCard();
        Card card2 = pile.getBottomCard();

        if (!foundation.isEmpty()) {
            if (pile.isEmpty()) {
                if (card1.getCardNumber() == 13) {
                    return true;

                }

            } else {
                if (card1.getCardNumber() + 1 == card2.getCardNumber() && sameColour(card1, card2) == false) {

                    if (card1.getCardNumber() == 1) {
                        foundation.setSuit("");

                    }

                    return true;

                }

            }

        }

        return false;

    }

    /*
        If we are moving a Card from Table Pile to Foundation
    */
    public boolean moveLogic(Pile pile, Foundation foundation) {
        
        Card card1 = pile.getBottomCard();
        Card card2 = foundation.getBottomCard();

        if (!pile.isEmpty()) {
            if (foundation.isEmpty()) {
                if (card1.getCardNumber() == 1) {
                    foundation.setSuit(card1.getCardSuit());
                    return true;

                }

            } else {
                // Foundation is not empty
                if (card1.getCardNumber() - 1 == card2.getCardNumber() && sameColour(card1, card2) == true) {
                    return true;

                }

            }

        }

        return false;

    }


    /*

    */
    public boolean moveLogic(Pile pile1, Pile pile2) {

        ArrayList<Card> cards1 = pile1.getVisibleCards();
        ArrayList<Card> cards2 = pile2.getVisibleCards();

        if (!pile1.isEmpty()) {
            if (pile2.isEmpty()) {
                if (cards1.get(0).getCardNumber() == 13) {
                    return true;

                }

            } else {
                // Both pile 1 and pile 2 are not empty 
                // We compare its top and bottom 

                Card top = cards1.get(0);
                Card bottom = cards2.get(cards2.size() - 1);

                if (top.getCardNumber() + 1 == bottom.getCardNumber() && sameColour(top, bottom) == false) {
                    return true;

                }

            }

        }

        return false;

    }

    /*

    */
    public boolean moveLogic(Foundation foundation1, Foundation foundation2) {

        Card card1 = foundation1.getBottomCard();
        Card card2 = foundation1.getBottomCard();

        if (!foundation1.isEmpty() && foundation2.isEmpty()) {
            if (card1.getCardNumber() == 1) {
                return true;

            }

        }

        return false;

    }

    public boolean sameColour(Card card1, Card card2) {

        if (card1.getCardColour().equals(card2.getCardColour())) {
            return true;

        }

        return false;

    }

    public boolean validMove(Pile p1, Pile p2, int index) {

        return false;

    }

}
