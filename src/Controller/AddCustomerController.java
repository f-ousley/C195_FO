package Controller;

import DBHelper.JDBC;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    public TextField IDTextField;
    public ComboBox CountryCombo;
    public ComboBox DivisionCombo;
    public TextField NameTextField;
    public TextField AddressTextField;
    public TextField PostalTextField;
    Stage stage;
    Parent scene;

    public void OnActionCancelCustomer(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordsAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IDTextField.setEditable(false);
        try {
            CountryCombo.setItems(getCountryList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public ObservableList<String> getCountryList() throws SQLException {
        ObservableList<String> countryList = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("SELECT Country FROM Countries");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            countryList.add(resultSet.getString("Country"));
        }
        System.out.println(countryList);
        return countryList;
    }

    public void OnActionCountry(ActionEvent actionEvent) {
        String country = String.valueOf(CountryCombo.getSelectionModel().getSelectedItem());
        System.out.println(country);
        System.out.println("Getting Divisions");
        CustomerRecordsAppointmentsController.customer.setCountry(country);
        if (country.equals("U.S")){getDivisionList(1);System.out.println("USA");}
        if (country.equals("UK")){getDivisionList(2);}
        if (country.equals("Canada")){getDivisionList(3);}
    }

    public void OnActionAdd(ActionEvent actionEvent) throws SQLException {
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("INSERT INTO CUSTOMERS Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By," +
                " Last_Update, Last_Updated_By, Division_ID VALUES ?,?,?,?,?,?,?,?,? ");
        preparedStatement.setString(1, NameTextField.getText());
        preparedStatement.setString(2, AddressTextField.getText());

    }
}
