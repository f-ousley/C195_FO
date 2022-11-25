package Model;

import java.sql.Timestamp;

public class Country {
    private int Country_ID = 0;
    private String Country;
    private Timestamp Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;

    public Country(int country_id, String country, Timestamp create_date, String created_by, Timestamp last_update, String last_updated_by){
        Country_ID = country_id;
        Country = country;
        Create_Date = create_date;
        Created_By = created_by;
        Last_Update = last_update;
        Last_Updated_By = last_updated_by;
    }
    Country(){}

    public Country(String country) {
    }

    public int getCountry_ID() {
        return Country_ID;
    }

    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public Timestamp getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(Timestamp create_Date) {
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




}

