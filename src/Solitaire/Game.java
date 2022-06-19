package Solitaire;

import java.util.ArrayList;

public class Game {
    

        // game map 87 x 41
        private GameBoard game;
        private Parser parser;
        private Logic logic;
        private State state;
        private boolean validMove;
    
        public Game() {
    
            parser = new Parser();
            game = new GameBoard();
            logic = new Logic();
            state = new State(game.getWaste(), game.getStock(), game.getFoundations(), game.getPiles());
            validMove = false;
    
        }

        public State getState() {

            return state;

        }

        public GameBoard getBoard() {

            return game;

        }

        public Parser getParser() {

            return parser;

        }

        public Logic getLogic() {

            return logic;

        }
    
        public boolean gameFinished() {
    
            for (Foundation foundation : game.getFoundations()) {
                if (foundation.isEmpty() || foundation.getBottomCard().getCardNumber() != 13) {
                    return false;
    
                }
    
            }
    
            return true;
    
        }
    
        public void processCommand(String word1) {
    
            if (word1.equals("help")) {
                //TODO
                System.out.println("yet to be written");
                return;
    
            } else if (word1.equals("undo")) {
                System.out.println("idk how to undo yet");
                return;
    
            } else if (word1.equals("restart")) {
                restartGame();
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
    
        private String convertInput(String input) {
    
            if (input.equals("a")) {
                return "1";
    
            } else if (input.equals("j")) {
                return "11";
    
            } else if (input.equals("q")) {
                return "12";
    
            } else if (input.equals("k")) {
                return "13";
    
            }
    
            return input;
    
        }
    
        public void setValidMove(boolean input) {
    
            validMove = input;
    
        }
    
        public boolean getValidMove() {
    
            return validMove;
    
        }

        private void restartGame() {

            System.out.println("Restarting game...");
            game = new GameBoard();

        }

}
