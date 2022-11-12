package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.LoadListener;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;



public class LoginController implements Initializable{

    @FXML
    private Label LoginRegionLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale locale = Locale.getDefault();
        String country = locale.getDisplayCountry();
        LoginRegionLabel.setText(country);
        System.out.println(country);

    }
}
