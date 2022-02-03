package Solitaire;

import java.util.ArrayList;

public class GameBoard {
    
    private ArrayList<Pile> piles;
    private Stock stock;
    private Waste waste;

    public GameBoard() {

        piles = new ArrayList<>();
        stock = new Stock();
        waste = new Waste();

        for(int i = 0; i < 7; i++) {
            piles.add(new Pile());

        }

    }

    public Stock getStock() {

        return stock;

    }

    public Waste getWaste() {

        return waste;

    }

    public Pile getPile(int index) {

        return piles.get(index);

    }

}
