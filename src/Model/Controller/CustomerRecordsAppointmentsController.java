package Model.Controller;

import DBHelper.JDBC;
import Model.Appointment;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class CustomerRecordsAppointmentsController implements Initializable {
    @FXML
    public TableView<Appointment> AppointmentsTableView;
    @FXML
    public TableView<Customer> CustomerTableView;
    public TableColumn<Customer, String> CustomerIDColumn;
    public TableColumn<Customer, String> CustomerNameColumn;
    public TableColumn<Customer, String> CustomerAddressColumn;
    public TableColumn<Customer, String> CustomerPostalCodeColumn;
    public TableColumn<Customer, String> CustomerPhoneColumn;
    public TableColumn<Customer, Date> CustomerCreateDateColumn;
    public TableColumn<Customer, String> CustomerCreatedByColumn;
    public TableColumn<Customer, Date> CustomerLastUpdateColumn;
    public TableColumn<Customer, String> CustomerUpdatedByColumn;
    public TableColumn<Customer, String> CustomerDivisionIDColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Customer_ID"));
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Customer_Name"));
        CustomerAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Address"));
        CustomerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Postal_Code"));
        CustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Phone"));
        CustomerCreateDateColumn.setCellValueFactory(new PropertyValueFactory<Customer, Date>("Create_Date"));
        CustomerCreatedByColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Created_By"));
        CustomerLastUpdateColumn.setCellValueFactory(new PropertyValueFactory<Customer, Date>("Last_Update"));
        CustomerUpdatedByColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Last_Updated_By"));
        CustomerDivisionIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Division_ID"));
        CustomerTableView.setItems(getDataCustomers());
    }

    public static ObservableList<Customer> getDataCustomers() {
        ObservableList<Customer> observableList = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = JDBC.connection.prepareStatement("SELECT * FROM CUSTOMERS");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                observableList.add(new Customer(Integer.parseInt(resultSet.getString("Customer_ID")),resultSet.getString("Customer_Name"),
                        resultSet.getString("Address"),resultSet.getString("Postal_Code"), resultSet.getString("Phone"),
                        resultSet.getDate("Create_Date"), resultSet.getString("Created_By"),
                        resultSet.getDate("Last_Update"), resultSet.getString("Last_Updated_By"),
                        resultSet.getString("Division_ID")));
                //System.out.println(observableList.toArray());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }

    public void OnActionAddCustomer(ActionEvent actionEvent) {
    }

    public void OnActionUpdateCustomer(ActionEvent actionEvent) {
    }

    public void OnActionAddAppointment(ActionEvent actionEvent) {
    }

    public void OnActionUpdateAppointment(ActionEvent actionEvent) {
    }


}