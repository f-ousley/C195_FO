package Model;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Appointment {
    private Customer customer;
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private Timestamp Start;
    private Timestamp End;
    private Date Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private String Customer_Id;
    private String User_Id;
    private String Contact_Id;

    public Appointment(int appointment_id, String title, String description, String location, String type, Timestamp start, Timestamp end, Date create_date, String created_by, Timestamp last_update, String last_updated_by, String customer_id, String user_id, String contact_id) {
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

    public int getAppointment_ID() {
        return Appointment_ID;
    }

    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Timestamp getStart() {
        return Start;
    }

    public void setStart(Timestamp start) {
        Start = start;
    }

    public Timestamp getEnd() {
        return End;
    }

    public void setEnd(Timestamp end) {
        End = end;
    }

    public Date getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(Date create_Date) {
        Create_Date = create_Date;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    public Timestamp getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    public String getCustomer_ID() {
        return Customer_Id;
    }

    public void setCustomer_ID(String customer_Id) {
        Customer_Id = customer_Id;
    }

    public String getUser_ID() {
        return User_Id;
    }

    public void setUser_ID(String user_Id) {
        User_Id = user_Id;
    }

    public String getContact_ID() {
        return Contact_Id;
    }

    public void setContact_ID(String contact_Id) {
        Contact_Id = contact_Id;
    }
}
