package Solitaire;

public class Command {

    // Fields
    private String word1, word2, word3;

    // Utility word commands
    private final String[] utilityWords = {

        "undo",
        "help",
        "s",
        "restart",
        "new"
        
    };

    // Location word commands
    private final String[] locationWords = {

        "w",
        "f1", "f2", "f3", "f4",
        "p1", "p2", "p3", "p4", "p5", "p6", "p7",

    };

    // Values word commands for specific card moving
    private final String[] valueWords = {

        "a", "2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k"

    };


    /**
     * Constructor for single word user-inputs.
     * @param word1 the single word the user inputs.
     */
    public Command(String word1) {

        // In the case that "help" or "undo" are used
        this.word1 = word1;
        word2 = null;
        word3 = null;

    }

    /**
     * Constructor for double word user-inputs.
     * @param word1 first word in the user input.
     * @param word2 second word in the user input.
     */
    public Command(String word1, String word2) {

        // In the case that we are moving a single card from x to y
        this.word1 = word1;
        this.word2 = word2;
        word3 = null;

    }

    /**
     * Constructor for triple word user-inputs.
     * @param word1 first word in the user input.
     * @param word2 second word in the user input.
     * @param word3 third word in the user input.
     */
    public Command(String word1, String word2, String word3) {

        // In the case we are moving multiple cards from x to y
        this.word1 = word1;
        this.word2 = word2;
        this.word3 = word3;

    }

    /**
     * Validates whether the user input consists of the valid utility/location command words in the right order
     * @return (true/false) depending on the validity of the user input.
     */
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

    /**
     * Validates whether the single words are valid commands
     * @param word User input word.
     * @return a string based on which category the word calls under.
     */
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

    /**
     * Getter for the first word.
     * @return The first word.
     */
    public String getFirstWord() {

        return word1;

    }

    /**
     * Getter for the second word.
     * @return The second word.
     */
    public String getSecondWord() {

        return word2;

    }

    /**
     * Getter for the third word.
     * @return The third word.
     */
    public String getThirdWord() {

        return word3;

    }

}
