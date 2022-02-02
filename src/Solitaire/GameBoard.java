package Solitaire;

public class GameBoard {
    
    private Pile p1, p2, p3, p4, p5, p6, p7;

    public GameBoard() {

        p1 = new Pile();
        p2 = new Pile();
        p3 = new Pile();
        p4 = new Pile();
        p5 = new Pile();
        p6 = new Pile();
        p7 = new Pile();

    }

    public Pile getPile(int pile) {

        switch (pile) {

            case 0: return p1;
            case 1: return p2;
            case 2: return p3;
            case 3: return p4;
            case 4: return p5;
            case 5: return p6;
            case 6: return p7;
        }

        return null;

    }




}
