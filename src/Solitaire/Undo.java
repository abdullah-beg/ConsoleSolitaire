package Solitaire;

import java.util.Stack;

public class Undo {

    private Stack<State> moveStack;

    private State baseState;

    /**
     * Constructor for Undo.
     */
    public Undo() {

        moveStack = new Stack<>();
        
    }

    /**
     * Changes the base state of the game. (The state before the user has performed any moves)
     * @param baseState The new original state of the board.
     */
    public void setBaseState(State baseState) {

        this.baseState = baseState;

    }

    /**
     * Getter for the baseState field.
     * @return The baseState field.
     */
    public State getBaseState() {

        return baseState;

    }

    /**
     * Clears the performed moves.
     */
    public void clearMoveStack() {

        moveStack = new Stack<>();

    }

    /**
     * Adds a new state to the moveStack.
     * @param state The state to be added.
     */
    public void doMove(State state) {

        moveStack.add(state);

    }

    /**
     * Undoes a move and remove it from the stack.
     */
    public void undoMove() {

        moveStack.pop();
        
    }

    /**
     * Get the most recent board state.
     * @return The most recent state of the board.
     */
    public State getMostRecentState() {

        return moveStack.peek();

    }

    /**
     * Get the size of the moveStack.
     * @return The size of the moveStack.
     */
    public int getMoveStackSize() {

        return moveStack.size();
        
    }
    
}
