package Solitaire;

public class Main {

    // game map 87 x 41
    private GameBoard game;
    private Parser parser;
    private Logic logic;

    private Main() {

        parser = new Parser();
        game = new GameBoard();
        logic = new Logic();

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

    public void processCommand(String word1) {

        if (word1.equals("help")) {

            return;

        } else if (word1.equals("undo")) {

            return;

        }

        // If this has been reached, they have inputted "s"

        logic.cycleStock(game.getStock(), game.getWaste());


    }

    public void processCommand(String word1, String word2) {


        if (word1.equals("w")) {

            Waste waste = game.getWaste();

            if (word2.substring(0,1).equals("p")) {
                
                Pile pile = game.getPile(Integer.parseInt((word2).substring(1,2)) - 1);

                if (logic.moveLogic(waste, pile)) {
                    
                    pile.addCardToPile(waste.getFrontCard());
                    waste.removeCardFromBottom();
        
                }


            } else {
                
                Foundation foundation = game.getFoundation(Integer.parseInt((word2).substring(1,2)) - 1);

                if (logic.moveLogic(waste, foundation)) {

                    foundation.addCardToPile(waste.getFrontCard());
                    waste.removeCardFromBottom();
                    waste.setFrontCard();

                }

            }

        } else if (word1.substring(0,1).equals("f")) { 

            Foundation foundation = game.getFoundation(Integer.parseInt((word1).substring(1,2)) - 1);

            if (word2.substring(0,1).equals("p")) {

                Pile pile = game.getPile(Integer.parseInt((word2).substring(1,2)) - 1);

                if (logic.moveLogic(foundation, pile)) {

                    pile.addCardToPile(foundation.getBottomCard());
                    foundation.removeCardFromBottom();
        
                }


            } else {

                Foundation foundation2 = game.getFoundation(Integer.parseInt((word2).substring(1,2)) - 1);

                if (logic.moveLogic(foundation, foundation2)) {

                    foundation2.addCardToPile(foundation2.getBottomCard());
                    foundation.removeCardFromBottom();
        
                }


            }

        } else {

            Pile pile = game.getPile(Integer.parseInt((word1).substring(1,2)) - 1);

            if (word2.substring(0,1).equals("p")) {

                Pile pile2 = game.getPile(Integer.parseInt((word2).substring(1,2)) - 1);

                if (logic.moveLogic(pile, pile2)) {
                    for (Card card : pile.getVisibleCards()) {
                        pile2.addCardToPile(card);
                        pile.getCardsInPile().remove(card);
                    }
        
                }

            } else {

                Foundation foundation = game.getFoundation(Integer.parseInt((word2).substring(1,2)) - 1);

                if (logic.moveLogic(pile, foundation)) {
                
                    foundation.addCardToPile(pile.getBottomCard());
                    pile.removeCardFromBottom();
        
                }

            }

        }


        // if (word1.equals("w")) {
        //     pile1 = new Waste();
        //     pile1 = game.getWaste();

        // } else if (word1.substring(0,1).equals("f")) {
        //     pile1 = new Foundation();
        //     pile1 = game.getFoundation(Integer.parseInt((word1).substring(1,2)) - 1);

        // } else {
        //     pile1 = game.getPile(Integer.parseInt((word1).substring(1,2)) - 1);

        // }

        // if (word2.substring(0,1).equals("f")) {
        //     pile2 = new Foundation();
        //     pile2 = game.getFoundation(Integer.parseInt((word2).substring(1,2)) - 1);

        // } else {
        //     pile2 = game.getPile(Integer.parseInt((word2).substring(1,2)) - 1);

        // }

        // System.out.println(pile1.getClass());
        // System.out.println(pile2.getClass());

        // // We now have both piles in their types.

        // if (logic.moveLogic(pile1, pile2)) {
        //     for (Card card : pile1.getVisibleCards()) {
        //         pile2.addCardToPile(card);

        //     }

        // }


        // if (word1.equals("w")) {
        //     Waste waste = game.getWaste();

        // } else {

        // }
        // if (word2.substring(0,1).equals("f")) {
        //     Foundation foundation = game.getFoundation(Integer.parseInt((word2).substring(1,2)) - 1);
        //     Pile pile = game.getPile(Integer.parseInt((word1).substring(1,2)) - 1);

        //     if (logic.foundationMoveLogic(pile, foundation)) {
        //         foundation.addCardToPile(pile.getCardAtIndex(pile.getCardCount() - 1));
        //         pile.removeCardAtIndex(pile.getCardCount() - 1);

        //     }

        // } else {
        //     if (logic.validMove(game.locatePile(word1), game.locatePile(word2))) {
        //         Pile pile1 = game.getPile(Integer.parseInt((word1).substring(1,2)) - 1);
        //         Pile pile2 = game.getPile(Integer.parseInt((word2).substring(1,2)) - 1);

        //         pile2.addCardToPile(pile1.getBottomCard());
        //         pile1.removeCardFromBottom();

        //     }

        // }

    }

    public void processCommand(String word1, String word2, String word3) {

        

    }

    public static void main(String[] args) {

        Main main = new Main();

        while (!main.gameFinished()) {

            Command command = main.parser.getUserCommand();

            if (command.validateCommand()) {

                String word1 = command.getFirstWord();
                String word2 = command.getSecondWord();
                String word3 = command.getThirdWord();

                if (command.getSecondWord() == null) {
                    main.processCommand(word1);

                } else if (command.getThirdWord() == null) {
                    main.processCommand(word1, word2);

                } else {
                    main.processCommand(word1, word2, word3);

                }

                main.game.showFrontCard();

            } else {



            }


        }

    }

}