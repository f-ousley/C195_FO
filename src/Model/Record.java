package Model;

public class Record {
    private String Month;
    private String Type;
    private int Number;
    private String Customer_Name;

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }


    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_name) {
        Customer_Name = customer_name;
    }

    public Record(String customer_name, int number) {
        Customer_Name = customer_name;
        Number = number;

    }

    public Record(String month, String type, int number) {
        Month = month;
        Type = type;
        Number = number;
    }
}
