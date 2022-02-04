package Solitaire;

public class Main {

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

        while (!main.gameFinished()) {

            Command command = main.parser.getUserCommand();

            if (command.validateCommand()) {

                System.out.println("valid");

            } else {

                
            }


        }

    }

}