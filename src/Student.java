import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.Scanner;

/**
 * Created by betty on 7/21/17.
 */
public class Student {
    private String StudID = "";
    private String FullName;
    private String Dep;
    private Year ClassYear;
    Scanner scn = new Scanner(System.in);

    public String getDep() {

        return this.Dep;
    }

    public String getFullName() {

        return this.FullName;
    }

    public String getStudID() {
        return this.StudID;
    }

    public Year getClassYear() {

        return this.ClassYear;
    }

    public void setStudID(String studID) {

        StudID = studID;
    }

    public void getClearanceInfo() throws Exception {
        User usr = new User();
        try {
            Clearance clr = new Clearance();
            String SQL1 = "SELECT * FROM Clearance";
            ResultSet rs2 = usr.connection().executeQuery(SQL1);
            System.out.println("Enter your ID:");
            boolean found = false;
            while (rs2.next()) {
                if (rs2.getString("Stud_ID").compareTo(getStudID()) == 0) {
                    System.out.println(getFullName());
                    System.out.println(getStudID());
                    System.out.println(clr.getDecription());
                    found = true;
                    break;
                }
            }
            if (found == false) {
                System.out.println("Student not found.");
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());


        }
    }

    public static void main(String[] args) throws Exception {
        Student std = new Student();
        std.getClearanceInfo();
    }
}
