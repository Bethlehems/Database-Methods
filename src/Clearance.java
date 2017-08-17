import javax.xml.soap.Text;
import java.util.Date;

/**
 * Created by betty on 7/21/17.
 */
public class Clearance {
    private String ClearanceID;
    private String Description;
    private String ReasonForC;
    private Date DateCleared;
    Student std = new Student();

    public Date getDateCleared() {

        return this.DateCleared;
    }

    public String getClearanceID() {

        return this.ClearanceID;
    }

    public String getDecription() {

        return this.Description;
    }

    public String getReasonForC() {

        return this.ReasonForC;
    }

    public void setDescription(String description) {

        Description = description;
    }

    public void setClearanceID(String clearanceID) {

        ClearanceID = clearanceID;
    }
}
