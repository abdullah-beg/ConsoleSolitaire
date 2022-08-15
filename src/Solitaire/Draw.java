package Solitaire;

import java.util.ArrayList;

public class Draw {
    
    // private static final String BLUE = "\033[0;34m";    // BLUE
    private static final String RED = "\033[0;31m";     // RED
    private static final String RESET = "\033[0m";  // Text Reset
    
    private final int width = 90;
    private final int height = 42;

    private String[][] board;

    /**
     * Constructor for draw which initialises the board.
     */
    public Draw() {

        board = new String[width][height];

    }

    /**
     * Clears the board by setting every element to " ".
     */
    private void clearBoard() {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board[x][y] = " ";

            }

        }

    }

    /**
     * Prints the board by taking in each section of the board
     * @param cardCount Number of cards in the stock.
     * @param waste The Waste pile in the board.
     * @param foundations The Foundation piles in the board.
     * @param piles The Table Piles in the board.
     * @param move The validity of the move.
     * @param moveCount The number of moves.
     * @param message Message to be printed at the side.
     */
    public void printBoard(int cardCount, Waste waste, Foundation[] foundations, Pile[] piles, boolean move, int moveCount, String[] message) {

        clearBoard();
        printStock(cardCount);
        printWaste(waste);
        printFoundations(foundations);
        printTablePiles(piles);
        printGameLabels(waste);
        printValidMove(move);
        printMoveCount(moveCount);
        printHelpMessage(message);

        String[] output = new String[height];

        for (int index = 0; index < height; index++) {

            output[index] = "";

            for (int x = 0; x < width; x++) {
                output[index] += "" + board[x][index];

            }

        }

        String finalOutput = "";

        for (String s : output) {
            finalOutput += "\n" + s;

        }

        System.out.println(finalOutput);

    }

    /**
     * Draw the stock into the array.
     * @param cardCount Number of cards in the stock.
     */
    private void printStock(int cardCount) {

        if (cardCount > 0) {

            drawCardTemplate(5,3);

            // Fill inside of card

            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 4; y++) {
                    board[6 + x][4 + y] = RED + "▒" + RESET;
                }
            }

            if (cardCount > 9) {

                board[7][5] = "" + (cardCount / 10);
                board[8][5] = "" + (cardCount % 10);

            } else {

                board[8][5] = "" + cardCount;

            }

        } else {

            drawEmptySpace(5,3);
            board[8][5] = "0";

        }

    }

    /**
     * Draw the Waste into the array.
     * @param waste Waste pile.
     */
    private void printWaste(Waste waste) {

        Card backCard = waste.getBackCard();
        Card midCard = waste.getMidCard();
        Card frontCard = waste.getFrontCard();

        if (waste.getCardCount() == 0) {

            drawEmptySpace(15,3);

        } else if (waste.getCardCount() == 1) {

            drawCardTemplate(15,3);
            drawIndicator(17, 2, "W");

            drawCardValues(15, 3, frontCard.getCardRepresentation(), frontCard.getCardSuit(), frontCard.getCardColour());
            
        } else if (waste.getCardCount() == 2) {

            drawCardTemplate(15,3);
            drawCardTemplate(18,3);
            drawIndicator(20, 2, "W");
            
            drawWasteValues(15, 3, backCard.getCardRepresentation(), backCard.getCardSuit(), backCard.getCardColour());
            drawCardValues(18, 3, frontCard.getCardRepresentation(), frontCard.getCardSuit(), frontCard.getCardColour());

        } else {

            // Meaning that there are  >= 3 cards
            drawCardTemplate(15,3);
            drawCardTemplate(18,3);
            drawCardTemplate(21,3);
            drawIndicator(23, 2, "W");
            
            drawWasteValues(15, 3, backCard.getCardRepresentation(), backCard.getCardSuit(), backCard.getCardColour());
            drawWasteValues(18, 3, midCard.getCardRepresentation(), midCard.getCardSuit(), midCard.getCardColour());
            drawCardValues(21, 3, frontCard.getCardRepresentation(), frontCard.getCardSuit(), frontCard.getCardColour());

        }

    }

    /**
     * Draw the foundations into the array.
     * @param foundations Array of Foundation piles.
     */
    private void printFoundations(Foundation[] foundations) {

        int pointer = 35;

        for (Foundation foundation : foundations) {

            if (foundation.getCardCount() > 0) {

                Card bottomCard = foundation.getBottomCard();

                drawCardTemplate(pointer,3);
                drawCardValues(pointer, 3, bottomCard.getCardRepresentation(), bottomCard.getCardSuit(), bottomCard.getCardColour());

            } else {
                drawEmptySpace(pointer,3);

            }

            pointer += 10;


        }

    }

    /**
     * Draw the Table Piles into the array.
     * @param piles Array of Table Piles.
     */
    private void printTablePiles(Pile[] piles) {

        int pointerX = 5;

        for (Pile pile: piles) {

            int pointerY = 11;

            ArrayList<Card> visibleCards = pile.getVisibleCards();
            ArrayList<Card> hiddenCards = pile.getHiddenCards();

            for (Card card : hiddenCards) {

                drawHiddenCard(pointerX, pointerY);
                pointerY += 2;

            }

            // pointerY = 11;
            // drawEmptySpace(pointerX, 11);

            if (visibleCards.size() == 0) { 
                drawEmptySpace(pointerX, pointerY);

            } else {
                
                drawCardTemplate(pointerX, pointerY);

                for (Card card : visibleCards) {

                    if (!(card == pile.getBottomCard())) {
                        // If its not the bottom card, then

                        drawStackedCard(pointerX, pointerY, card.getCardRepresentation(), card.getCardSuit(), card.getCardColour());
                        pointerY++;

                    } else {
                    
                        drawCutCard(pointerX, pointerY);
                        drawCardValues(pointerX, pointerY, card.getCardRepresentation(), card.getCardSuit(), card.getCardColour());

                    }

                }

            }

            pointerX += 10;

        }

    }

    /**
     * Draw each of the pile indicators.
     * @param waste The Waste pile.
     */
    private void printGameLabels(Waste waste) {

        // Stock
        drawIndicator(7, 2, "S");

        // Waste 
        if (waste.getCardCount() < 2) {
            drawIndicator(17, 2, "W");

        } else if (waste.getCardCount() == 2) {
            drawIndicator(20, 2, "W");

        } else {
            drawIndicator(23, 2, "W");

        }
        
        // Foundations
        int foundationPointer = 37;
        for (int foundation = 0; foundation < 4; foundation++) {
            drawIndicator(foundationPointer + foundation * 10, 2, "F", foundation + 1);
        }

        // Table Piles
        int tablePointer = 7;
        for (int table = 0; table < 7; table++) {
            drawIndicator(tablePointer + table * 10, 10, "P", table + 1);
        }

    }

    /**
     * Draw a blank card space.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    private void drawEmptySpace(int x, int y) {

        // Card top
        board[x+1][y] = "_";
        board[x+5][y] = "_";


        // Card sides
        board[x][y + 1] = "|";
        board[x + 6][y + 1] = "|";

        board[x][y + 4] = "|";
        board[x + 6][y + 4] = "|";

        // Card bottom
        board[x + 1][y + 4] = "_";
        board[x + 5][y + 4] = "_";

    }

    /**
     * Draw a full card.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    private void drawCardTemplate(int x, int y) {

        // Card top
        board[x][y] = " ";
        board[x + 1][y] = "_";
        board[x + 2][y] = "_";
        board[x + 3][y] = "_";
        board[x + 4][y] = "_";
        board[x + 5][y] = "_";
        board[x + 6][y] = " ";
        
        // Card sides
        for (int m = 1; m < 5; m++) {
            for (int n = 0; n < 7; n++) {
                if (n == 0 || n == 6) {
                    board[x + n][y + m] = "|";

                } else {
                    board[x + n][y + m] = " ";

                }
                
            }

        }

        // Card bottom
        board[x + 1][y + 4] = "_";
        board[x + 2][y + 4] = "_";
        board[x + 3][y + 4] = "_";
        board[x + 4][y + 4] = "_";
        board[x + 5][y + 4] = "_";


    }

    /**
     * Draw the top of a card.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    private void drawCutCard(int x, int y) {

        // The top is not defined for this shape
        
        // Card sides
        for (int m = 1; m < 5; m++) {
            for (int n = 0; n < 7; n++) {
                if (n == 0 || n == 6) {
                    board[x + n][y + m] = "|";

                } else {
                    board[x + n][y + m] = " ";

                }
                
            }

        }

        // Card bottom
        board[x + 1][y + 4] = "_";
        board[x + 2][y + 4] = "_";
        board[x + 3][y + 4] = "_";
        board[x + 4][y + 4] = "_";
        board[x + 5][y + 4] = "_";


    }

    /**
     * Draw the pile indicator.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param input The String to be drawn inside.
     */
    private void drawIndicator(int x, int y, String input) {

        board[x][y] = "[";
        board[x + 1][y] = input;
        board[x + 2][y] = "]";

    }

    /**
     * Draw the pile indicator with an index.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param input The String to be drawn inside.
     * @param index The index to be drawn.
     */
    private void drawIndicator(int x, int y, String input, int index) {

        board[x][y] = "[";
        board[x + 1][y] = input;
        board[x + 2][y] = "" + index;
        board[x + 3][y] = "]";

    }

    /**
     * Draws a hidden card (face down).
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    private void drawHiddenCard(int x, int y) {

        board[x][y] = " ";
        board[x + 1][y] = "_";
        board[x + 2][y] = "_";
        board[x + 3][y] = "_";
        board[x + 4][y] = "_";
        board[x + 5][y] = "_";
        board[x + 6][y] = " ";

        board[x][y+ 1] = "|";
        board[x + 1][y + 1] = RED + "▒" + RESET;
        board[x + 2][y + 1] = RED + "▒" + RESET;
        board[x + 3][y + 1] = RED + "▒" + RESET;
        board[x + 4][y + 1] = RED + "▒" + RESET;
        board[x + 5][y + 1] = RED + "▒" + RESET;
        board[x + 6][y + 1] = "|";

    }

    /**
     * Draw a card inside a stack.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param number Number of the card.
     * @param suit Suit of the card.
     * @param colour Colour of the card.
     */
    private void drawStackedCard(int x, int y, String number, String suit, String colour) {

        board[x][y + 1] = "|";
        board[x + 4][y + 1] = "_";
        board[x + 5][y + 1] = "_";
        board[x + 6][y + 1] = "|";

        if (number.length() < 2) {

            board[x + 1][y + 1] = getCardColour(colour, number);
            board[x + 2][y + 1] = getCardColour(colour, suit);
            board[x + 3][y + 1] = "_";

        } else {

            board[x + 1][y + 1] = getCardColour(colour, number);
            board[x + 2][y + 1] = getCardColour(colour, suit);
            board[x + 3][y + 1] = "";

        }

    }

    /**
     * Draws the card's value and suit.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param number The number of the card.
     * @param suit The suit of the card.
     * @param colour The colour of the card.
     */
    private void drawCardValues(int x, int y, String number, String suit, String colour) {

        // Card number and Suit
        if (number.equals("10")) {
            board[x + 2][y + 2] = getCardColour(colour, "1");
            board[x + 3][y + 2] = getCardColour(colour, "0");

        } else {
            board[x + 3][y + 2] = getCardColour(colour, number);

        }

        board[x + 3][y + 3] = getCardColour(colour, suit);

    }

    /**
     * Draws a card which is inside the waste pile.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param number Value of the card.
     * @param suit Suit of the card.
     * @param colour Colour of the card.
     */
    private void drawWasteValues(int x, int y, String number, String suit, String colour) {

        if (number.equals("10")) {
            board[x + 1][y + 1] = getCardColour(colour, "1");
            board[x + 1][y + 2] = getCardColour(colour, "0");
            board[x + 1][y + 3] = getCardColour(colour, suit);

        } else {
            board[x + 1][y + 1] = getCardColour(colour, number);
            board[x + 1][y + 2] = getCardColour(colour, suit);

        }

    }

    /**
     * Gets the colour of the card and changes it if RED.
     * @param colour The colour of the card.
     * @param input The input to be drawn.
     * @return Returns the String with a colour if conditions are met.
     */
    private String getCardColour(String colour, String input) {

        if (colour.equals("red")) {
            return RED + input + RESET;

        }

        return input;

    }

    /**
     * Draw the move status on the board.
     * @param valid (true/false) if the move was valid or not.
     */
    private void printValidMove(boolean valid) {

        // // line 40

        String moveStatus = "";

        if (valid) {
            moveStatus = "MOVE STATUS: VALID";

        } else {
            moveStatus = "MOVE STATUS: INVALID";

        }

        for (int index = 0; index < moveStatus.length(); index++) {
            board[1 + index][height - 1] = Character.toString(moveStatus.charAt(index));

        }

    }

    /**
     * Draws the number of moves into the array.
     * @param moveCount Number of moves performed.
     */
    private void printMoveCount(int moveCount) {

        String moveCountString = "# OF MOVES : " + moveCount;

        for (int index = 0; index < moveCountString.length(); index++) {
            board[1 + index][height - 2] = Character.toString(moveCountString.charAt(index));

        }

    }

    /**
     * Draws the desired message on the side of the array.
     * @param message The message to be drawn.
     */
    private void printHelpMessage(String[] message) {

        for (int layer = 0; layer < message.length; layer++) {
            board[width - 1][height - 7 - layer] = message[message.length - 1 - layer];

        }

    }

}
