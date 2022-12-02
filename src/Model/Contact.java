package Model;

import java.sql.Timestamp;

public class Contact {
    private String Contact_Name;
    private int Appointment_ID;
    private String Title;
    private String Type;
    private String Description;
    private Timestamp Start;
    private Timestamp End;
    private int Customer_ID;

    public String getContact_Name() {
        return Contact_Name;
    }

    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

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
}
