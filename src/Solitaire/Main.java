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



    }

    public void processCommand(String word1, String word2) {

        if (word2.substring(0,1).equals("f")) {
            Foundation foundation = game.getFoundation(Integer.parseInt((word2).substring(1,2)) - 1);
            Pile pile = game.getPile(Integer.parseInt((word1).substring(1,2)) - 1);

            if (logic.foundationMoveLogic(pile, foundation)) {
                foundation.addCardToPile(pile.getCardAtIndex(pile.getCardCount() - 1));
                pile.removeCardAtIndex(pile.getCardCount() - 1);

            }

        } else {
            if (logic.validMove(game.locatePile(word1), game.locatePile(word2))) {
                Pile pile1 = game.getPile(Integer.parseInt((word1).substring(1,2)) - 1);
                Pile pile2 = game.getPile(Integer.parseInt((word2).substring(1,2)) - 1);

                pile2.addCardToPile(pile1.getBottomCard());
                pile1.removeCardFromBottom();

            }

        }

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