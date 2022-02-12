package Solitaire;

import java.util.ArrayList;

public class Draw {
    
    // private static final String BLUE = "\033[0;34m";    // BLUE
    private static final String RED = "\033[0;31m";     // RED
    private static final String RESET = "\033[0m";  // Text Reset
    
    private String[][] board;

    public Draw() {

        board = new String[89][42];

    }

    private void clearBoard() {

        for (int x = 0; x < 89; x++) {
            for (int y = 0; y < 42; y++) {
                board[x][y] = " ";

            }

        }

    }

    public void printBoard(int cardCount, Waste waste, ArrayList<Foundation> foundations, ArrayList<Pile> piles, boolean move) {

        clearBoard();
        printStock(cardCount);
        printWaste(waste);
        printFoundations(foundations);
        printTablePiles(piles);
        printGameLabels(waste);
        printValidMove(move);

        for (int y = 0; y < 42; y++) {
            for (int x = 0; x < 89; x++) {
                System.out.print(board[x][y]);
            
            }

            System.out.println();

        }


    }

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

    private void printFoundations(ArrayList<Foundation> foundations) {

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

    private void printTablePiles(ArrayList<Pile> piles) {

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

    private void drawIndicator(int x, int y, String input) {

        board[x][y] = "[";
        board[x + 1][y] = input;
        board[x + 2][y] = "]";

    }

    private void drawIndicator(int x, int y, String input, int index) {

        board[x][y] = "[";
        board[x + 1][y] = input;
        board[x + 2][y] = "" + index;
        board[x + 3][y] = "]";

    }

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

    private void drawCard(int x, int y, String number, String suit) {

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


       drawCard(x, y, number, suit);

    }

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

    private String getCardColour(String colour, String input) {

        if (colour.equals("red")) {
            return RED + input + RESET;

        }

        return input;

    }

    private void printValidMove(boolean valid) {

        // // line 40

        String moveStatus = "";

        if (valid) {
            moveStatus = "MOVE STATUS: VALID";

        } else {
            moveStatus = "MOVE STATUS: INVALID";

        }

        board[0][41] = moveStatus;

        for (int i = 0; i < moveStatus.length(); i++) {
            board[1 + i][40] = "";

        }

    }
}
