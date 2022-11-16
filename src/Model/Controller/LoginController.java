package Model.Controller;

import DBHelper.JDBC;
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
import java.util.Locale;
import java.util.ResourceBundle;



public class LoginController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Label LoginRegionLabel;
    public TextField UsernameField;
    public TextField PasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale locale = Locale.getDefault();
        String country = locale.getDisplayCountry();
        LoginRegionLabel.setText(country);
        System.out.println(country);

    }

    public void OnActionLogin(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {

        String password;
        //username = UsernameField.getText();
        password = PasswordField.getText();
        String sql = "SELECT * FROM USERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()) {
            if (resultSet.getString("Password").equals(password)) {
                System.out.println(password);
            }
        }
        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordsAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments");
        stage.show();
    }
}
