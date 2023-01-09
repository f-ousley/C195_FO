package Controller;

import DBHelper.JDBC;
import FO_program.Main;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;


/** This class controls app login functionality.*/
public class LoginController implements Initializable {

    public Button LoginButton;
    Stage stage;
    Parent scene;
    public static User user;

    @FXML
    private Label LoginRegionLabel;
    public TextField UsernameField;
    public TextField PasswordField;

    @Override
    /** This method initializes text in the scene.*/
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId  zoneId = ZoneId.systemDefault();
        LoginRegionLabel.setText(zoneId.toString());
        UsernameField.setText(Main.resourceBundle.getString("Username"));
        PasswordField.setText(Main.resourceBundle.getString("Password"));
        LoginButton.setText(Main.resourceBundle.getString("Login"));

    }
    /** This method checks username/password combination and loads the home scene.
     * @param actionEvent Button Click*/
    public void OnActionLogin(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {

        String password;
        String username;
        int user_id;
        password = PasswordField.getText();
        String sql = "SELECT * FROM USERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getString("Password").equals(password)) {
                username = UsernameField.getText();
                user_id = Set_UID(password);
                StoreUser(user_id, username, password);
            }

        }
        if(user != null) {
            File file = new File("src/Login_activity.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Successful Login Attempt UserID: " + UsernameField.getText() + " " + Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.now())));
            printWriter.close();
        }
        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(Main.resourceBundle.getString("loginAlertTitle"));
            alert.setContentText(Main.resourceBundle.getString("loginAlertText"));
            alert.setHeaderText(Main.resourceBundle.getString("loginAlertHeader"));
            alert.showAndWait();
            File file = new File("src/Login_activity.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Unsuccessful Login Attempt UserID: " + UsernameField.getText() + " " + Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.now())));
            printWriter.close();
        }
        stage = (Stage) ((Button) (actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordsAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("");
        stage.show();
    }
    /** This method initializes UID (user_id) variable from MySQL database.
     * @param password
     * @throws SQLException*/
    public int Set_UID(String password) throws SQLException {
        int UID = 0;
        PreparedStatement statement =JDBC.connection.prepareStatement("Select USER_ID FROM USERS WHERE PASSWORD = ?");
        statement.setString(1,password);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            UID = resultSet.getInt("USER_ID");
        }
        return UID;
    }
    /** This method creates a User object.
     * @param password
     * @param username
     * @param user_ID */
    public void StoreUser(int user_ID, String username, String password) {
        user = new User(user_ID, username, password);
    }
}
