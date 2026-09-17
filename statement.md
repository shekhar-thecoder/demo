# Problem Statement: Student Management System
Course Code: CSE2006
course name : Programming in Java  
Academic Component: Flipped Course project  
Project Architecture:Core Java (OOPs, Collections, Custom Exception Handling, File Input/output Streams)

--------------------------------------------------------------

# 1. Problem Statement
In professional and educational institutes including university, schools and academic labs, management of records heavily
depends on physical papers and unvalidated spreadsheets entries. In schools and university there are hundreds and
thousands of students, which required to be stored and validated. Instead of traditional appraoch which produces pain points like :

- Data Redundancy and Duplication: Manual entries can leadd to conflicting entries and duplicate roll numbers for same student.
- Accidental Record Loss: While keeping phycial records there can be a subtle chance for physical damage which 
includes wear, tear and other uncontrolable features without backup copies.
- Absence of Real-Time Validation: Manual entries can be filled wrong with proper utilzation of acceptibility in details like cgpa exceeding 10 aur wrongly being filled below 0.
- Inefficient Retrieval and Updates: Finding a student's record among hundreds of handwritten pages requires sequential manual checkinh, which result in updating aur changing details of one a tedius process.

To eliminate these problems one can refer to a lightweight and structured console system which can be reliable for
handling student data in memory while persisting changes to disk.

---------------------------------------------------------------

# 2. Scope of the Project

The one of many goal of this project is to develop a modular, Cli Student Management System in Java which acts as a local database.

## In-Scope:
1. Apply Object-Oriented Design: Decompose the application into modular components ensuring Abstraction, Inheritance, Encapsulation, Polymorphism.
2. Dynamic In-Memory Management: Replacing static array boundaries with the ('ArrayList')Java Collections Framework 
to ensure dynamic record insertion and deletion at runtime.
3. Defensive Programming using Custom Exceptions: Implement a user-defined exception (`invalidStudentDataException`) to reject invalid, corrupt, or duplicate inputs before they reach the database.
4. File Persistence: Ensure all student data persists between application restarts using file streams (`BufferedReader` and `BufferedWriter`) reading from and writing to `students_data.txt`.

### Out-of-Scope (Future Enhancements):
- Multi-user authentication, password encryption, or role-based login access.
- Integration with cloud database integration like MySQl or MongoDB.
- To provide Graphical User Interface (GUI) using Swing or JavaFX.

--------------------------------------------------------------

# 3. Target Users
This Student Management System is built for the following academically involved users:
1. Academic Lab Instructors & Faculty Advisors:
   - quick accessiblity for student enrollments for particular lab slots.
   - Require fast lookups by student Roll ID (`searchStudentById`) to verify course eligibility of a student and CGPA at current time.
2. Department Academic Administrators & Course Coordinators:
   - Responsible for batch record operations: storing , updating various details of discontiued students and continuing students.
   - Require data persistence (`students_data.txt`) so records remain safe between sessions.
3. Engineering Students & Academic Evaluators (CSE2006):*
   - Faculty evaluators reviewing practical implementation of Core Java concepts (OOPs, Collections, Exception Handling, File Input/ output Streams).
   - Fellow developers who are motivated to study clean modular Java architecture and defensive programming patterns.

# 4. High-Level Features

The application provides a complete CRUD workflow using a click user interface application:
| Feature | Method / Component | Description |
| :--- | :--- | :--- |
| 1. Student Onboarding | `addNewStudent(myStudentFile newStudentObj)` | Collects `idofUser`, `nameofUser`, `emailofUser`, `courseofStudent`, and `cgpaofStudent`. Validates inputs against domain rules, blocks duplicate roll IDs, and appends the valid record to `studentRecordsList`. |
| 2. Dynamic Registry Display (View All) | `viewAllStudents()` | Traverses `studentRecordsList` and displays all registered student using polymorphic method calls. |
| 3. Targeted Record Query (Search by ID) | `searchStudentById(int searchId)` | Performs an in-memory scan for a specific `idofUser`. If matched, prints the student profile; otherwise, a "Record Not Found" notice. |
| 4. In-Place Record Modification | `updateStudentDetails(...)` | Locates an existing record by `searchId` and updates its `nameofUser`, `emailofUser`, `courseofStudent`, and `cgpaofStudent` after doing validation. |
| 5. Obsolete Record Removal (Delete) | `removeStudentRecord(int searchId)` | Searches for the given `searchId` and removes the student object from `studentRecordsList`. |
| 6. Input Shielding & Exception Handling | `invalidStudentDataException` & `Main.java` | Protects the computer system from invalid inputs (e.g., negative IDs, letters in numeric prompts, out-of-bound CGPAs $0.0 - 10.0$) using `try-catch` statement blocks. |