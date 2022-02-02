package Solitaire;

import java.util.ArrayList;

public class GameBoard {
    
    private ArrayList<Pile> piles;

    public GameBoard() {

        piles = new ArrayList<>();

        for(int i = 0; i < 7; i++) {
            piles.add(new Pile());

        }

    }

    public Pile getPile(int index) {

        return piles.get(index);

    }

}
