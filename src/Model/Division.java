package Model;

import java.sql.Timestamp;

/** This class creates Division objects.*/
public class Division {
    private int Division_ID;
    private String Division;
    private Timestamp Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Country_ID;

    /** This method is a constructor for Division object.
     @param division_ID primary key in database*/
    public Division(int division_ID, String division, Timestamp create_Date, String created_By, Timestamp last_Update, String last_Updated_By, int country_ID) {
        Division_ID = division_ID;
        Division = division;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
        Country_ID = country_ID;
    }
    public Division(){}
    /** This method is a getter for Division division_id
     * @return Division_ID.*/
    public int getDivision_ID() {
        return Division_ID;
    }
    /** This method is a setter for Division division_id.*/
    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }
    /** This method is a getter for Division division
     * @return Division.*/
    public String getDivision() {
        return Division;
    }
    /** This method is a setter for Division division.*/
    public void setDivision(String division) {
        Division = division;
    }
    /** This method is a getter for Division create_date
     * @return Create_Date.*/
    public Timestamp getCreate_Date() {
        return Create_Date;
    }
    /** This method is a setter for Division create_date.*/
    public void setCreate_Date(Timestamp create_Date) {
        Create_Date = create_Date;
    }
    /** This method is a getter for Division created_by
     * @return Created_By.*/
    public String getCreated_By() {
        return Created_By;
    }
    /** This method is a setter for Division created_by.*/
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }
    /** This method is a getter for Division last_update
     * @return Last_Update.*/
    public Timestamp getLast_Update() {
        return Last_Update;
    }
    /** This method is a setter for Division last_update.*/
    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }
    /** This method is a getter for Division last_updated_by.
     * @return Last_Updated_By*/
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }
    /** This method is a setter for Division last_update_by.*/
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }
    /** This method is a getter for Division country_id
     * @return Country_ID.*/
    public int getCountry_ID() {
        return Country_ID;
    }
    /** This method is a setter for Division country_id.*/
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }
}
