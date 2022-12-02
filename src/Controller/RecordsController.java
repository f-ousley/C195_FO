package Controller;

import DBHelper.JDBC;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RecordsController implements Initializable {
    public TableColumn ContactAppointmentColumn;
    public TableColumn ContactTitleColumn;
    public TableColumn ContactTypeColumn;
    public TableColumn ContactDescriptionColumn;
    public TableColumn ContactStartColumn;
    public TableColumn ContactEndColumn;
    public TableColumn ContactCustomerColumn;
    public ComboBox ContactCombo;
    public TableColumn ContactNameColumn;
    public TableView<Contact> ContactTableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ContactNameColumn.setCellValueFactory(new PropertyValueFactory<>("Contact_Name"));
        ContactAppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        ContactTitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        ContactTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        ContactDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        ContactStartColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
        ContactEndColumn.setCellValueFactory(new PropertyValueFactory<>("End"));
        ContactCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        try {
            setContactCombo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void setContactCombo() throws SQLException {
        ObservableList<String> contact_names = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("Select Distinct Contact_Name From Contacts");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            contact_names.add(resultSet.getString("Contact_Name"));
        }
        ContactCombo.setItems(contact_names);

    }

    public void OnActionContact(ActionEvent actionEvent) throws SQLException {
        ObservableList<Contact> contact_list = FXCollections.observableArrayList();
        PreparedStatement preparedStatement  = JDBC.connection.prepareStatement("SELECT  contacts.Contact_Name, appointments.Appointment_ID, appointments.Title, appointments.Type, appointments.Description, appointments.Start, appointments.End, customers.Customer_ID FROM Appointments " +
                "Inner Join Contacts ON contacts.Contact_ID = Appointments.Contact_ID " +
                "Inner Join Customers ON customers.Customer_ID = Appointments.Customer_ID " +
                "where contacts.Contact_Name = ?");
        preparedStatement.setString(1, ContactCombo.getSelectionModel().getSelectedItem().toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            contact_list.add(new Contact(resultSet.getString("contacts.Contact_Name"), resultSet.getInt("appointments.Appointment_ID"), resultSet.getString("appointments.Title"), resultSet.getString("appointments.Type"), resultSet.getString("appointments.Description"),
            resultSet.getTimestamp("appointments.Start"), resultSet.getTimestamp("appointments.End"), resultSet.getInt("customers.Customer_ID")));

        }
        ContactTableView.setItems(contact_list);
    }
}
