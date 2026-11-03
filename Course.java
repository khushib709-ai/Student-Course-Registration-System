public class Course {
    private String courseCode;
    private String courseName;
    private int credits;
    private String prerequisite;
    private int maxEnrollment;
    public Course(String courseCode, String courseName, int credits, String prerequisite, int maxEnrollment) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.prerequisite = prerequisite;
        this.maxEnrollment = maxEnrollment;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    public int getCredits() {
        return credits;
    }
    public String getPrerequisite() {
        return prerequisite;
    }
    public int getMaxEnrollment() {
        return maxEnrollment;
    }
    public void displayInfo() {
        System.out.println("Course Code   : " + courseCode);
        System.out.println("Course Name   : " + courseName);
        System.out.println("Credits       : " + credits);
        String prereq = (prerequisite == null || prerequisite.trim().isEmpty() || prerequisite.equalsIgnoreCase("none")) ? "None" : prerequisite;
        System.out.println("Prerequisite  : " + prereq);
        System.out.println("Max Enrollment: " + maxEnrollment);
    }
}
