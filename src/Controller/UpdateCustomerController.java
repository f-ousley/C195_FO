package Controller;

import DBHelper.JDBC;
import Model.Country;
import Model.Division;
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
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {
    public ComboBox CountryCombo;
    public ComboBox DivisionCombo;
    public static Country country;
    ObservableList<Division> divisionlist = FXCollections.observableArrayList();
    ObservableList<String> countryList = FXCollections.observableArrayList();
    public TextField CustomerIDField;
    public TextField NameField;
    public TextField AddressField;
    public TextField PostalCodeField;
    public TextField PhoneField;
    public TextField DivisionIDField;
    Stage stage;
    Parent scene;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerIDField.setText(Integer.toString(CustomerRecordsAppointmentsController.customer.getCustomer_ID()));
        CustomerIDField.setEditable(false);
        NameField.setText(CustomerRecordsAppointmentsController.customer.getCustomer_Name());
        AddressField.setText(CustomerRecordsAppointmentsController.customer.getAddress());
        PostalCodeField.setText(CustomerRecordsAppointmentsController.customer.getPostal_Code());
        PhoneField.setText(CustomerRecordsAppointmentsController.customer.getPhone());
        DivisionIDField.setText(CustomerRecordsAppointmentsController.customer.getDivision_ID());
        try {
            CountryCombo.setItems(getCountryList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //DivisionCombo.setItems();
    }

    public ObservableList<String> getCountryList() throws SQLException {
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("SELECT Country FROM Countries");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            countryList.add(resultSet.getString("Country"));
        }
        return countryList;
    }

    //public ObservableList<String> getDivisionlist() {
       // return divisionlist;
   // }

    public void OnActionCancelCustomer(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordsAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments");
        stage.show();
    }

    public void OnActionUpdateCustomer(ActionEvent actionEvent) throws IOException, SQLException {

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("update CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ?"
                + " WHERE Customer_ID = ?");

        preparedStatement.setString(1, NameField.getText());
        preparedStatement.setString(2, AddressField.getText());
        preparedStatement.setString(3, PostalCodeField.getText());
        preparedStatement.setString(4, PhoneField.getText());
        preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
        preparedStatement.setString(6, LoginController.user.getUsername());
        preparedStatement.setInt(7, Integer.valueOf(DivisionIDField.getText()));
        preparedStatement.setInt(8, CustomerRecordsAppointmentsController.customer.getCustomer_ID());
        System.out.println(preparedStatement.toString());
        int result = preparedStatement.executeUpdate();
        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordsAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Add Customer");
        stage.show();
    }
}
