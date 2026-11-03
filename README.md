# Student Course Registration System

A console-based Java application demonstrating Object-Oriented Programming (OOP) principles to manage students, courses, enrollments, prerequisites, and academic summaries with file-based persistence.

---

## Features

- **Student Management**: Add, view, and delete students with their department and semester info.
- **Course Management**: Add courses specifying credits, capacity limits, and prerequisite courses.
- **Registration & Validation**:
  - **Prerequisite Checking**: Enforces that a student must complete prerequisite courses before registering.
  - **Credit Limit Enforcement**: Restricts total registered credits per student to a maximum of 20 credits.
  - **Capacity Management**: Prevents over-enrollment when a course reaches its maximum capacity.
  - **Duplicate Prevention**: Rejects duplicate enrollments for the same course.
- **Academic Summary**: Displays a student's enrolled courses, total registered credits, and remaining credit allowance.
- **Course Roster**: View all students enrolled in a specific course.
- **Completed Courses Tracking**: Record courses a student has previously completed.
- **Data Persistence**: Automatically loads data on startup and persists all students, courses, completed courses, and registrations to `data.txt`.

---

## OOP Concepts Demonstrated

- **Abstraction**: `Person` abstract class and `AcademicInfo` interface define essential behaviors.
- **Inheritance**: `Student` inherits common attributes and behavior from `Person`.
- **Polymorphism**: `AcademicRecord` implements the `AcademicInfo` interface.
- **Encapsulation**: Private fields accessed through public getters and validation methods.
- **Exception Handling**: Custom `RegistrationException` handles domain-specific registration errors.

---

## Project Structure

```text
StudentRegistrationSystem/
├── src/
│   ├── AcademicRecord.java       # Interface AcademicInfo & AcademicRecord implementation
│   ├── Course.java               # Course entity with credits, prereqs, capacity
│   ├── FileManager.java          # Reads and writes data to data.txt
│   ├── Main.java                 # Main CLI application entry point and interactive menu
│   ├── Person.java               # Abstract base class for people
│   ├── Registration.java         # Association between Student and Course
│   ├── RegistrationException.java# Custom checked exception for registration errors
│   ├── RegistrationSystem.java   # Core business logic and enrollment management
│   └── Student.java              # Student entity inheriting from Person
├── data.txt                      # Preloaded / persistent data store
├── .gitignore                    # Git ignore file for Java & IDE files
└── README.md                     # Project documentation
```

---

## Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher (`java -version` and `javac -version`)

---

## How to Compile and Run

### Option 1: Command Line / Terminal

1. **Clone the repository** (or navigate to the project directory):
   ```bash
   cd StudentRegiatrationSystem
   ```

2. **Compile the source files into a `bin` directory**:
   - **Windows (Command Prompt / PowerShell)**:
     ```cmd
     javac -d bin src/*.java
     ```
   - **Linux / macOS**:
     ```bash
     javac -d bin src/*.java
     ```

3. **Run the application**:
   ```bash
   java -cp bin Main
   ```

---

### Option 2: Run directly from `src/`

```bash
cd src
javac *.java
java Main
```

---

### Option 3: In an IDE (VS Code, IntelliJ IDEA, Eclipse)

1. Open or import the project folder in your IDE.
2. Ensure the Java SDK is configured.
3. Mark `src` as the Source folder if prompted.
4. Run `Main.java`.

---

## Menu Options

```text
==========================================
   STUDENT COURSE REGISTRATION SYSTEM
==========================================
1.  Add Student
2.  View Students
3.  Add Course
4.  View Courses
5.  Register Student in Course
6.  View Student Academic Summary
7.  View Students in Course
8.  Add Completed Course for Student
9.  Delete Student
10. Save Data
11. Exit
==========================================
```

---

## Data Persistence (`data.txt`)

Data is stored in CSV format with record identifiers:
- `STUDENT,<id>,<name>,<department>,<semester>`
- `COMPLETED,<student_id>,<course_code>`
- `COURSE,<code>,<name>,<credits>,<prerequisite>,<max_enrollment>`
- `REG,<student_id>,<course_code>`

The system loads existing records from `data.txt` when launched and writes back changes when choosing **Save Data (10)** or **Exit (11)**.
