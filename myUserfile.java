// Abstract base class banai gayi hai jisme basic user ki information rahegi
// OOPs Concept: Abstraction aur Encapsulation
public abstract class myUserfile {

    // Protected variables rakhe hain taaki child class myStudentFile directly inherit kar sake
    protected int idofUser;
    protected String nameofUser;
    protected String emailofUser;

    // Default constructor
    public myUserfile() {
        this.idofUser = 0;
        this.nameofUser = "";
        this.emailofUser = "";
    }

    // Parameterized constructor values initialize karne ke liye
    public myUserfile(int idofUser, String nameofUser, String emailofUser) {
        this.idofUser = idofUser;
        this.nameofUser = nameofUser;
        this.emailofUser = emailofUser;
    }

    // Getters aur Setters methods encapsulation maintain karne ke liye
    public int getIdofUser() {
        return idofUser;
    }

    public void setIdofUser(int idofUser) {
        this.idofUser = idofUser;
    }

    public String getNameofUser() {
        return nameofUser;
    }

    public void setNameofUser(String nameofUser) {
        this.nameofUser = nameofUser;
    }

    public String getEmailofUser() {
        return emailofUser;
    }

    public void setEmailofUser(String emailofUser) {
        this.emailofUser = emailofUser;
    }

    // Abstract method banaya hai jisko har child class apne hisab se implement karegi (Polymorphism)
    public abstract void displayUserDetails();
}
