import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
public class FileManager{
    public void saveData(RegistrationSystem system) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("data.txt"));
            for (int i = 0; i < system.getStudentCount(); i++) {
                Student s = system.getStudents()[i];
                writer.println("STUDENT," + s.getStudentId() + "," + s.getName() + "," + s.getDepartment() + "," + s.getSemester());
                for (int j = 0; j < s.getCompletedCourseCount(); j++) {
                    writer.println("COMPLETED," + s.getStudentId() + "," + s.getCompletedCourses()[j]);
                }
            }
            for (int i = 0; i < system.getCourseCount(); i++) {
                Course c = system.getCourses()[i];
                String prereq = c.getPrerequisite();
                if (prereq == null || prereq.trim().isEmpty()) {
                    prereq = "None";
                }
                writer.println("COURSE," + c.getCourseCode() + "," + c.getCourseName() + "," + c.getCredits() + "," + prereq + "," + c.getMaxEnrollment());
            }
            for (int i = 0; i < system.getRegistrationCount(); i++) {
                Registration r = system.getRegistrations()[i];
                writer.println("REG," + r.getStudent().getStudentId() + "," + r.getCourse().getCourseCode());
            }
            writer.close();
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving data.");
        }
    }
    public void loadData(RegistrationSystem system) {
        try {
            File file = new File("data.txt");
            if (!file.exists()) {
                return;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts[0].equals("STUDENT") && parts.length >= 5) {
                    system.addStudent(new Student(parts[1], parts[2], parts[3], parts[4]));
                } else if (parts[0].equals("COMPLETED") && parts.length >= 3) {
                    Student s = system.findStudent(parts[1]);
                    if (s != null) {
                        s.addCompletedCourse(parts[2]);
                    }
                } else if (parts[0].equals("COURSE") && parts.length >= 6) {
                    system.addCourse(new Course(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], Integer.parseInt(parts[5])));
                } else if (parts[0].equals("REG") && parts.length >= 3) {
                    system.registerStudent(parts[1], parts[2]);
                }
            }
            scanner.close();
            System.out.println("Previous data loaded.");
        } catch (Exception e) {
            System.out.println("Error loading data.");
        }
    }
}
