package Solitaire;

import java.util.Scanner;

public class Parser {

    private final Scanner userInput;

    /**
     * Constructor for Parser.
     */
    public Parser() {

        userInput = new Scanner(System.in);

    }

    /**
     * Reads the user input and returns it in the format of a segmented command.
     * @return Command, either 1, 2 or 3 word command.
     */
    public Command getUserCommand() {
        System.out.print(">");
        String[] command = userInput.nextLine().toLowerCase().stripLeading().stripTrailing().split(" ", 4); 

        if (command.length == 1) {
            return new Command(command[0]);

        } else if (command.length == 2) {
            return new Command(command[0], command[1]);

        }

        // If it has reached here, it means that the command is greater than or equal to 3 words
        // we will only take the first 3 words as input, the rest is ignored.
        return new Command(command[0], command[1], command[2]);

    }



}
