package Solitaire;

public class Command {

    private String word1, word2, word3;

    // In the case that "help" or "undo" are used
    public Command(String word1) {
        this.word1 = word1;
        word2 = null;
        word3 = null;

    }

    // In the case that we are moving a single card from x to y
    public Command(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
        word3 = null;

    }

    // In the case we are moving multiple cards from x to y
    public Command(String word1, String word2, String word3) {
        this.word1 = word1;
        this.word2 = word2;
        this.word3 = word3;

    }

    // Accessor Methods

    public String getFirstWord() {

        return word1;

    }

    public String getSecondWord() {

        return word2;

    }

    public String getThirdWord() {

        return word3;

    }


    
}
