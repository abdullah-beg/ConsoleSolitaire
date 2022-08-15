package Solitaire;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        Draw draw = new Draw();

        userInputPlayWithSound();
        CardSound.playRandomCardShuffleSoundEffect();

        game.getBoard().showFrontCard();
        game.getUndo().setBaseState(new State(game.getBoard().getWaste(), game.getBoard().getStock(), game.getBoard().getFoundations(), game.getBoard().getPiles()));
        game.setHelpMessage();
        draw.printBoard(game.getBoard().getStock().getCardCount(), game.getBoard().getWaste(), game.getBoard().getFoundations(), game.getBoard().getPiles(), game.getValidMove(), game.getMoveCount(), game.getGameMessage());
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

                    if (!game.getSkip()) {
                        if (game.getValidMove()) {
                            game.getBoard().showFrontCard();
                            game.getUndo().doMove(new State(game.getBoard().getWaste(), game.getBoard().getStock(), game.getBoard().getFoundations(), game.getBoard().getPiles()));
                            game.incrementMoveCount();
                            CardSound.playRandomCardMoveSoundEffect();

                        }
                        
                    }

                }

            }

            draw.printBoard(game.getBoard().getStock().getCardCount(), game.getBoard().getWaste(), game.getBoard().getFoundations(), game.getBoard().getPiles(), game.getValidMove(), game.getMoveCount(), game.getGameMessage());
            game.setValidMove(false);
            game.setSkip(false);
            game.clearMessage();

        }

        game.setWinMessage();
        draw.printBoard(game.getBoard().getStock().getCardCount(), game.getBoard().getWaste(), game.getBoard().getFoundations(), game.getBoard().getPiles(), game.getValidMove(), game.getMoveCount(), game.getGameMessage());
            
    }

    /**
     * Allow the user to pick whether they are to play with sound or not.
     */
    public static void userInputPlayWithSound() {

        Scanner userInput = new Scanner(System.in);
        System.out.print("Play with sound? (y/n)\n   Enter Option: ");
        String option = userInput.nextLine();

        if (option.trim().toLowerCase().startsWith("y")) {
            CardSound.setPlayWithSound(true);

        }

    }

}