package Solitaire;

import java.util.ArrayList;

public class Draw {
    
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String RED = "\033[0;31m";     // RED
    public static final String RESET = "\033[0m";  // Text Reset
    
    private String[][] board;

    public Draw() {

        board = new String[89][42];

    }

    public void clearBoard() {

        for (int x = 0; x < 89; x++) {
            for (int y = 0; y < 42; y++) {
                board[x][y] = " ";

            }

        }

    }

    public void printBoard(int cardCount, Waste waste) {

        clearBoard();
        printStock(cardCount);
        printWaste(waste);
        

        for (int y = 0; y < 42; y++) {
            for (int x = 0; x < 89; x++) {
                System.out.print(board[x][y]);
            
            }

            System.out.println();

        }


    }

    public void printStock(int cardCount) {

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

    public void drawCardTemplate(int x, int y) {

        // Card top

        board[x][y] = " ";
        board[x + 1][y] = "_";
        board[x + 2][y] = "_";
        board[x + 3][y] = "_";
        board[x + 4][y] = "_";
        board[x + 5][y] = "_";
        board[x + 6][y] = " ";
        
        // Card sides
        // for (int n = 0; n < 5; n++) {
        //     for (int m = 1; m < 4; m++) {
        //         if (n == 0 || n == 6) {                
        //             board[x + n][y + m] = "|";

        //         } else {
        //             board[x + n][y + m] = " ";

        //         }
        //     }

        // }

        for (int m = 1; m < 5; m++) {
            for (int n = 0; n < 7; n++) {
                if (n == 0 || n == 6) {
                    board[x + n][y + m] = "|";

                } else {
                    board[x + n][y + m] = " ";

                }
                
            }

        }

        // board[x][y + 1] = "|";
        // board[x + 1][y + 1] = "|";
        // board[x + 2][y + 1] = "|";
        // board[x + 3][y + 1] = "|";
        // board[x + 4][y + 1] = "|";
        // board[x + 5][y + 1] = "|";
        // board[x + 6][y + 1] = "|";

        // board[x][y + 2] = "|";
        // board[x + 6][y + 2] = "|";

        // board[x][y + 3] = "|";
        // board[x + 6][y + 3] = "|";

        // board[x][y + 4] = "|";
        // board[x + 6][y + 4] = "|";

        // Card bottom
        board[x + 1][y + 4] = "_";
        board[x + 2][y + 4] = "_";
        board[x + 3][y + 4] = "_";
        board[x + 4][y + 4] = "_";
        board[x + 5][y + 4] = "_";


    }

    public void drawEmptySpace(int x, int y) {

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

    public void printWaste(Waste waste) {

        if (waste.getCardCount() == 0) {

            drawEmptySpace(15,3);

        } else if (waste.getCardCount() == 1) {

            drawCardTemplate(15,3);
            drawCardTemplate(18,3);

            // Clearing Overlaps
            board[18][3] = " ";
            board[21][4] = " ";
            board[21][5] = " ";
            board[21][6] = " ";

        }

    }


}
