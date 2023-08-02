package tracker.entities;

import tracker.util.Utils;

import java.util.*;

public class Student {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;

    private Map<Course, Integer> courses;
    private Map<Course, Boolean> courseCompletionMap;

    public Student(String firstName, String lastName, String emailAddress) {
        this.id = Utils.getRandomId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        initCourses();
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

    public Map<Course, Integer> getCourses() {
        return courses;
    }

    private void initCourses() {
        courses = new LinkedHashMap<>();
        courseCompletionMap = new LinkedHashMap<>();

        for (Course course: Course.values()) {
            courses.put(course, 0);
            courseCompletionMap.put(course, false);
        }
    }

    public void addPoints(String[] pointsArray) {
        int index = 0;
        for (Course course: courses.keySet()) {
            int points = Integer.parseInt(pointsArray[index]);
            courses.put(course, courses.get(course) + points);
            index++;
        }
    }

    public Set<Course> getRecentlyCompletedCourses() {
        Set<Course> compCourses = new HashSet<>();
        for (Course course: Course.values()) {
            if (courses.get(course) >= course.pointsToComplete) {
                if (!courseCompletionMap.get(course)) {
                    compCourses.add(course);
                    courseCompletionMap.put(course, true);
                }
            }
        }
        return compCourses;
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
        JAVA("Java", 600),
        DSA("DSA", 400),
        DATABASES("Databases", 480),
        SPRING("Spring", 550);

        private final String name;
        private final int pointsToComplete;

        Course(String name, int pointsToComplete) {
            this.name = name;
            this.pointsToComplete = pointsToComplete;
        }

        public String getName() {
            return name;
        }

        public int getPointsToComplete() {
            return pointsToComplete;
        }
    }
}
