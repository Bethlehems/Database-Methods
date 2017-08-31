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

    public void  setDep(String depar) {

        Dep=depar;
    }

    public void setStudID(String studID) {

        StudID = studID;
    }
    public void setStudFullName(String name) {

        FullName = name;
    }

    public String getClearanceInfo() throws Exception {
        Scanner scn = new Scanner(System.in);
        User usr = new User();
        String data[]=new String[3];
        int count=0;
        try {
            String SQL2 = " SELECT Student.Student_ID, Student.Full_Name, Student.Department FROM Student"; //INNER JOIN Clearance ON Student.Student_ID = Clearance.Stud_ID
            ResultSet rs3 = usr.connection1().executeQuery(SQL2);


            boolean found = false;
            while (rs3.next()) {

                    setStudFullName(rs3.getString("Full_Name"));

                setStudID(rs3.getString("Student_ID"));

                setDep(rs3.getString("Department"));


            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());

}
        return getFullName()+getDep()+getStudID();
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
