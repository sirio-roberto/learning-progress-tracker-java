package tracker;

import tracker.entities.Student;
import tracker.entities.StudentScore;
import tracker.util.InputHandler;
import tracker.util.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

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
        commands.add(new AddPointsCommand("add points"));
        commands.add(new FindCommand("find"));
        commands.add(new StatisticsCommand("statistics"));
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

    abstract static class Command {
        private final String name;

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

    class FindCommand extends Command {

        public FindCommand(String name) {
            super(name);
        }

        @Override
        void execute() {
            System.out.println("Enter an id or 'back' to return:");
            String userInput = in.getNextString();
            while (!"back".equals(userInput)) {
                Student student = Utils.getStudentById(students, userInput);

                if (student != null) {
                    System.out.println(student);
                } else {
                    System.out.printf("No student is found for id=%s.\n", userInput);
                }

                userInput = in.getNextString();
            }
        }
    }

    class AddPointsCommand extends Command {

        public AddPointsCommand(String name) {
            super(name);
        }

        @Override
        void execute() {
            System.out.println("Enter an id and points or 'back' to return:");
            String userInput = in.getNextString();
            while (!"back".equals(userInput)) {
                if (Utils.isValidPointsFormat(userInput)) {
                    String[] fields = userInput.split(" ");
                    String id = fields[0];
                    Student student = Utils.getStudentById(students, id);

                    if (student != null) {
                        student.addPoints(Arrays.copyOfRange(fields, 1, fields.length));
                        System.out.println("Points updated.");
                    } else {
                        System.out.printf("No student is found for id=%s.\n", id);
                    }
                } else {
                    System.out.println("Incorrect points format.");
                }
                userInput = in.getNextString();
            }
        }
    }

    class StatisticsCommand extends Command {

        public StatisticsCommand(String name) {
            super(name);
        }

        @Override
        void execute() {
            System.out.println("Type the name of a course to see details or 'back' to quit:");
            printCourseStatistics();
            String userInput = in.getNextString();
            while (!"back".equals(userInput)) {
                try {
                    Student.Course course = Student.Course.valueOf(userInput.toUpperCase());
                    List<StudentScore> orderedScores = students.stream()
                            .map(s -> new StudentScore(s, course))
                            .filter(score -> score.getPoints() > 0)
                            .sorted()
                            .toList();

                    System.out.println(course.getName());
                    System.out.println("id       points completed");
                    orderedScores.forEach(System.out::println);
                } catch (IllegalArgumentException ex) {
                    System.out.println("Unknown course.");
                }

                userInput = in.getNextString();
            }
        }

        private void printCourseStatistics() {
            System.out.println("""
                    Most popular: n/a
                    Least popular: n/a
                    Highest activity: n/a
                    Lowest activity: n/a
                    Easiest course: n/a
                    Hardest course: n/a""");
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
