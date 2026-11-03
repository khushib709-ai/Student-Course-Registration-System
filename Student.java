public class Student extends Person {
    private String studentId;
    private String department;
    private String semester;
    private String[] completedCourses;
    private int completedCourseCount;
    public Student(String studentId, String name, String department, String semester) {
        super(name);
        this.studentId = studentId;
        this.department = department;
        this.semester = semester;
        this.completedCourses = new String[20];
        this.completedCourseCount = 0;
    }
    public String getStudentId() {
        return studentId;
    }
    public String getDepartment() {
        return department;
    }
    public String getSemester() {
        return semester;
    }
    public String[] getCompletedCourses() {
        return completedCourses;
    }
    public int getCompletedCourseCount() {
        return completedCourseCount;
    }
    public void addCompletedCourse(String courseCode) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            return;
        }
        if (completedCourseCount >= completedCourses.length) {
            return;
        }
        courseCode = courseCode.trim().toUpperCase();
        if (!hasCompleted(courseCode)) {
            completedCourses[completedCourseCount] = courseCode;
            completedCourseCount++;
        }
    }
    public boolean hasCompleted(String courseCode) {
        if (courseCode == null) {
            return false;
        }
        for (int i = 0; i < completedCourseCount; i++) {
            if (completedCourses[i] != null && completedCourses[i].equalsIgnoreCase(courseCode.trim())) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void displayInfo() {
        System.out.println("Student ID  : " + studentId);
        System.out.println("Name        : " + getName());
        System.out.println("Department  : " + department);
        System.out.println("Semester    : " + semester);
        if (completedCourseCount > 0) {
            System.out.print("Completed   : ");
            for (int i = 0; i < completedCourseCount; i++) {
                System.out.print(completedCourses[i]);
                if (i < completedCourseCount - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
