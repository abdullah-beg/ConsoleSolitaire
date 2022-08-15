package Solitaire;

import java.util.ArrayList;

public class Logic {
    
    public Logic() {}

    /**
     * Logic for cycling the stock.
     * @param stock The Stock pile.
     * @param waste The Waste pile.
     */
    public void cycleStock(Stock stock, Waste waste) {

        if (!stock.isEmpty()) {
            waste.takeCardFromStock(stock.getCardAtIndex(0));
            stock.removeCardAtIndex(0);
            waste.setCardOrder();

        } else {
            for (Card card : waste.getCardsInPile()) {
                stock.returnCardToStock(card);

            }

            waste.getCardsInPile().clear();

        }


    }


    /**
     * Logical computation when moving a card from Waste to Table
     * @param waste The Waste pile.
     * @param pile The Table Pile.
     * @return boolean dictating whether the move is valid.
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

    /**
     * Logical computation when moving a card from Waste to Foundation.
     * @param waste The Waste pile.
     * @param foundation The Foundation pile.
     * @return boolean dictating whether the move is valid.
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

    /**
     * Logical computation when moving a card from a foundation to a pile.
     * @param foundation The Foundation pile.
     * @param pile The Table pile.
     * @return boolean dictating whether the move is valid.
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

    /**
     * Logical computation when moving a card from a Table pile to Foundation.
     * @param pile The Table pile.
     * @param foundation The Foundation pile.
     * @return boolean dictating whether the move is valid.
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
                // make sure to compare their suits not their colour
                if (card1.getCardNumber() - 1 == card2.getCardNumber() && card1.getCardSuit().equals(card2.getCardSuit())) {
                    return true;

                }

            }

        }

        return false;

    }

    /**
     * Logical computation when moving a card from a pile to a pile.
     * @param pile1 The table pile.
     * @param pile2 The other table pile.
     * @return boolean dictating whether the move is valid.
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

    /**
     * Logical computation when moving a card from foundation to foundation.
     * @param foundation1 The Foundation pile.
     * @param foundation2 The other Foundation pile.
     * @return boolean dictating whether the move is valid.
     */
    public boolean moveLogic(Foundation foundation1, Foundation foundation2) {

        Card card1 = foundation1.getBottomCard();

        // This doesn't need a second card, since only possible is moving A across foundations

        if (!foundation1.isEmpty() && foundation2.isEmpty()) {
            if (card1.getCardNumber() == 1) {
                return true;

            }

        }

        return false;

    }

    /**
     * Logical computation when moving a section of cards from one pile to another.
     * @param pile1 The Table pile.
     * @param cardNumber The selected card number to move.
     * @param pile2 The other Table pile.
     * @return boolean dictating whether the move is valid.
     */
    public ArrayList<Card> moveLogic(Pile pile1, int cardNumber, Pile pile2) {

        ArrayList<Card> visibleCards = pile1.getVisibleCards();
        ArrayList<Card> returnCards = new ArrayList<>();
        Card card1;

        if (!pile1.isEmpty()) {
            if (pile2.isEmpty()) {
                for (Card card : visibleCards) {
                    if (card.getCardNumber() == cardNumber) {

                        card1 = card;

                        if (card1.getCardNumber() == 13) {
                            return visibleCards;

                        }

                    }

                }

            } else {

                // Pile 1 and 2 are not empty

                for (Card card : visibleCards) {
                    if (card.getCardNumber() == cardNumber) {
                        card1 = card;
                        int index = pile1.getCardsInPile().indexOf(card);
                        Card card2 = pile2.getBottomCard();

                        for (int i = index; i < pile1.getCardCount(); i++) {
                            returnCards.add(pile1.getCardAtIndex(i));
                        }

                        if (card1.getCardNumber() + 1 == card2.getCardNumber() && !sameColour(card1, card2)) {
                            return returnCards;                            

                        }
        
                    }
        
                }

            }

        }

        return null;

    }

    /**
     * Compare if two cards are the same colour.
     * @param card1 Card 1
     * @param card2 Card 2
     * @return boolean dictating if the two cards are the same colour.
     */
    public boolean sameColour(Card card1, Card card2) {

        return card1.getCardColour().equals(card2.getCardColour());

    }

}
