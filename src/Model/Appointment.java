package Model;
import java.sql.Timestamp;
/** This class creates Appointment objects.*/
public class Appointment {
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private Timestamp Start;
    private Timestamp End;
    private Timestamp Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private String Customer_Id;
    private String User_Id;
    private int Contact_Id;

    /** This method is a constructor for an Appointment object.
     @param appointment_id primary key in database
     @param title
     @param description
     @param location
     @param type
     @param start
     @param end
     @param create_date
     @param created_by
     @param last_update
     @param last_updated_by
     @param contact_id
     @param customer_id
     @param user_id
     */
    public Appointment(int appointment_id, String title, String description, String location, String type, Timestamp start, Timestamp end, Timestamp create_date, String created_by, Timestamp last_update, String last_updated_by, String customer_id, String user_id, int contact_id) {
        Appointment_ID = appointment_id;
        Title = title;
        Description = description;
        Location = location;
        Type = type;
        Start = start;
        End = end;
        Create_Date = create_date;
        Created_By = created_by;
        Last_Update = last_update;
        Last_Updated_By = last_updated_by;
        Customer_Id = customer_id;
        User_Id = user_id;
        Contact_Id = contact_id;
    }
    public Appointment(){}

    /** This method is a getter for appointment_id
     @return Appointment_ID.*/
    public int getAppointment_ID() {
        return Appointment_ID;
    }
    /** This method is a setter for appointment_id.*/
    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }
    /** This method is a getter for Appointment title
     @return Title.*/
    public String getTitle() {
        return Title;
    }
    /** This method is a setter for Appointment title.*/
    public void setTitle(String title) {
        Title = title;
    }
    /** This method is a getter for Appointment description
     * @return Description.*/
    public String getDescription() {
        return Description;
    }
    /** This method is a setter for Appointment description.*/
    public void setDescription(String description) {
        Description = description;
    }
    /** This method is a getter for Appointment location
     * @return Location.*/
    public String getLocation() {
        return Location;
    }
    /** This method is a setter for Appointment location.*/
    public void setLocation(String location) {
        Location = location;
    }
    /** This method is a getter for Appointment type
     * @return Type.*/
    public String getType() {
        return Type;
    }
    /** This method is a setter for Appointment type.*/
    public void setType(String type) {
        Type = type;
    }
    /** This method is a getter for Appointment start
     * @return Start.*/
    public Timestamp getStart() {
        return Start;
    }
    /** This method is a setter for Appointment start.*/
    public void setStart(Timestamp start) {
        Start = start;
    }
    /** This method is a getter for Appointment end
     * @return End.*/
    public Timestamp getEnd() {
        return End;
    }
    /** This method is a setter for Appointment end.*/
    public void setEnd(Timestamp end) {
        End = end;
    }
    /** This method is a getter for Appointment create_date
     * @return Create_Date.*/
    public Timestamp getCreate_Date() {
        return Create_Date;
    }
    /** This method is a setter for Appointment create_date.*/
    public void setCreate_Date(Timestamp create_Date) {
        Create_Date = create_Date;
    }
    /** This method is a getter for Appointment created_by
     * @return Created_By.*/
    public String getCreated_By() {
        return Created_By;
    }
    /** This method is a setter for Appointment created_by.*/
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }
    /** This method is a getter for appointment last_update
     * @return Last_Update.*/
    public Timestamp getLast_Update() {
        return Last_Update;
    }
    /** This method is a setter for Appointment last_update.*/
    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }
    /** This method is a getter for Appointment last_updated_by
     * @return Last_Updated_By.*/
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }
    /** This method is a setter for Appointment last_updated_by.*/
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }
    /** This method is a getter for Appointment customer_id
     * @return Customer_ID.*/
    public String getCustomer_ID() {
        return Customer_Id;
    }
    /** This method is a setter for Appointment customer_id.*/
    public void setCustomer_ID(String customer_Id) {
        Customer_Id = customer_Id;
    }
    /** This method is a getter for Appointment user_id
     * @return User_ID.*/
    public String getUser_ID() {
        return User_Id;
    }
    /** This method is a setter for Appointment user_id.*/
    public void setUser_ID(String user_Id) {
        User_Id = user_Id;
    }
    /** This method is a getter for Appointment contact_id
     * @return Contact_ID.*/
    public int getContact_ID() {
        return Contact_Id;
    }
    /** This method is a setter for Appointment contact_id.*/
    public void setContact_ID(int contact_Id) {
        Contact_Id = contact_Id;
    }
}
