package tracker.entities;

public class StudentScore implements Comparable<StudentScore> {
    private final String id;
    private final int points;
    private final double completedPercentage;

    public StudentScore (Student student, Student.Course course) {
        this.id = student.getId();
        this.points = student.getCourses().get(course);
        this.completedPercentage = getCompletedPercentage(student, course);
    }

    public int getPoints() {
        return points;
    }

    private double getCompletedPercentage(Student student, Student.Course course) {
        int currentPoints = student.getCourses().get(course);
        return currentPoints * 100.0 / course.getPointsToComplete();
    }

    @Override
    public String toString() {
        return String.format("%8s %-6s %.1f%%", id, points, completedPercentage);
    }

    @Override
    public int compareTo(StudentScore other) {
        int completionComp = Double.compare(other.completedPercentage, this.completedPercentage);
        if (completionComp == 0) {
            return id.compareTo(other.id);
        } else {
            return completionComp;
        }
    }
}
