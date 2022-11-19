package Controller;

import DBHelper.JDBC;
import Model.Appointment;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class CustomerRecordsAppointmentsController implements Initializable {

    Stage stage;
    Parent scene;

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
    public TableView<Appointment> AppointmentsTableView;
    public TableColumn<Appointment, String> AppointmentIDColumn;
    public TableColumn<Appointment, String> AppointmentTitleColumn;
    public TableColumn<Appointment, String> AppointmentDescriptionColumn;
    public TableColumn<Appointment, String> AppointmentLocationColumn;
    public TableColumn<Appointment, String> AppointmentTypeColumn;
    public TableColumn<Appointment, java.sql.Date> AppointmentStartDateColumn;
    public TableColumn<Appointment, java.sql.Date> AppointmentEndDateColumn;
    public TableColumn<Appointment, java.sql.Date> AppointmentCreateDateColumn;
    public TableColumn<Appointment, String> AppointmentCreatedByColumn;
    public TableColumn<Appointment, Time> AppointmentLastUpdateColumn;
    public TableColumn<Appointment, String> AppointmentUpdatedByColumn;
    public TableColumn<Appointment, String> AppointmentCustomerIDColumn;
    public TableColumn<Appointment, String> AppointmentUserIDColumn;
    public TableColumn<Appointment,String> AppointmentContactIDColumn;


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

        AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Appointment_ID"));
        AppointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Title"));
        AppointmentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Description"));
        AppointmentLocationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Location"));
        AppointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Type"));
        AppointmentStartDateColumn.setCellValueFactory(new PropertyValueFactory<Appointment, java.sql.Date>("Start"));
        AppointmentEndDateColumn.setCellValueFactory(new PropertyValueFactory<Appointment, java.sql.Date>("End"));
        AppointmentCreateDateColumn.setCellValueFactory(new PropertyValueFactory<Appointment, java.sql.Date>("Create_Date"));
        AppointmentCreatedByColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Created_By"));
        AppointmentLastUpdateColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Time>("Last_Update"));
        AppointmentUpdatedByColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Last_Updated_By"));
        AppointmentCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Customer_ID"));
        AppointmentUserIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("User_ID"));
        AppointmentContactIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Contact_ID"));
        User user = LoginController.user;
        AppointmentsTableView.setItems(getDataAppointments(user));

    }

    public static ObservableList<Customer> getDataCustomers() {
        ObservableList<Customer> observableList = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = JDBC.connection.prepareStatement("SELECT * FROM CUSTOMERS");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                observableList.add(new Customer((resultSet.getInt("Customer_ID")),resultSet.getString("Customer_Name"),
                        resultSet.getString("Address"),resultSet.getString("Postal_Code"), resultSet.getString("Phone"),
                        resultSet.getDate("Create_Date"), resultSet.getString("Created_By"),
                        resultSet.getDate("Last_Update"), resultSet.getString("Last_Updated_By"),
                        (resultSet.getString("Division_ID"))));
                //System.out.println(observableList.toArray());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }
    public static ObservableList<Appointment> getDataAppointments(User user) {
        ObservableList<Appointment> observableList = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = JDBC.connection.prepareStatement("SELECT * FROM APPOINTMENTS WHERE USER_ID = ?");
            int UID = user.getUser_ID();
            preparedStatement.setString(1,Integer.toString(UID));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                observableList.add(new Appointment((resultSet.getString("Appointment_ID")), resultSet.getString("Title")
                , resultSet.getString("Description"), resultSet.getString("Location"), resultSet.getString("Type"),
                        resultSet.getDate("Start"), resultSet.getDate("End"), resultSet.getDate("Create_Date"),
                        resultSet.getString("Created_By"), resultSet.getTimestamp("Last_update"), resultSet.getString("Last_Updated_By"),
                        (resultSet.getString("Customer_ID")),(resultSet.getString("User_ID")),(resultSet.getString("Contact_ID"))));
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

    public void OnActionAddAppointment(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Add Appointment");
        stage.show();

        

    }

    public void OnActionUpdateAppointment(ActionEvent actionEvent) {
    }


}