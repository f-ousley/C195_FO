package Model;

import java.sql.Timestamp;
/** This class creates Country objects.*/
public class Country {
    private int Country_ID = 0;
    private String Country;
    private Timestamp Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    /** This method is a constructor for a Country object.
     @param country_id primary key in database
     @param last_updated_by
     @param last_update
     @param created_by
     @param create_date
     @param country
     */
    public Country(int country_id, String country, Timestamp create_date, String created_by, Timestamp last_update, String last_updated_by){
        Country_ID = country_id;
        Country = country;
        Create_Date = create_date;
        Created_By = created_by;
        Last_Update = last_update;
        Last_Updated_By = last_updated_by;
    }

    public Country(String country) {
    }
    /** This method is a getter for Country country_id
     * @return Country_ID.*/
    public int getCountry_ID() {
        return Country_ID;
    }
    /** This method is a setter for Country country_id.*/
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }
    /** This method is a getter for Country country
     * @return Country.*/
    public String getCountry() {
        return Country;
    }
    /** This method is a setter for Country country.*/
    public void setCountry(String country) {
        Country = country;
    }
    /** This method is a getter for Country create_date.*/
    public Timestamp getCreate_Date() {
        return Create_Date;
    }
    /** This method is a setter for Country create_date.*/
    public void setCreate_Date(Timestamp create_Date) {
        Create_Date = create_Date;
    }
    /** This method is a getter for Country created_by.*/
    public String getCreated_By() {
        return Created_By;
    }
    /** This method is a setter for Country created_by.*/
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }
    /** This method is a getter for Country last_update.*/
    public Timestamp getLast_Update() {
        return Last_Update;
    }
    /** This method is a setter for Country last_update.*/
    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }
    /** This method is a getter for Country updated_by.*/
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }
    /** This method is a setter for Country updated_by.*/
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }
}

