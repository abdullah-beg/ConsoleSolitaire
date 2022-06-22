package Solitaire;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        Draw draw = new Draw();

        game.getBoard().showFrontCard();
        game.getUndo().setBaseState(new State(game.getBoard().getWaste(), game.getBoard().getStock(), game.getBoard().getFoundations(), game.getBoard().getPiles()));
        draw.printBoard(game.getBoard().getStock().getCardCount(), game.getBoard().getWaste(), game.getBoard().getFoundations(), game.getBoard().getPiles(), game.getValidMove());
        game.setValidMove(false);

        while (!game.gameFinished()) {

            Command command = game.getParser().getUserCommand();

            if (command.validateCommand()) {

                String word1 = command.getFirstWord();
                String word2 = command.getSecondWord();
                String word3 = command.getThirdWord();

                if (command.getSecondWord() == null) {
                    game.processCommand(word1);

                } else  {
                    if (command.getThirdWord() == null) {
                        game.processCommand(word1, word2);

                    } else {
                        game.processCommand(word1, word2, word3);
                    
                    }

                    if (game.getValidMove()) {
                        game.getBoard().showFrontCard();
                        game.getUndo().doMove(new State(game.getBoard().getWaste(), game.getBoard().getStock(), game.getBoard().getFoundations(), game.getBoard().getPiles()));
                        game.incrementMoveCount();

                    }

                }

            }

            draw.printBoard(game.getBoard().getStock().getCardCount(), game.getBoard().getWaste(), game.getBoard().getFoundations(), game.getBoard().getPiles(), game.getValidMove());
            game.setValidMove(false);

        }

        System.out.println("!!!~~~~~~~~~~GAME OVER~~~~~~~~~~!!!");
        System.out.println("!!!~~~~~~~~~~~YOU WON~~~~~~~~~~~!!!");

    }

}