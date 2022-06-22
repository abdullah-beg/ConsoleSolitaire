package Solitaire;

import java.util.Stack;

public class Undo {

    private Stack<State> moveStack;

    private State baseState;

    public Undo() {

        moveStack = new Stack<>();
        
    }   
    
    public void setBaseState(State baseState) {

        this.baseState = baseState;

    }

    public State getBaseState() {

        return baseState;

    }

    public void doMove(State state) {

        moveStack.add(state);
        System.out.println("Size after adding : " + moveStack.size());

    }

    public void undoMove() {

        moveStack.pop();
        System.out.println("Size after removing : " + moveStack.size());
        
    }

    public State getMostRecentState() {

        return moveStack.peek();

    }

    public int getMoveStackSize() {

        return moveStack.size();
        
    }
    
}
