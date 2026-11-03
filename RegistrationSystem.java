public class RegistrationSystem {
    private Student[] students;
    private Course[] courses;
    private Registration[] registrations;
    private int studentCount;
    private int courseCount;
    private int registrationCount;
    public RegistrationSystem() {
        students = new Student[100];
        courses = new Course[100];
        registrations = new Registration[500];
        studentCount = 0;
        courseCount = 0;
        registrationCount = 0;
    }
    public boolean addStudent(Student student) {
        if (student == null || studentCount >= students.length) {
            return false;
        }
        if (findStudent(student.getStudentId()) != null) {
            return false;
        }
        students[studentCount] = student;
        studentCount++;
        return true;
    }
    public boolean addCourse(Course course) {
        if (course == null || courseCount >= courses.length) {
            return false;
        }
        if (findCourse(course.getCourseCode()) != null) {
            return false;
        }
        courses[courseCount] = course;
        courseCount++;
        return true;
    }
    public Student findStudent(String studentId) {
        if (studentId == null) {
            return null;
        }
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentId().equalsIgnoreCase(studentId.trim())) {
                return students[i];
            }
        }
        return null;
    }
    public Course findCourse(String courseCode) {
        if (courseCode == null) {
            return null;
        }
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCourseCode().equalsIgnoreCase(courseCode.trim())) {
                return courses[i];
            }
        }
        return null;
    }
    public int getEnrolledCount(String courseCode) {
        int count = 0;
        for (int i = 0; i < registrationCount; i++) {
            if (registrations[i].getCourse().getCourseCode().equalsIgnoreCase(courseCode.trim())) {
                count++;
            }
        }
        return count;
    }
    public void displayStudents() {
        if (studentCount == 0) {
            System.out.println("No students found. Please add a student first.");
            return;
        }
        System.out.println("================ LIST OF STUDENTS ================");
        for (int i = 0; i < studentCount; i++) {
            students[i].displayInfo();
            if (i < studentCount - 1) {
                System.out.println("--------------------------------------------------");
            }
        }
        System.out.println("==================================================");
    }
    public void displayCourses() {
        if (courseCount == 0) {
            System.out.println("No courses found. Please add a course first.");
            return;
        }
        System.out.println("================= LIST OF COURSES ================");
        for (int i = 0; i < courseCount; i++) {
            courses[i].displayInfo();
            int enrolled = getEnrolledCount(courses[i].getCourseCode());
            System.out.println("Enrolled      : " + enrolled + " / " + courses[i].getMaxEnrollment());
            if (i < courseCount - 1) {
                System.out.println("--------------------------------------------------");
            }
        }
        System.out.println("==================================================");
    }
    public void registerStudent(String studentId, String courseCode) throws RegistrationException {
        Student student = findStudent(studentId);
        Course course = findCourse(courseCode);
        if (student == null) {
            throw new RegistrationException("Student does not exist.");
        }
        if (course == null) {
            throw new RegistrationException("Course does not exist.");
        }
        for (int i = 0; i < registrationCount; i++) {
            if (registrations[i].getStudent().getStudentId().equalsIgnoreCase(student.getStudentId()) &&
                registrations[i].getCourse().getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
                throw new RegistrationException("Student is already registered for this course.");
            }
        }
        String prerequisite = course.getPrerequisite();
        if (prerequisite != null &&
            !prerequisite.trim().isEmpty() &&
            !prerequisite.equalsIgnoreCase("none") &&
            !prerequisite.equalsIgnoreCase("null") &&
            !prerequisite.equals("-")) {
            if (!student.hasCompleted(prerequisite)) {
                throw new RegistrationException("Prerequisite course (" + prerequisite + ") is not completed.");
            }
        }
        int totalCredits = getTotalCredits(studentId);
        if (totalCredits + course.getCredits() > 20) {
            throw new RegistrationException("Registration rejected. Credit limit is 20 (Current: " + totalCredits + ", Adding: " + course.getCredits() + ").");
        }
        int enrolled = getEnrolledCount(course.getCourseCode());
        if (enrolled >= course.getMaxEnrollment()) {
            throw new RegistrationException("Registration rejected. Course is full.");
        }
        if (registrationCount >= registrations.length) {
            throw new RegistrationException("Registration storage is full.");
        }
        registrations[registrationCount] = new Registration(student, course);
        registrationCount++;
    }
    public void displayStudentCourses(String studentId) throws RegistrationException {
        Student student = findStudent(studentId);
        if (student == null) {
            throw new RegistrationException("Student does not exist.");
        }
        boolean found = false;
        System.out.println("Registered Courses for " + student.getName() + ":");
        for (int i = 0; i < registrationCount; i++) {
            if (registrations[i].getStudent().getStudentId().equalsIgnoreCase(studentId)) {
                Course course = registrations[i].getCourse();
                System.out.println("- " + course.getCourseCode() + " : " + course.getCourseName() + " (" + course.getCredits() + " credits)");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No courses registered.");
        }
    }
    public int getTotalCredits(String studentId) {
        int totalCredits = 0;
        for (int i = 0; i < registrationCount; i++) {
            if (registrations[i].getStudent().getStudentId().equalsIgnoreCase(studentId)) {
                totalCredits += registrations[i].getCourse().getCredits();
            }
        }
        return totalCredits;
    }
    public void displayAcademicSummary(String studentId) throws RegistrationException {
        Student student = findStudent(studentId);
        if (student == null) {
            throw new RegistrationException("Student does not exist.");
        }
        AcademicRecord record = new AcademicRecord(student);
        record.displayAcademicInfo();
        System.out.println("\nRegistered Courses:");
        int totalCredits = 0;
        boolean found = false;
        for (int i = 0; i < registrationCount; i++) {
            if (registrations[i].getStudent().getStudentId().equalsIgnoreCase(studentId)) {
                Course course = registrations[i].getCourse();
                System.out.println("- " + course.getCourseCode() + " : " + course.getCourseName() + " (" + course.getCredits() + " credits)");
                totalCredits += course.getCredits();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No courses registered.");
        }
        System.out.println("------------------------------------------");
        System.out.println("Total Registered Credits   : " + totalCredits);
        System.out.println("Remaining Credit Allowance : " + (20 - totalCredits));
        System.out.println("==========================================");
    }
    public void displayRegisteredStudents(String courseCode) throws RegistrationException {
        Course course = findCourse(courseCode);
        if (course == null) {
            throw new RegistrationException("Course does not exist.");
        }
        boolean found = false;
        System.out.println("Students registered in " + course.getCourseCode() + " (" + course.getCourseName() + "):");
        for (int i = 0; i < registrationCount; i++) {
            if (registrations[i].getCourse().getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
                Student student = registrations[i].getStudent();
                System.out.println("- " + student.getStudentId() + " : " + student.getName() + " (" + student.getDepartment() + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students registered for this course yet.");
        }
    }
    public Student[] getStudents() {
        return students;
    }
    public Course[] getCourses() {
        return courses;
    }
    public Registration[] getRegistrations() {
        return registrations;
    }
    public int getStudentCount() {
        return studentCount;
    }
    public int getCourseCount() {
        return courseCount;
    }
    public int getRegistrationCount() {
        return registrationCount;
    }
    public boolean deleteStudent(String studentId) {
    int index = -1;
    for (int i = 0; i < studentCount; i++) {
        if (students[i].getStudentId()
                .equalsIgnoreCase(studentId.trim())) {
            index = i;
            break;
        }
    }
    if (index == -1) {
        return false;
    }
    for (int i = 0; i < registrationCount; i++) {
        if (registrations[i].getStudent()
                .getStudentId()
                .equalsIgnoreCase(studentId.trim())) {
            for (int j = i; j < registrationCount - 1; j++) {
                registrations[j] = registrations[j + 1];
            }
            registrations[registrationCount - 1] = null;
            registrationCount--;
            i--;
        }
    }
    for (int i = index; i < studentCount - 1; i++) {
        students[i] = students[i + 1];
    }
    students[studentCount - 1] = null;
    studentCount--;
    return true;}
}