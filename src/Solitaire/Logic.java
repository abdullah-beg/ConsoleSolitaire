package Solitaire;

public class Logic {
    
    public Logic() {

    }

    public boolean foundationMoveLogic(Pile pile, Foundation foundation) {

        Card card1 = pile.getCardAtIndex(pile.getCardCount() - 1);
        Card card2 = foundation.getCardAtIndex(foundation.getCardCount() - 1);
        
        if (card1.getCardNumber() == 1 && foundation.getCardCount() == 0) {
            System.out.println("A added to foundation pile");
            foundation.setSuit(card1.getCardSuit());
            return true;

        }

        if (card1.getCardNumber() - 1 == card2.getCardNumber() && card1.getCardSuit().equals(card2.getCardSuit())) {
            System.out.println("yeah you can add it to foundation it matches");
            return true;

        }

        return false;

    }

    public boolean validMove(Pile p1, Pile p2) {

        Card card1 = p1.getCardAtIndex(p1.getCardCount() - 1);
        Card card2 = p2.getCardAtIndex(p2.getCardCount() - 1);

        if (p1.getCardCount() == 0) {
            System.out.println("nah g ur waffljng");
        }

        if (card1.getCardNumber() + 1 == card2.getCardNumber()) {


        }

        if (card1.getCardNumber() + 1 == card2.getCardNumber() && card1.getCardColour() != card2.getCardColour()) {
            System.out.println("true");

            return true;

        } else if (card1.getCardNumber() == 13 && p2.isEmpty() == true) {
            System.out.println("true");

            return true;

        }

        return false;

    }

    public boolean validMove(Pile p1, Pile p2, int index) {

        return false;

    }

}
