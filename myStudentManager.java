import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Core manager class jo myStudentOperations interface ko implement karti hai
// Collections Framework (ArrayList) aur File I/O Streams ka use yahan kiya gaya hai
public class myStudentManager implements myStudentOperations {

    // In-memory student records store karne ke liye ArrayList collection
    private ArrayList<myStudentFile> studentRecordsList;

    // Data persistence ke liye file ka naam
    private final String persistentDataFileName = "students_data.txt";

    // Constructor: ArrayList initialize karega aur file se purana data memory me load karega
    public myStudentManager() {
        this.studentRecordsList = new ArrayList<>();
        loadRecordsFromFile(); // Program start hote hi existing records load ho jayenge
    }

    // Input validation method: Galat values par custom exception throw karega
    private void validateStudentInput(int idofUser, String nameofUser, String emailofUser, double cgpaofStudent) throws invalidStudentDataException {
        // ID check: Roll number positive hona chahiye
        if (idofUser <= 0) {
            throw new invalidStudentDataException("Student ID hamesha positive integer honi chahiye!");
        }

        // Name check: Khali nahi hona chahiye
        if (nameofUser == null || nameofUser.trim().isEmpty()) {
            throw new invalidStudentDataException("Student ka name khali nahi chhod sakte!");
        }

        // Email check: Basic check for @ aur .
        if (emailofUser == null || !emailofUser.contains("@") || !emailofUser.contains(".")) {
            throw new invalidStudentDataException("Invalid email address! Kripya proper email address dalein.");
        }

        // CGPA check: University scale 0.0 to 10.0 ke beech hona chahiye
        if (cgpaofStudent < 0.0 || cgpaofStudent > 10.0) {
            throw new invalidStudentDataException("CGPA 0.0 se 10.0 ke beech hona chahiye!");
        }
    }

    // Interface method implementation: Naya student record add karna
    @Override
    public void addNewStudent(myStudentFile newStudentObj) throws invalidStudentDataException {
        // Step 1: Input data validate karo
        validateStudentInput(newStudentObj.getIdofUser(), newStudentObj.getNameofUser(),
                newStudentObj.getEmailofUser(), newStudentObj.getCgpaofStudent());

        // Step 2: Check karo ki yeh Roll ID pehle se toh exist nahi karti
        for (myStudentFile existingRecord : studentRecordsList) {
            if (existingRecord.getIdofUser() == newStudentObj.getIdofUser()) {
                throw new invalidStudentDataException("Student ID " + newStudentObj.getIdofUser() + " pehle se system me exist karti hai!");
            }
        }

        // Step 3: Record ko ArrayList collection me append kar do
        studentRecordsList.add(newStudentObj);
        System.out.println(">> Success: Student record successfully add ho gaya!");
    }

    // Interface method implementation: Sabhi students ki list dikhana
    @Override
    public void viewAllStudents() {
        if (studentRecordsList.isEmpty()) {
            System.out.println(">> Notice: Abhi koi bhi student record system me nahi hai.");
            return;
        }

        System.out.println("\n========== KUL STUDENTS KI LIST (" + studentRecordsList.size() + ") ==========");
        for (myStudentFile currentRecord : studentRecordsList) {
            // Polymorphism: Overridden method call ho raha hai
            currentRecord.displayUserDetails();
        }
    }

    // Interface method implementation: Roll ID ke basis par search karna
    @Override
    public myStudentFile searchStudentById(int searchId) {
        for (myStudentFile currentRecord : studentRecordsList) {
            if (currentRecord.getIdofUser() == searchId) {
                return currentRecord; // Record mil gaya
            }
        }
        return null; // Record nahi mila
    }

    // Interface method implementation: Details update karna
    @Override
    public boolean updateStudentDetails(int searchId, String newName, String newEmail, String newCourse, double newCgpa) throws invalidStudentDataException {
        myStudentFile targetRecord = searchStudentById(searchId);

        if (targetRecord == null) {
            System.out.println(">> Error: ID " + searchId + " ka student nahi mila.");
            return false;
        }

        // Updated values ko bhi validate karo
        validateStudentInput(searchId, newName, newEmail, newCgpa);

        // Values update kar do
        targetRecord.setNameofUser(newName);
        targetRecord.setEmailofUser(newEmail);
        targetRecord.setCourseofStudent(newCourse);
        targetRecord.setCgpaofStudent(newCgpa);

        System.out.println(">> Success: Student details successfully update ho gayi!");
        return true;
    }

    // Interface method implementation: Record delete karna
    @Override
    public boolean removeStudentRecord(int searchId) {
        myStudentFile targetRecord = searchStudentById(searchId);

        if (targetRecord != null) {
            studentRecordsList.remove(targetRecord);
            System.out.println(">> Success: Student ID " + searchId + " ka record delete ho gaya.");
            return true;
        } else {
            System.out.println(">> Error: ID " + searchId + " ka student delete karne ke liye nahi mila.");
            return false;
        }
    }

    // Interface method implementation: BufferedWriter se file me data save karna
    @Override
    public void saveRecordsToFile() {
        BufferedWriter writerObj = null;
        try {
            // FileWriter aur BufferedWriter se file stream banaya
            writerObj = new BufferedWriter(new FileWriter(persistentDataFileName));

            for (myStudentFile recordItem : studentRecordsList) {
                writerObj.write(recordItem.formatDataForFile());
                writerObj.newLine(); // Har record new line par store hoga
            }

            System.out.println(">> File I/O: Sabhi records successfully '" + persistentDataFileName + "' me save ho gaye.");
        } catch (IOException ioExceptionObj) {
            System.out.println(">> File Error: File me data write karte waqt error: " + ioExceptionObj.getMessage());
        } finally {
            // Finally block me streams ko band karte hain taaki memory leak na ho
            try {
                if (writerObj != null) {
                    writerObj.close();
                }
            } catch (IOException closeErr) {
                System.out.println(">> Close Error: File close nahi ho saki: " + closeErr.getMessage());
            }
        }
    }

    // Interface method implementation: BufferedReader se file read karna
    @Override
    public void loadRecordsFromFile() {
        File dataFileHandle = new File(persistentDataFileName);

        // Agar file pehle se present nahi hai toh chupchaap return kar jao
        if (!dataFileHandle.exists()) {
            return;
        }

        BufferedReader readerObj = null;
        try {
            readerObj = new BufferedReader(new FileReader(dataFileHandle));
            String eachLineData;

            // List clear kar lete hain taaki duplicates na aayein
            studentRecordsList.clear();

            while ((eachLineData = readerObj.readLine()) != null) {
                if (eachLineData.trim().isEmpty()) {
                    continue; // Khali lines skip karo
                }

                // Comma se split karke har field nikal rahe hain
                String[] parsedTokens = eachLineData.split(",");
                if (parsedTokens.length == 5) {
                    int idValue = Integer.parseInt(parsedTokens[0].trim());
                    String nameValue = parsedTokens[1].trim();
                    String emailValue = parsedTokens[2].trim();
                    String courseValue = parsedTokens[3].trim();
                    double cgpaValue = Double.parseDouble(parsedTokens[4].trim());

                    // Object banakar list me append kiya
                    myStudentFile loadedStudent = new myStudentFile(idValue, nameValue, emailValue, courseValue, cgpaValue);
                    studentRecordsList.add(loadedStudent);
                }
            }
            System.out.println(">> File I/O: File se " + studentRecordsList.size() + " records successfully load ho gaye.");
        } catch (IOException | NumberFormatException loadErr) {
            System.out.println(">> Load Error: File read karne me error: " + loadErr.getMessage());
        } finally {
            try {
                if (readerObj != null) {
                    readerObj.close();
                }
            } catch (IOException closeErr) {
                System.out.println(">> Close Error: Reader close nahi ho saka: " + closeErr.getMessage());
            }
        }
    }
}
