package tracker;

import tracker.util.InputHandler;

public class TrackerApp {
    private final InputHandler in = new InputHandler();

    public void run() {
        System.out.println("Learning Progress Tracker");
        String userInput = in.getNextString();
        while (!userInput.equals("exit")) {
            if (userInput.isBlank()) {
                System.out.println("No input.");
            } else {
                System.out.println("Error: unknown command!");
            }
            userInput = in.getNextString();
        }
        System.out.println("Bye!");
    }
}
