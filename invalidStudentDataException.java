// Custom user-defined exception class jo standard Exception class ko extend karti hai
// Syllabus Coverage: Custom Exception Handling
public class invalidStudentDataException extends Exception {

    // Default constructor
    public invalidStudentDataException() {
        super("Invalid student data entered!");
    }

    // Parameterized constructor custom error message pass karne ke liye
    public invalidStudentDataException(String customErrorMessage) {
        super(customErrorMessage);
    }
}
