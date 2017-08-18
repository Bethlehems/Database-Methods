import java.sql.*;
import java.util.Scanner;

/**
 * Created by betty on 7/21/17.
 */
public class User {
    private String Password;
    private String UserName;
    private String UserID;
    private String PhoneNum;
    private Student stdt = new Student();
    private Clearance clr = new Clearance();

    public static Statement connection1() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/SampleDb", "root", "made2begr8");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            return stmt;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return null;
    }


    public void login() {
        Scanner inp = new Scanner(System.in);
        String userName = "Admin";
        String passWord = "12345";
        System.out.println("\n\tLOG IN TO YOUR ACCOUNT.\n\n");
        boolean stop = false;
        while (!stop) {
            System.out.print("USER NAME:");
            UserName = inp.next();
            System.out.print("\nPASSWORD:");
            Password = inp.next();
            System.out.println();
            if (UserName.equalsIgnoreCase(userName) && Password.equals(passWord)) {
                System.out.println("\nYOU ARE LOGGED IN.\n");
                stop = true;
                break;
            } else {
                System.out
                        .println("\nINVALID USER NAME OR PASSWORD. TRY AGAIN.\n\n");
                continue;
            }


        }

    }

    public void ProvideClcInfo() {//this also workd for updating the clearance information
        Scanner input = new Scanner(System.in);
        System.out.println("STUDENT ID:");
        stdt.setStudID(input.nextLine());
        System.out.println("Description");
        clr.setDescription(input.nextLine());

        Statement stm2 = connection1();

        try {
            String SQL = "SELECT * FROM Clearance";
            ResultSet rs = stm2.executeQuery(SQL);
            boolean found = false;
            while (rs.next()) {
                if (rs.getString("Stud_ID").compareTo(stdt.getStudID()) == 0) {
                    rs.updateString("Description", clr.getDecription());
                    rs.updateRow();
                    System.out.println("Submitted.");
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

    public void UpdateStudInfo() {

    }


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        User usr = new User();
        usr.login();
        usr.ProvideClcInfo();
    }
}
