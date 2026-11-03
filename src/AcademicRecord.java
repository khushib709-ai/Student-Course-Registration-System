interface AcademicInfo {
    void displayAcademicInfo();
}
public class AcademicRecord implements AcademicInfo {
    private Student student;
    public AcademicRecord(Student student) {
        this.student = student;
    }
    public Student getStudent() {
        return student;
    }
    @Override
    public void displayAcademicInfo() {
        System.out.println("==========================================");
        System.out.println("             ACADEMIC RECORD              ");
        System.out.println("==========================================");
        System.out.println("Student ID  : " + student.getStudentId());
        System.out.println("Name        : " + student.getName());
        System.out.println("Department  : " + student.getDepartment());
        System.out.println("Semester    : " + student.getSemester());
    }
}
