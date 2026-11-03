import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegistrationSystem system = new RegistrationSystem();
        FileManager fileManager = new FileManager();
        fileManager.loadData(system);
        int choice = 0;
        do {
            System.out.println("\n==========================================");
            System.out.println("   STUDENT COURSE REGISTRATION SYSTEM");
            System.out.println("==========================================");
            System.out.println("1.  Add Student");
            System.out.println("2.  View Students");
            System.out.println("3.  Add Course");
            System.out.println("4.  View Courses");
            System.out.println("5.  Register Student in Course");
            System.out.println("6.  View Student Academic Summary");
            System.out.println("7.  View Students in Course");
            System.out.println("8.  Add Completed Course for Student");
            System.out.println("9.  Delete Student");
            System.out.println("10. Save Data");
            System.out.println("11. Exit");
            System.out.println("==========================================");
            System.out.print("Enter your choice (1-11): ");
            String input = sc.nextLine().trim();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 11.");
                continue;
            }
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        String id = sc.nextLine().trim();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine().trim();
                        System.out.print("Enter Department: ");
                        String department = sc.nextLine().trim();
                        System.out.print("Enter Semester: ");
                        String semester = sc.nextLine().trim();
                        if (id.isEmpty() || name.isEmpty()) {
                            System.out.println("Student ID and Name cannot be empty.");
                            break;
                        }
                        Student student = new Student(id, name, department, semester);
                        if (system.addStudent(student)) {
                            System.out.println("Student added successfully.");
                        } else {
                            System.out.println("Student ID already exists or storage is full.");
                        }
                        break;
                    case 2:
                        system.displayStudents();
                        break;
                    case 3:
                        System.out.print("Enter Course Code: ");
                        String code = sc.nextLine().trim().toUpperCase();
                        System.out.print("Enter Course Name: ");
                        String courseName = sc.nextLine().trim();
                        System.out.print("Enter Credits: ");
                        int credits = 0;
                        try {
                            credits = Integer.parseInt(sc.nextLine().trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid credits. Please enter a valid number.");
                            break;
                        }
                        System.out.print("Enter Prerequisite Code (or 'None'): ");
                        String prerequisite = sc.nextLine().trim();
                        if (prerequisite.isEmpty()) {
                            prerequisite = "None";
                        }
                        System.out.print("Enter Maximum Enrollment: ");
                        int capacity = 0;
                        try {
                            capacity = Integer.parseInt(sc.nextLine().trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid capacity. Please enter a valid number.");
                            break;
                        }
                        if (code.isEmpty() || courseName.isEmpty()) {
                            System.out.println("Course Code and Course Name cannot be empty.");
                            break;
                        }
                        Course course = new Course(
                                code,
                                courseName,
                                credits,
                                prerequisite,
                                capacity
                        );
                        if (system.addCourse(course)) {
                            System.out.println("Course added successfully.");
                        } else {
                            System.out.println("Course code already exists or storage is full.");
                        }
                        break;
                    case 4:
                        system.displayCourses();
                        break;
                    case 5:
                        System.out.print("Enter Student ID: ");
                        String studentId = sc.nextLine().trim();
                        System.out.print("Enter Course Code: ");
                        String courseCode = sc.nextLine().trim();
                        system.registerStudent(studentId, courseCode);
                        System.out.println("Registration successful.");
                        break;
                    case 6:
                        System.out.print("Enter Student ID: ");
                        String summaryId = sc.nextLine().trim();
                        system.displayAcademicSummary(summaryId);
                        break;
                    case 7:
                        System.out.print("Enter Course Code: ");
                        String registeredCourse = sc.nextLine().trim();
                        system.displayRegisteredStudents(registeredCourse);
                        break;
                    case 8:
                        System.out.print("Enter Student ID: ");
                        String completedStudentId = sc.nextLine().trim();
                        Student completedStudent =
                                system.findStudent(completedStudentId);
                        if (completedStudent == null) {
                            System.out.println("Student does not exist.");
                        } else {
                            System.out.print("Enter Completed Course Code: ");
                            String completedCourseCode =
                                    sc.nextLine().trim().toUpperCase();
                            if (completedCourseCode.isEmpty()) {
                                System.out.println("Course code cannot be empty.");
                            } else {
                                completedStudent.addCompletedCourse(
                                        completedCourseCode
                                );
                                System.out.println(
                                        "Completed course added successfully."
                                );
                            }
                        }
                        break;
                    case 9:
                        System.out.print("Enter Student ID to delete: ");
                        String deleteId = sc.nextLine().trim();
                        if (system.deleteStudent(deleteId)) {
                            System.out.println(
                                    "Student deleted successfully."
                            );
                        } else {
                            System.out.println(
                                    "Student does not exist."
                            );
                        }
                        break;
                    case 10:
                        fileManager.saveData(system);
                        break;
                    case 11:
                        System.out.println("Saving data before exiting...");
                        fileManager.saveData(system);
                        System.out.println("Exiting system. Goodbye!");
                        break;
                    default:
                        System.out.println(
                                "Invalid choice. Please enter a number between 1 and 11."
                        );
                }
            } catch (RegistrationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 11);
        sc.close();
    }
}