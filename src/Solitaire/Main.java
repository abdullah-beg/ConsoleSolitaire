package Solitaire;

public class Main {

    // game map 87 x 41
    private GameBoard game;
    private Parser parser;

    private Main() {

        parser = new Parser();
        game = new GameBoard();

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
        Logic logic = new Logic();

        while (!main.gameFinished()) {

            Command command = main.parser.getUserCommand();

            if (command.validateCommand()) {

                String word1 = command.getFirstWord();
                String word2 = command.getSecondWord();
                String word3 = command.getThirdWord();

                if (command.getSecondWord() == null) {
                    

                } else if (command.getThirdWord() == null) {
                    if (command.getSecondWord().substring(0,1).equals("f")) {
                        Foundation foundation = main.game.getFoundation(Integer.parseInt((word2).substring(1,2)) - 1);
                        Pile pile = main.game.getPile(Integer.parseInt((word1).substring(1,2)) - 1);

                        if (logic.foundationMoveLogic(pile, foundation)) {
                            foundation.addCardToPile(pile.getCardAtIndex(pile.getCardCount() - 1));
                            pile.removeCardAtIndex(pile.getCardCount() - 1);

                        }

                    } else {
                        logic.validMove(main.game.locatePile(command.getFirstWord()), main.game.locatePile(command.getSecondWord()));

                    }

                } else {

                }

            } else {


            }


        }

    }

}