package Model;

import java.sql.Timestamp;
/** This class creates Contact object.*/
public class Contact {
    private String Contact_Name;
    private int Appointment_ID;
    private String Title;
    private String Type;
    private String Description;
    private Timestamp Start;
    private Timestamp End;
    private int Customer_ID;
    /** This method is a constructor for Contact.
     * @param customer_id
     * @param end
     * @param start
     * @param type
     * @param description
     * @param appointment_id
     * @param contact_name
     * @param title */
    public Contact(String contact_name, int appointment_id, String title, String type, String description, Timestamp start, Timestamp end, int customer_id) {
        Contact_Name = contact_name;
        Appointment_ID = appointment_id;
        Title = title;
        Type = type;
        Description = description;
        Start = start;
        End = end;
        Customer_ID = customer_id;
    }
    /** This method is a getter for Contact contact_name
     * @return Contact_Name.*/
    public String getContact_Name() {
        return Contact_Name;
    }
    /** This method is a setter for Contact contact_name.*/
    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }
    /** This method is a getter for Contact appointment_id
     * @return Appointment_ID.*/
    public int getAppointment_ID() {
        return Appointment_ID;
    }
    /** This method is a setter for Contact appointment_id.*/
    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }
    /** This method is a getter for Contact title
     * @return Title.*/
    public String getTitle() {
        return Title;
    }
    /** This method is a setter for Contact title.*/
    public void setTitle(String title) {
        Title = title;
    }
    /** This method is a getter for Contact type
     * @return Type.*/
    public String getType() {
        return Type;
    }
    /** This method is a setter for Contact type.*/
    public void setType(String type) {
        Type = type;
    }
    /** This method is a getter for Contact description
     * @return Description.*/
    public String getDescription() {
        return Description;
    }
    /** This method is a getter for Contact description.*/
    public void setDescription(String description) {
        Description = description;
    }
    /** This method is a getter for Contact start
     * @return Start.*/
    public Timestamp getStart() {
        return Start;
    }
    /** This method is a setter for Contact start.*/
    public void setStart(Timestamp start) {
        Start = start;
    }
    /** This method is a getter for Contact end
     * @return End.*/
    public Timestamp getEnd() {
        return End;
    }
    /** This method is a setter for Contact end.*/
    public void setEnd(Timestamp end) {
        End = end;
    }
    /** This method is a getter for Contact customer_id
     * @return Customer_ID.*/
    public int getCustomer_ID() {
        return Customer_ID;
    }
    /** This method is a setter for Contact customer_id.*/
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }


}
