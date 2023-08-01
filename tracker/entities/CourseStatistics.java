package tracker.entities;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CourseStatistics {
    private final Student.Course course;
    private int popularity;
    private int activity;
    private int totalPoints;

    private final HashSet<String> enrolledStudentIds;

    public CourseStatistics(Student.Course course) {
        this.course = course;
        popularity = 0;
        activity = 0;
        totalPoints = 0;
        enrolledStudentIds = new HashSet<>();
    }

    public Student.Course getCourse() {
        return course;
    }

    public void updateStatistics(String studentId, int points) {
        if (points > 0) {
            if (!enrolledStudentIds.contains(studentId)) {
                popularity++;
                enrolledStudentIds.add(studentId);
            }
            activity++;
            totalPoints += points;
        }
    }

    public double getAverageScore() {
        if (activity > 0) {
            return (double) totalPoints / activity;
        } else {
            return 0.0;
        }
    }

    public static Set<Student.Course> getMostPopularCourses(Set<CourseStatistics> courseStatistics) {
        CourseStatistics mostPopular = courseStatistics.stream()
                .max(Comparator.comparingInt(stat -> stat.popularity))
                .filter(stat -> stat.popularity > 0)
                .orElse(null);

        if (mostPopular == null) {
            return new HashSet<>();
        }
        return courseStatistics.stream()
                .filter(stat -> stat.popularity == mostPopular.popularity)
                .map(CourseStatistics::getCourse)
                .collect(Collectors.toSet());
    }

    public static Set<Student.Course> getLeastPopularCourses(Set<CourseStatistics> courseStatistics) {
        Set<Student.Course> mostPopular = getMostPopularCourses(courseStatistics);
        if (mostPopular.isEmpty()) {
            return new HashSet<>();
        }
        CourseStatistics leastPopular = courseStatistics.stream()
                .min(Comparator.comparingInt(stat -> stat.popularity))
                .filter(stat -> stat.popularity > 0)
                .orElse(null);

        if (leastPopular == null || mostPopular.contains(leastPopular.getCourse())) {
            return new HashSet<>();
        }
        return courseStatistics.stream()
                .filter(stat -> stat.popularity == leastPopular.popularity)
                .map(CourseStatistics::getCourse)
                .collect(Collectors.toSet());
    }

    public static Set<Student.Course> getCoursesWithHighestActivity(Set<CourseStatistics> courseStatistics) {
        CourseStatistics highestActivity = courseStatistics.stream()
                .max(Comparator.comparingInt(stat -> stat.activity))
                .filter(stat -> stat.activity > 0)
                .orElse(null);

        if (highestActivity == null) {
            return new HashSet<>();
        }
        return courseStatistics.stream()
                .filter(stat -> stat.activity == highestActivity.activity)
                .map(CourseStatistics::getCourse)
                .collect(Collectors.toSet());
    }

    public static Set<Student.Course> getCoursesWithLowestActivity(Set<CourseStatistics> courseStatistics) {
        Set<Student.Course> highestActivity = getCoursesWithHighestActivity(courseStatistics);
        if (highestActivity.isEmpty()) {
            return new HashSet<>();
        }
        CourseStatistics lowestActivity = courseStatistics.stream()
                .min(Comparator.comparingInt(stat -> stat.activity))
                .filter(stat -> stat.activity > 0)
                .orElse(null);

        if (lowestActivity == null || highestActivity.contains(lowestActivity.getCourse())) {
            return new HashSet<>();
        }
        return courseStatistics.stream()
                .filter(stat -> stat.activity == lowestActivity.activity)
                .map(CourseStatistics::getCourse)
                .collect(Collectors.toSet());
    }

    public static Set<Student.Course> getHardestCourses(Set<CourseStatistics> courseStatistics) {
        CourseStatistics hardest = courseStatistics.stream()
                .min(Comparator.comparingDouble(CourseStatistics::getAverageScore))
                .filter(stat -> stat.getAverageScore() > 0.0)
                .orElse(null);

        if (hardest == null) {
            return new HashSet<>();
        }
        return courseStatistics.stream()
                .filter(stat -> stat.getAverageScore() == hardest.getAverageScore())
                .map(CourseStatistics::getCourse)
                .collect(Collectors.toSet());
    }

    public static Set<Student.Course> getEasiestCourses(Set<CourseStatistics> courseStatistics) {
        Set<Student.Course> hardest = getHardestCourses(courseStatistics);
        if (hardest.isEmpty()) {
            return new HashSet<>();
        }
        CourseStatistics easiest = courseStatistics.stream()
                .max(Comparator.comparingDouble(CourseStatistics::getAverageScore))
                .filter(stat -> stat.getAverageScore() > 0.0)
                .orElse(null);

        if (easiest == null || hardest.contains(easiest.getCourse())) {
            return new HashSet<>();
        }
        return courseStatistics.stream()
                .filter(stat -> stat.getAverageScore() == easiest.getAverageScore())
                .map(CourseStatistics::getCourse)
                .collect(Collectors.toSet());
    }

    public static Set<CourseStatistics> initCourseStatisticsSet() {
        Set<CourseStatistics> courseStatistics = new HashSet<>();
        for (Student.Course course: Student.Course.values()) {
            courseStatistics.add(new CourseStatistics(course));
        }
        return courseStatistics;
    }
}
