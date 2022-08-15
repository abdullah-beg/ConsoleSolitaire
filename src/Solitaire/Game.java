package Solitaire;

import java.util.ArrayList;

public class Game {
    
    // game map 87 x 41
    private GameBoard game;
    private Parser parser;
    private Logic logic;
    private State state;
    private Undo undo;
    private boolean validMove;
    private int moveCount;

    private boolean skip;

    private String[] message;

    /**
     * Constructor for Game. Initialise fields.
     */
    public Game() {

        parser = new Parser();
        game = new GameBoard();
        logic = new Logic();
        undo = new Undo();
        validMove = false;
        moveCount = 0;
        skip = false;

        message = new String[] {""};

    }

    /**
     * Getter for State.
     * @return State of the board.
     */
    public State getState() {

        return state;

    }

    /**
     * Getter for board.
     * @return Returns the gameboard field.
     */
    public GameBoard getBoard() {

        return game;

    }

    /**
     * Getter for parser.
     * @return Returns the parser field.
     */
    public Parser getParser() {

        return parser;

    }

    /**
     * Getter for logic.
     * @return Returns the logic field.
     */
    public Logic getLogic() {

        return logic;

    }

    /**
     * Getter for undo
     * @return Returns the undo field.
     */
    public Undo getUndo() {

        return undo;

    }

    /**
     * Check if the game is in its finished state.
     * @return (true/false) if the game is in finished state.
     */
    public boolean gameFinished() {

        for (Foundation foundation : game.getFoundations()) {
            if (foundation.isEmpty() || foundation.getBottomCard().getCardNumber() != 13) {
                return false;

            }

        }

        return true;

    }

    /**
     * Process single word commands.
     * @param word1 The command to be processed.
     */
    public void processCommand(String word1) {

        // Only valid command words can make it to this point.
        
        switch (word1) {
            case "help" : 
                setHelpMessage();
                setValidMove(true);
                break;

            case "undo" :
                undoRoutine();
                CardSound.playRandomCardMoveSoundEffect();
                break;

            case "restart" :
                restartGame();
                CardSound.playRandomCardShuffleSoundEffect();
                break;

            case "new" :
                newGame();
                CardSound.playRandomCardShuffleSoundEffect();
                break;

            case "s" :
                cycleStock();
                CardSound.playRandomCardMoveSoundEffect();
                break;

        }

    }

    /**
     * Process two word commands.
     * @param word1 The first word in the user input.
     * @param word2 The second word in the user input.
     */
    public void processCommand(String word1, String word2) {

        if (word1.equals("help")) {
            
            switch (word2) {

                case "s" : 
                    message = new String[] 
                    {
                        "~~~ s",
                        " - Usage: s",
                        " - Action: cycles the cards in the stock.",
                        " - Example: s"
                    };
                    setValidMove(true);
                    setSkip(true);
                    break;


                case "w" : 
                    message = new String[] 
                    {
                      "~~~ w",
                      " - Usage: w",
                      " - Action: select the right-most card in the waste.",
                      " - Example: w p7 (moves the right-most card in the waste onto pile 7)",
                      " - Example: w f4 (moves the right-most card in the waste into foundation 4)"  
                    };
                    setValidMove(true);
                    setSkip(true);
                    break;


                case "undo" :
                    message = new String[] 
                    {
                        "~~~ undo",
                        " - Usage: undo",
                        " - Action: undoes the most recent move.",
                        " - Example: undo"
                    };
                    setValidMove(true);
                    setSkip(true);
                    break;
                

                case "restart" :
                    message = new String[] 
                    {
                        "~~~ restart",
                        " - Usage: restart",
                        " - Action: restarts the current game.",
                        " - Example: restart"
                    };
                    setValidMove(true);
                    setSkip(true);
                    break;

                
                case "f" :
                    message = new String[] 
                    {
                        "~~~ f",
                        " - Usage: f<1-4>",
                        " - Action: selects foundation pile <1-4>",
                        " - Example: p3 f2 (moves the bottom card from pile 3 into foundation 2)",
                        " - Example: f4 p7 (moves the top card in foundation 4 into pile 7)"
                    };
                    setValidMove(true);
                    setSkip(true);
                    break;

                
                case "p" :
                    message = new String[] 
                    {
                        "~~~ p",
                        " - Usage: p<1-7>",
                        " - Action: selects table pile <1-7>",
                        " - Example: p1 p2 (moves top-most visible card in pile 1 onto the bottm card in pile 2)",
                        " - Example: p5 7 p3 (moves the cards from 7 and below in pile 5 onto pile 3)"
                    };
                    setValidMove(true);
                    setSkip(true);
                    break;

                case "new" :
                    message = new String[] 
                    {
                        "~~~ new",
                        " - Usage: new",
                        " - Action: start a new game.",
                        " - Example: new"
                    };
                    setValidMove(true);
                    setSkip(true);
                    break;

            }


        } else if (word1.equals("w")) {

            Waste waste = game.getWaste();

            if (word2.substring(0,1).equals("p")) {

                Pile pile = game.getPile(Integer.parseInt((word2).substring(1,2)) - 1);

                if (logic.moveLogic(waste, pile)) {
                    pile.addCardToPile(waste.getFrontCard());
                    waste.removeCardFromBottom();
                    waste.setCardOrder();

                    setValidMove(true);
        
                }


            } else {

                Foundation foundation = game.getFoundation(Integer.parseInt((word2).substring(1,2)) - 1);

                if (logic.moveLogic(waste, foundation)) {
                    foundation.addCardToPile(waste.getFrontCard());
                    waste.removeCardFromBottom();
                    waste.setCardOrder();

                    setValidMove(true);

                }

            }

        } else if (word1.substring(0,1).equals("f")) { 

            Foundation foundation = game.getFoundation(Integer.parseInt((word1).substring(1,2)) - 1);

            if (word2.substring(0,1).equals("p")) {
                Pile pile = game.getPile(Integer.parseInt((word2).substring(1,2)) - 1);

                if (logic.moveLogic(foundation, pile)) {
                    pile.addCardToPile(foundation.getBottomCard());
                    foundation.removeCardFromBottom();

                    setValidMove(true);
        
                }


            } else {

                Foundation foundation2 = game.getFoundation(Integer.parseInt((word2).substring(1,2)) - 1);

                if (logic.moveLogic(foundation, foundation2)) {
                    foundation2.addCardToPile(foundation.getBottomCard());
                    foundation.removeCardFromBottom();

                    setValidMove(true);
        
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

                    setValidMove(true);
        
                }

            } else {

                Foundation foundation = game.getFoundation(Integer.parseInt((word2).substring(1,2)) - 1);

                if (logic.moveLogic(pile, foundation)) {
                    foundation.addCardToPile(pile.getBottomCard());
                    pile.removeCardFromBottom();

                    setValidMove(true);
        
                }

            }

        }

    }

    /**
     * Process triple word commands.
     * @param word1 The first word in the user input.
     * @param word2 The second word in the user input.
     * @param word3 The third word in the user input.
     */
    public void processCommand(String word1, String word2, String word3) {

        Pile pile1 = game.getPile(Integer.parseInt((word1).substring(1,2)) - 1);
        Pile pile2 = game.getPile(Integer.parseInt((word3).substring(1,2)) - 1);
        int cardNumber = Integer.parseInt((convertInput(word2)));
        
        if (logic.moveLogic(pile1, cardNumber, pile2) != null) {
            ArrayList<Card> addCards = logic.moveLogic(pile1, cardNumber, pile2);
            
            for (Card card : addCards) {
                // Add each of the new cards to Pile2
                pile2.addCardToPile(card);

                // Remove each card from Pile1
                pile1.getCardsInPile().remove(card);

            }

            setValidMove(true);
            
        }


    }

    /**
     * Convert the user inputted character values to numbers.
     * @param input (1 - 9) (K, Q, J, A) to numbers.
     * @return String which is the card value as a number.
     */
    private String convertInput(String input) {

        return switch (input) {
            case "a" -> "1";

            case "j" -> "11";

            case "q" -> "12";

            case "k" -> "13";

            default -> input;

        };

    }

    /**
     * Setter for the validMove field.
     * @param input (true/false) valid move.
     */
    public void setValidMove(boolean input) {

        validMove = input;

    }

    /**
     * Getter for the validMove field.
     * @return Returns the validMove field.
     */
    public boolean getValidMove() {

        return validMove;

    }

    /**
     * Restarts the game and presents a message for the user.
     */
    private void restartGame() {

        message = new String[] 
        {
            "Game Restarted!"
        };

        game.setState(undo.getBaseState());
        undo.clearMoveStack();
        setMoveCount(0);
        setValidMove(true);

    }

    /**
     * Creates a new game and presents a message for the user.
     */
    private void newGame() {

        message = new String[] 
        {
            "New game!"
        };

        game = new GameBoard();
        undo.setBaseState(new State(game.getWaste(), game.getStock(), game.getFoundations(), game.getPiles()));
        undo.clearMoveStack();
        setMoveCount(0);
        setValidMove(true);

    }

    /**
     * Increments the move count.
     */
    public void incrementMoveCount() {

        moveCount++;

    }

    /**
     * Decrements the move count.
     */
    public void decrementMoveCount() {

        moveCount--;

    }

    /**
     * Getter for the move count.
     * @return Number of moves.
     */
    public int getMoveCount() {

        return moveCount;

    }

    /**
     * Set the move count.
     * @param moveCount Value for the move count to be set to.
     */
    public void setMoveCount(int moveCount) {

        this.moveCount = moveCount;

    }

    /**
     * Undo the last move if there was any.
     */
    public void undoRoutine() {

        State old;

        switch (undo.getMoveStackSize()) {

            case 0 :
                message = new String[] 
                {
                    "There are no moves to undo!"
                };

                setValidMove(false);
                return;

            case 1 :
                undo.undoMove();
                old = undo.getBaseState();
                break;

            default :
                undo.undoMove();
                old = undo.getMostRecentState();
                break;

        }

        game.setWaste(old.convertWaste());
        game.setStock(old.convertStock());
        game.setFoundations(old.convertFoundations());
        game.setPiles(old.convertTablePiles());

        setValidMove(true);
        decrementMoveCount();
        
    }

    /**
     * Getter for the message field.
     * @return Returns the message to be printed onto the board.
     */
    public String[] getGameMessage() {

        return message;

    }

    /**
     * Clears the message on the board.
     */
    public void clearMessage() {

        message = new String[] {""};

    }

    /**
     * Setter for the skip field.
     * @param skip Value for the skip field to be set to.
     */
    public void setSkip(boolean skip) {

        this.skip = skip;

    }

    /**
     * Getter for the skip field.
     * @return (true/false) if the counter should be skipped.
     */
    public boolean getSkip() {

        return skip;

    }

    /**
     * Sets the win message.
     */
    public void setWinMessage() {

        message = new String[] 
        {
            "!!!!!!!!!!!!!!!!!!!!!!!!!!",
            "!!                      !!",
            "!!   ***GAME  OVER***   !!",
            "!!                      !!",
            "!!     **YOU  WIN**     !!",
            "!!                      !!",
            "!!!!!!!!!!!!!!!!!!!!!!!!!!"
        };

    }

    public void setHelpMessage() {

        message = new String[] {
            "The goal of the game is to sort all the cards into their suits",
            "To move a card type: <location1> <location2> For Example: 'p7 p2' or 'w f3' or 'f1 p2'",
            "You can also move specific cards: <location1> <card number> <location2>",
            "For detailed insutrctions please visit: 'https://bicyclecards.com/how-to-play/solitaire/'",
            "",
            "------ Commands -----------------------------------------------------",
            " - help    - restart    - new    - undo    - s    - w    - p    - f",
            "",
            "Type help <command> for more info on each command."
        };

    }

    private void cycleStock() {

        logic.cycleStock(game.getStock(), game.getWaste()); 
        undo.doMove(new State(game.getWaste(), game.getStock(), game.getFoundations(), game.getPiles()));
        incrementMoveCount();
        setValidMove(true);

    }

}
