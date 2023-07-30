package tracker.util;

import java.util.Scanner;

public class InputHandler {
    private final Scanner SCAN = new Scanner(System.in);

    public InputHandler() {
    }

    public String getNextString() {
        String userInput = SCAN.nextLine();
        if (userInput == null || userInput.isBlank()) {
            return "";
        }
        return userInput;
    }
}
