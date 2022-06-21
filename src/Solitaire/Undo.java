package Solitaire;

import java.util.Stack;

public class Undo {

    private Stack<State> moveStack;

    public Undo() {

        moveStack = new Stack<>();

    }    

    public void doMove(State state) {

        moveStack.add(state);

    }

    public void undoMove() {

        moveStack.pop();
        
    }

    public State getMostRecentState() {

        return moveStack.peek();

    }

    public boolean isUndoable() {

        return moveStack.size() > 1;

    }
    
}
