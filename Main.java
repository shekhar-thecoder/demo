import java.util.Scanner;

// Main interactive CLI Class jisme complete menu-driven execution flow banaya gaya hai
public class Main {

    public static void main(String[] args) {
        // Scanner object console se input lene ke liye
        Scanner scInput = new Scanner(System.in);

        // Core operational manager class ka object banaya
        myStudentManager managerObj = new myStudentManager();

        // While loop control karne ke liye boolean flag
        boolean runningStatus = true;

        System.out.println("==================================================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM (CSE2006)         ");
        System.out.println("==================================================");

        while (runningStatus) {
            // CLI Console Menu Display
            System.out.println("\n---------------- MAIN MENU ----------------");
            System.out.println("1. Naya Student Record Add Karein");
            System.out.println("2. Sabhi Students Ki List Dekhein");
            System.out.println("3. Student Search Karein (Roll ID Se)");
            System.out.println("4. Student Ki Details Update Karein");
            System.out.println("5. Student Record Remove/Delete Karein");
            System.out.println("6. Records Ko File Me Save Karein");
            System.out.println("7. Program Exit Karein");
            System.out.print("Apna Choice Number Dalein (1-7): ");

            int myUserSelection = -1;
            try {
                // User input ko read karke integer me convert kar rahe hain
                myUserSelection = Integer.parseInt(scInput.nextLine().trim());
            } catch (NumberFormatException numberFormatErr) {
                System.out.println(">> Invalid Input: Kripya valid number hi enter karein!");
                continue;
            }

            switch (myUserSelection) {
                case 1:
                    // Naya student record add karne ka block
                    try {
                        System.out.println("\n--- Naya Student Form ---");
                        System.out.print("Student Roll ID (Integer) enter karein: ");
                        int inputId = Integer.parseInt(scInput.nextLine().trim());

                        System.out.print("Student Name enter karein: ");
                        String inputName = scInput.nextLine().trim();

                        System.out.print("Student Email enter karein: ");
                        String inputEmail = scInput.nextLine().trim();

                        System.out.print("Student Course (e.g. B.Tech CSE) enter karein: ");
                        String inputCourse = scInput.nextLine().trim();

                        System.out.print("Student CGPA (0.0 to 10.0) enter karein: ");
                        double inputCgpa = Double.parseDouble(scInput.nextLine().trim());

                        // Student object instantiate kiya parameterized constructor se
                        myStudentFile newRecordToInsert = new myStudentFile(inputId, inputName, inputEmail, inputCourse, inputCgpa);
                        
                        // Manager class ko call kiya record add karne ke liye
                        managerObj.addNewStudent(newRecordToInsert);

                    } catch (NumberFormatException formatErr) {
                        System.out.println(">> Error: ID aur CGPA me numeric value enter karni hogi!");
                    } catch (invalidStudentDataException customErr) {
                        // User-defined custom exception catch ho raha hai yahan
                        System.out.println(">> Validation Failed: " + customErr.getMessage());
                    }
                    break;

                case 2:
                    // Sabhi student records display karne ka block
                    managerObj.viewAllStudents();
                    break;

                case 3:
                    // Student search karne ka block
                    try {
                        System.out.print("\nSearch karne ke liye Student Roll ID enter karein: ");
                        int searchRollId = Integer.parseInt(scInput.nextLine().trim());

                        myStudentFile foundStudent = managerObj.searchStudentById(searchRollId);
                        if (foundStudent != null) {
                            System.out.println(">> Record Mil Gaya:");
                            foundStudent.displayUserDetails(); // Polymorphic display method call
                        } else {
                            System.out.println(">> Notice: ID " + searchRollId + " ka koi bhi student nahi mila!");
                        }
                    } catch (NumberFormatException numErr) {
                        System.out.println(">> Error: Kripya valid integer ID enter karein!");
                    }
                    break;

                case 4:
                    // Student details update karne ka block
                    try {
                        System.out.print("\nUpdate karne ke liye Student Roll ID enter karein: ");
                        int updateTargetId = Integer.parseInt(scInput.nextLine().trim());

                        myStudentFile checkExists = managerObj.searchStudentById(updateTargetId);
                        if (checkExists == null) {
                            System.out.println(">> Error: ID " + updateTargetId + " ka student record exist nahi karta!");
                            break;
                        }

                        System.out.println(">> Student mil gaya. Ab updated details enter karein:");
                        System.out.print("New Name enter karein: ");
                        String updatedName = scInput.nextLine().trim();

                        System.out.print("New Email enter karein: ");
                        String updatedEmail = scInput.nextLine().trim();

                        System.out.print("New Course enter karein: ");
                        String updatedCourse = scInput.nextLine().trim();

                        System.out.print("New CGPA enter karein: ");
                        double updatedCgpa = Double.parseDouble(scInput.nextLine().trim());

                        managerObj.updateStudentDetails(updateTargetId, updatedName, updatedEmail, updatedCourse, updatedCgpa);

                    } catch (NumberFormatException numErr) {
                        System.out.println(">> Error: Numeric field me galat value enter hui hai!");
                    } catch (invalidStudentDataException customErr) {
                        System.out.println(">> Validation Failed: " + customErr.getMessage());
                    }
                    break;

                case 5:
                    // Student record remove/delete karne ka block
                    try {
                        System.out.print("\nDelete karne ke liye Student Roll ID enter karein: ");
                        int deleteTargetId = Integer.parseInt(scInput.nextLine().trim());

                        managerObj.removeStudentRecord(deleteTargetId);
                    } catch (NumberFormatException numErr) {
                        System.out.println(">> Error: Kripya valid integer ID hi enter karein!");
                    }
                    break;

                case 6:
                    // File me data write karne ka block
                    managerObj.saveRecordsToFile();
                    break;

                case 7:
                    // Program exit karne ka block
                    System.out.println("\n>> Program exit karne se pehle auto-saving student records...");
                    managerObj.saveRecordsToFile();
                    System.out.println(">> Dhanyawad! Student Management System successfully band ho gaya.");
                    runningStatus = false;
                    break;

                default:
                    System.out.println(">> Invalid Choice: Kripya 1 se 7 tak ka valid number select karein.");
                    break;
            }
        }

        // Scanner close kiya resource cleanup ke liye
        scInput.close();
    }
}
