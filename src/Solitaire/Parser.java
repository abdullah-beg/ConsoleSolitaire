package Solitaire;

import java.util.Scanner;

public class Parser {

    private Scanner userInput;
    
    private final String[] validCommands = {

        "s", "w", // Stock and Waste piles

        "f1", "f2", "f3", "f4", // Foundation Piles

        "f1", "f2", "f3", "f4", "f5", "f6", "f7", // Tableau Piles

        "undo", 

        "help"
        
    };

    public Parser() {
        userInput = new Scanner(System.in);
    }

    public Command getUserCommand() {
        System.out.print(">");
        String[] command = userInput.nextLine().toLowerCase().stripLeading().split(" ", 4); 

        if (command.length == 1) {
            return new Command(command[0]);

        } else if (command.length == 2) {
            return new Command(command[0], command[1]);

        }

        // If it has reached here, it means that the command is greater than or equal to 3 words
        return new Command(command[0], command[1], command[2]);

    }

}
