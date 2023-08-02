package tracker.util;

import tracker.entities.Student;

import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String getMiddleStringFromArray(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < fields.length - 1; i++) {
            result.append(fields[i]).append(" ");
        }
        return result.toString().trim();
    }

    public static boolean areValidUserCredentials(String credentials) {
        if (credentials.isBlank()) {
            System.out.println("Incorrect credentials.");
            return false;
        }
        String[] fields = credentials.split(" ");
        if (fields.length < 3) {
            System.out.println("Incorrect credentials.");
            return false;
        }
        String firstName = fields[0];
        if (isInvalidName(firstName)) {
            System.out.println("Incorrect first name.");
            return false;
        }
        String lastName = getMiddleStringFromArray(fields);
        if (!areValidNames(lastName)) {
            System.out.println("Incorrect last name.");
            return false;
        }
        String emailAddress = fields[fields.length - 1];
        if (!isValidEmailAddress(emailAddress)) {
            System.out.println("Incorrect email");
            return false;
        }
        return true;
    }

    private static boolean isValidEmailAddress(String emailAddress) {
        return emailAddress.matches("(?i)[a-z0-9.]+@[a-z0-9]+\\.[a-z0-9]+");
    }

    private static boolean areValidNames(String lastName) {
        String[] names = lastName.split(" ");
        for (String name: names) {
            if (isInvalidName(name)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isInvalidName(String name) {
        if (name.length() < 2) {
            return true;
        }
        if (!name.matches("(?i)[a-z]+[a-z'-]*[a-z]+")) {
            return true;
        }
        Matcher matcher = Pattern.compile("['-]{2,}").matcher(name);
        return matcher.find();
    }

    public static String getRandomId() {
        Random random = new Random();
        int intId = random.nextInt(100000000);
        return String.format("%8s", intId).replaceAll(" ", "0");
    }

    public static Student getStudentById(Set<Student> students, String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public static boolean isValidPointsFormat(String userInput) {
        return userInput.matches("(?i)[a-z0-9]+ \\d+ \\d+ \\d+ \\d+");
    }

    public static String getSetStringOrNA(Set<Student.Course> courses) {
        if (courses.isEmpty()) {
            return "n/a";
        }

        return String.join(", ", courses.stream().map(Student.Course::getName).toList());
    }
}
