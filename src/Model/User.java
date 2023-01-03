package Model;
/** This class creates User objects.*/
public class User {
    private int user_ID;
    private String username;
    private String password;

    /** This method is a User constructor.
     @param user_ID primary key for database*/
    public User(int user_ID, String username, String password) {
        this.user_ID = user_ID;
        this.username = username;
        this.password = password;
    }

    public User() {
        user_ID = 0;
        username = null;
        password = null;
    }
    /** This method is a getter for User user_id.*/
    public int getUser_ID() {
        return user_ID;
    }
    /** This method is a setter for User user_id.*/
    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }
    /** This method is a getter for User username.*/
    public String getUsername() {
        return username;
    }
    /** This method is a setter for User username.*/
    public void setUsername(String username) {
        this.username = username;
    }
    /** This method is a getter for User password.*/
    public String getPassword() {
        return password;
    }
    /** This method is a setter for User password.*/
    public void setPassword(String password) {
        this.password = password;
    }
}
