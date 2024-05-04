package cis2039.pocketbeasts.utils;

import java.util.Arrays;
import java.util.Scanner;

public class InputHandler {

    public Scanner scanner;

    /**
     * Constructor for the InputHandler class.
     */
    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prompts the user for input and returns the response.
     *
     * @param prompt The prompt to display to the user.
     * @return The user's response.
     */
    public String getPrompt(String prompt, String[] validResponse) {
        System.out.println(prompt);

        String response = this.scanner.nextLine();

        if (Arrays.stream(validResponse).anyMatch(response::equals)) {
            return response;
        }

        return getPrompt(prompt, validResponse);
    }

    /**
     * Prompts the user for a yes or no response.
     *
     * @param prompt The prompt to display to the user.
     * @return The user's response.
     */
    public boolean yesNoPrompt(String prompt) {
        String response = getPrompt(prompt, new String[]{"Yes", "yes", "y", "No", "no", "n"});
        return response.equals("Yes") || response.equals("yes") || response.equals("y");
    }
}
