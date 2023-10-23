import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class Course {
    private String code;
    private String name;

    public Course(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

public class AttendanceManagementSystem {
    private Map<String, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();
    private Map<String, Map<String, Boolean>> attendance = new HashMap<>();

    public void addStudent(String id, String name) {
        students.put(id, new Student(id, name));
    }

    public void addCourse(String code, String name) {
        courses.put(code, new Course(code, name));
    }

    public void markAttendance(String studentId, String courseCode, boolean isPresent) {
        if (students.containsKey(studentId) && courses.containsKey(courseCode)) {
            if (!attendance.containsKey(courseCode)) {
                attendance.put(courseCode, new HashMap<>());
            }
            attendance.get(courseCode).put(studentId, isPresent);
        } else {
            System.out.println("Invalid student or course ID.");
        }
    }

    public void generateAttendanceReport(String courseCode) {
        if (courses.containsKey(courseCode)) {
            Course course = courses.get(courseCode);
            Map<String, Boolean> attendanceRecord = attendance.get(courseCode);

            System.out.println("Attendance Report for " + course.getName() + " (" + course.getCode() + "):");
            for (Map.Entry<String, Boolean> entry : attendanceRecord.entrySet()) {
                String studentId = entry.getKey();
                Student student = students.get(studentId);
                String status = entry.getValue() ? "Present" : "Absent";
                System.out.println(student.getName() + " (" + studentId + "): " + status);
            }
        } else {
            System.out.println("Invalid course code.");
        }
    }

    public static void main(String[] args) {
        AttendanceManagementSystem ams = new AttendanceManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Attendance Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Mark Attendance");
            System.out.println("4. Generate Attendance Report");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.next();
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.next();
                    ams.addStudent(studentId, studentName);
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.next();
                    System.out.print("Enter Course Name: ");
                    String courseName = scanner.next();
                    ams.addCourse(courseCode, courseName);
                    System.out.println("Course added successfully.");
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.next();
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.next();
                    System.out.print("Is the student present (true/false)? ");
                    boolean isPresent = scanner.nextBoolean();
                    ams.markAttendance(studentId, courseCode, isPresent);
                    break;
                case 4:
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.next();
                    ams.generateAttendanceReport(courseCode);
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
