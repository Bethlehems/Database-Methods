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
        Scanner scn = new Scanner(System.in);
        User usr = new User();
        try {
            String SQL2 = " SELECT Student.Student_ID, Student.Full_Name, Clearance.Description FROM Student INNER JOIN Clearance ON Student.Student_ID = Clearance.Stud_ID";
            ResultSet rs3 = usr.connection1().executeQuery(SQL2);
            System.out.println("Enter your ID:");
            String inp = scn.nextLine();

            boolean found = false;
            while (rs3.next()) {
                if (rs3.getString("Student_ID").compareTo(inp) == 0) {

                    System.out.println(rs3.getString("Full_Name"));
                    System.out.println(rs3.getString("Student_ID"));
                    System.out.println(rs3.getString("Description"));
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Student std = new Student();
        std.getClearanceInfo();
    }
}
