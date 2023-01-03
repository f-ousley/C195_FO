package Model;
/** This class creates Record objects.*/
public class Record {
    private String Month;
    private String Type;
    private int Number;
    private String Customer_Name;

    /** This method is a constructor for Record.*/
    public Record(String month, String type, int number) {
        Month = month;
        Type = type;
        Number = number;
    }
    public Record(String customer_name, int number) {
        Customer_Name = customer_name;
        Number = number;

    }
    /** This method is a getter for Record month.*/
    public String getMonth() {
        return Month;
    }
    /** This method is a setter for Record month.*/
    public void setMonth(String month) {
        Month = month;
    }
    /** This method is a getter for Record type.*/
    public String getType() {
        return Type;
    }
    /** This method is a setter for Record type.*/
    public void setType(String type) {
        Type = type;
    }
    /** This method is a getter for Record number.*/
    public int getNumber() {
        return Number;
    }
    /** This method is a setter for Record number.*/
    public void setNumber(int number) {
        Number = number;
    }
    /** This method is a getter for Record customer_name.*/
    public String getCustomer_Name() {
        return Customer_Name;
    }
    /** This method is a setter for Record customer_name.*/
    public void setCustomer_Name(String customer_name) {
        Customer_Name = customer_name;
    }
}
