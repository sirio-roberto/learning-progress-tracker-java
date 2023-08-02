# Learning Progress Tracker Java App

The Learning Progress Tracker is a Java application that allows you to track the learning progress of students in different courses. It provides various commands to manage students, their course progress, and statistics related to course popularity, activity, and average scores.

## Features

1. **CourseStatistics Class:**
    - Track the popularity, activity, and total points earned for each course.
    - Calculate the average score for each course.
    - Get most popular and least popular courses based on enrollment.
    - Get courses with the highest and lowest activity levels.
    - Get the hardest and easiest courses based on average student scores.

2. **StudentScore Class:**
    - Represent a student's score and completion percentage for a specific course.
    - Provides a formatted string representation of a student's score with completion percentage.

3. **InputHandler Class:**
    - Handle user input from the command-line interface.

4. **Utils Class:**
    - Contains utility methods for validating user credentials, generating random IDs, and retrieving students by ID.
    - Provides methods to format and validate input related to points and course names.

5. **Main Class:**
    - Entry point of the application.
    - Initializes the main components and runs the application loop.

6. **TrackerApp Class:**
    - Manages the core functionality of the Learning Progress Tracker.
    - Includes commands to add students, list students, add points, find students, view course statistics, notify students, and exit the application.

## How to Use

1. **Add Students:**
    - Use the "add students" command to add new students to the tracker. Follow the prompts to enter student credentials (first name, last name, and email address). Students are uniquely identified by their email addresses.

2. **List Students:**
    - Use the "list" command to view a list of all students currently tracked in the system.

3. **Add Points:**
    - Use the "add points" command to update students' points for a specific course. Enter the student ID followed by the points earned in each course (e.g., "123456 80 90 95 75").

4. **Find Students:**
    - Use the "find" command to search for a specific student by ID. Enter the student ID to view their details.

5. **View Course Statistics:**
    - Use the "statistics" command to view course statistics. You can type the name of a course to see details for that course or type "back" to quit.

6. **Notify Students:**
    - Use the "notify" command to notify students who have recently completed a course. The app will send a notification for each completed course.

7. **Exit the Application:**
    - Use the "exit" command to exit the Learning Progress Tracker application.

## Example Usage

```yaml
Learning Progress Tracker

Enter a command:
> add students
Enter student credentials or 'back' to return:
John Doe john.doe@example.com
The student has been added.
Jane Smith jane.smith@example.com
The student has been added.
back

Total 2 students have been added.

> list
Students:
123456
789012

> add points
Enter an id and points or 'back' to return:
123456 80 90 95 75
Points updated.
back

> statistics
Most popular: JAVA, DSA
Least popular: n/a
Highest activity: JAVA, DSA
Lowest activity: n/a
Easiest course: DSA
Hardest course: JAVA

Type the name of a course to see details or 'back' to quit:
JAVA
Java
id       points completed
123456    80     57.1%
789012    90     64.3%
back

> notify
To: john.doe@example.com
Re: Your Learning Progress
Hello, John Doe! You have accomplished our JAVA course!

To: jane.smith@example.com
Re: Your Learning Progress
Hello, Jane Smith! You have accomplished our JAVA course!

Total 2 students have been notified.

> exit
Bye!
```

## Notes

- This is a basic example of using the Learning Progress Tracker Java app. You can integrate this functionality into your existing project or extend it further to suit your specific requirements.

- The application utilizes the `CourseStatistics` and `StudentScore` classes to manage course-related statistics and individual student scores, respectively.

- The `InputHandler` and `Utils` classes provide functionality for handling user input and various utility methods for validation and data manipulation.

- The `Main` and `TrackerApp` classes manage the overall flow of the application and handle commands entered by the user.

## License

This Learning Progress Tracker Java App is open-source and provided under the [MIT License](LICENSE). Feel free to modify and use it as per your requirements.

Thank you for using our Learning Progress Tracker Java App! Happy tracking and learning!
