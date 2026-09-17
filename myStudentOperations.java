// Interface jisme saare CRUD operational methods declare kiye gaye hain
// OOPs Concept: Pure Abstraction & Interface Contract
public interface myStudentOperations {

    // Naya student record add karne ke liye method declaration
    void addNewStudent(myStudentFile newStudentObj) throws invalidStudentDataException;

    // Sabhi student records display karne ke liye
    void viewAllStudents();

    // Roll ID ke basis par student dhoondhne ke liye
    myStudentFile searchStudentById(int searchId);

    // Existing student record update karne ke liye
    boolean updateStudentDetails(int searchId, String newName, String newEmail, String newCourse, double newCgpa) throws invalidStudentDataException;

    // Student record remove/delete karne ke liye
    boolean removeStudentRecord(int searchId);

    // Persistence: Data ko file me save karne ke liye
    void saveRecordsToFile();

    // Persistence: Data ko file se load karke ArrayList me dalne ke liye
    void loadRecordsFromFile();
}
