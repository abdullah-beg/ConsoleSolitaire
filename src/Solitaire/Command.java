package Solitaire;

public class Command {

    private String word1, word2, word3;

    private final String[] utilityWords = {

        "undo",
        "help",
        "s",
        "restart",
        "new"
        
    };

    private final String[] locationWords = {

        "w",
        "f1", "f2", "f3", "f4",
        "p1", "p2", "p3", "p4", "p5", "p6", "p7",

    };

    private final String[] valueWords = {

        "a", "2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k"

    };


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

    public boolean validateCommand() {

        String userCommand = "" + validateWord(word1) + validateWord(word2) + validateWord(word3);

        if (userCommand.equals("LLx")) {
            if (word2.equals("w")) {
                return false;

            } else if (word1.equals(word2)) {
                return false;

            }

            return true;

        } else if (userCommand.equals("LVL")) {
            return true;

        } else if (Character.toString(userCommand.charAt(0)).equals("U")) {
            
            switch (word1) {

                case "help" : return true;

                default : word2 = null; word3 = null; return true;

            }

        }

        return false;

    }

    private String validateWord(String word) {

        if (word == null) {
            return "x";

        } 

        for (String s: utilityWords) {
            if (word.equals(s)) {
                return "U"; // for (U)tility

            }

        }

        for (String s: locationWords) {
            if (word.equals(s)) {
                return "L"; // for (L)ocation

            }

        }

        for (String s: valueWords) {
            if (word.equals(s)) {
                return "V"; // for (V)alue

            }

        }

        return "I"; // for (I)nvalid

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
