package tracker.entities;

import tracker.util.Utils;

import java.util.*;

public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;

    private Map<Course, Integer> courses;

    public Student(String firstName, String lastName, String emailAddress) {
        this.id = Utils.getRandomId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.courses = initCourses();
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    private Map<Course, Integer> initCourses() {
        Map<Course, Integer> map = new LinkedHashMap<>();
        for (Course course: Course.values()) {
            map.put(course, 0);
        }
        return map;
    }

    public void addPoints(String[] pointsArray) {
        int index = 0;
        for (Course course: courses.keySet()) {
            int points = Integer.parseInt(pointsArray[index]);
            courses.put(course, courses.get(course) + points);
            index++;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getId());
        result.append(" points: ");
        for (Course course: courses.keySet()) {
            result.append(course.getName()).append("=").append(courses.get(course)).append("; ");
        }
        result.deleteCharAt(result.length() - 1);
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(emailAddress, student.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

    public enum Course {
        JAVA("Java"),
        DSA("DSA"),
        DATABASES("Databases"),
        SPRING("Spring");

        private final String name;

        Course(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
