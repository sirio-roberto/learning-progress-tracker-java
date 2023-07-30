package tracker;

import tracker.entities.Student;
import tracker.util.InputHandler;
import tracker.util.Utils;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class TrackerApp {
    private boolean isAppRunning;
    private final InputHandler in;
    private final HashSet<Command> commands;
    private final HashSet<Student> students;

    public TrackerApp() {
        isAppRunning = false;
        in = new InputHandler();
        students = new LinkedHashSet<>();

        commands = new HashSet<>();
        commands.add(new AddStudentsCommand("add students"));
        commands.add(new ListCommand("list"));
        commands.add(new ExitCommand("exit"));
    }

    public void run() {
        isAppRunning = true;
        System.out.println("Learning Progress Tracker");

        while (isAppRunning) {
            String userInput = in.getNextString();
            if (userInput.isBlank()) {
                System.out.println("No input.");
            } else if ("back".equals(userInput)) {
                System.out.println("Enter 'exit' to exit the program.");
            } else {
                executeCommandIfExists(userInput);
            }
        }
    }

    private void executeCommandIfExists(String userInput) {
        for (Command command: commands) {
            if (userInput.equals(command.getName())) {
                command.execute();
                return;
            }
        }
        System.out.println("Error: unknown command!");
    }

    abstract class Command {
        private String name;

        public Command(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        abstract void execute();
    }

    class AddStudentsCommand extends Command {

        public AddStudentsCommand(String name) {
            super(name);
        }

        @Override
        public void execute() {
            int addedStudents = 0;

            System.out.println("Enter student credentials or 'back' to return:");
            String credentials = in.getNextString();
            while (!"back".equals(credentials)) {
                if (Utils.areValidUserCredentials(credentials)) {
                    String[] fields = credentials.split(" ");

                    String firstName = fields[0];
                    String lastName = Utils.getMiddleStringFromArray(fields);
                    String emailAddress = fields[fields.length - 1];

                    Student student = new Student(firstName, lastName, emailAddress);
                    if (students.contains(student)) {
                        System.out.println("This email is already taken.");
                    } else {
                        students.add(student);
                        addedStudents++;
                        System.out.println("The student has been added.");
                    }
                }
                credentials = in.getNextString();
            }
            System.out.printf("Total %s students have been added.\n", addedStudents);
        }
    }

    class ListCommand extends Command {

        public ListCommand(String name) {
            super(name);
        }

        @Override
        void execute() {
            if (students.isEmpty()) {
                System.out.println("No students found");
            } else {
                System.out.println("Students:");
                students.forEach(s -> System.out.println(s.getId()));
            }
        }
    }

    class ExitCommand extends Command {

        public ExitCommand(String name) {
            super(name);
        }

        @Override
        void execute() {
            isAppRunning = false;
            System.out.println("Bye!");
        }
    }
}
