// Subclass jo myUserfile ko extend kar rahi hai
// OOPs Concept: Inheritance aur Polymorphism
public class myStudentFile extends myUserfile {

    // Student specific properties encapsulation ke sath
    private String courseofStudent;
    private double cgpaofStudent;

    // Default constructor
    public myStudentFile() {
        super();
        this.courseofStudent = "";
        this.cgpaofStudent = 0.0;
    }

    // Parameterized constructor jo super class ke constructor ko bhi call karega
    public myStudentFile(int idofUser, String nameofUser, String emailofUser, String courseofStudent, double cgpaofStudent) {
        super(idofUser, nameofUser, emailofUser); // Parent class ka constructor invoke hua
        this.courseofStudent = courseofStudent;
        this.cgpaofStudent = cgpaofStudent;
    }

    // Getters aur Setters student specific fields ke liye
    public String getCourseofStudent() {
        return courseofStudent;
    }

    public void setCourseofStudent(String courseofStudent) {
        this.courseofStudent = courseofStudent;
    }

    public double getCgpaofStudent() {
        return cgpaofStudent;
    }

    public void setCgpaofStudent(double cgpaofStudent) {
        this.cgpaofStudent = cgpaofStudent;
    }

    // Abstract method ko yahan override kiya gaya hai (Dynamic Polymorphism)
    @Override
    public void displayUserDetails() {
        System.out.println("--------------------------------------------------");
        System.out.println("Student Roll ID : " + idofUser);
        System.out.println("Student Name    : " + nameofUser);
        System.out.println("Student Email   : " + emailofUser);
        System.out.println("Course Enrolled : " + courseofStudent);
        System.out.println("Current CGPA    : " + cgpaofStudent);
        System.out.println("--------------------------------------------------");
    }

    // File me save karne ke liye comma separated format banane ka method
    public String formatDataForFile() {
        return idofUser + "," + nameofUser + "," + emailofUser + "," + courseofStudent + "," + cgpaofStudent;
    }
}
