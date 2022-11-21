package Controller;

import DBHelper.JDBC;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;



public class LoginController implements Initializable {

    Stage stage;
    Parent scene;
    public static User user;

    @FXML
    private Label LoginRegionLabel;
    public TextField UsernameField;
    public TextField PasswordField;
    private Object String;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale locale = Locale.getDefault();
        ZoneId  zoneId = ZoneId.systemDefault();
        String country = locale.getDisplayCountry();
        LoginRegionLabel.setText(country + "/" + zoneId);

    }

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
        stage = (Stage) ((Button) (actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordsAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments");
        stage.show();
    }

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

    public void StoreUser(int user_ID, String username, String password) {
        user = new User(user_ID, username, password);
        System.out.println(user);
    }
}
