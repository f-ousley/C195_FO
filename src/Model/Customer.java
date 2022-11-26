package Model;

import java.sql.Timestamp;
import java.util.Date;

public class Customer {
    private int customer_ID;
    private String customer_Name;
    private String address;
    private String postal_Code;
    private String phone;
    private Timestamp create_Date;
    private String created_By;
    private Timestamp last_Update;
    private String last_updated_By;
    private int division_ID;
    private String country;
    private String division;
    private int country_ID;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getCountry_ID() {
        return country_ID;
    }

    public void setCountry_ID(int country_ID) {
        this.country_ID = country_ID;
    }

    public Customer(int customer_id, String customer_name, String address, String postal_code, String phone, Timestamp create_date, String created_by, Timestamp last_update, String last_updated_by, int division_id, String country, String division, int country_id) {
        this.customer_ID = customer_id;
        this.customer_Name = customer_name;
        this.address = address;
        this.postal_Code = postal_code;
        this.phone = phone;
        this.create_Date = create_date;
        this.created_By = created_by;
        this.last_Update = last_update;
        this.last_updated_By = last_updated_by;
        this.division_ID = division_id;
        this.country = country;
        this.division = division;
        this.country_ID = country_id;
    }
    public Customer(){};


    public int getCustomer_ID() { return customer_ID; }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal_Code() {
        return postal_Code;
    }

    public void setPostal_Code(String postal_Code) {
        this.postal_Code = postal_Code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getCreate_Date() {
        return create_Date;
    }

    public void setCreate_Date(Timestamp create_Date) {
        this.create_Date = create_Date;
    }

    public String getCreated_By() {
        return created_By;
    }

    public void setCreated_By(String created_By) {
        this.created_By = created_By;
    }

    public Date getLast_Update() {
        return last_Update;
    }

    public void setLast_Update(Timestamp last_Update) {
        this.last_Update = last_Update;
    }

    public String getLast_Updated_By() {
        return last_updated_By;
    }

    public void setLast_Updated_By(String last_updated_By) {
        this.last_updated_By = last_updated_By;
    }

    public int getDivision_ID() {
        return division_ID;
    }

    public void setDivision_ID(int division_ID) {
        this.division_ID = division_ID;
    }
}
