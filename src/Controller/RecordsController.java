package Controller;

import DBHelper.JDBC;
import Model.Contact;
import Model.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RecordsController implements Initializable {

    Stage stage;
    Parent scene;


    public TableView CustomRecordTable;
    public TableColumn CustomNameColumn;
    public TableColumn CustomTotalColumn;

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

    public TableColumn TypeMonthColumn;
    public TableColumn TypeColumn;
    public TableColumn TypeTotalColumn;
    public TableView TypeTableView;

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

        TypeMonthColumn.setCellValueFactory(new PropertyValueFactory<>("Month"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        TypeTotalColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));

        CustomNameColumn.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        CustomTotalColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        try {
            setContactCombo();
            setTypeTable();
            setRecordTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void setRecordTable() throws SQLException {
        ObservableList recordlist = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("SELECT customers.customer_name, COUNT(*) as Number FROM customers JOIN appointments " +
                "ON customers.customer_id = appointments.customer_id GROUP BY customer_name");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            recordlist.add( new Record(resultSet.getString("customers.Customer_Name"), resultSet.getInt("Number")));
            CustomRecordTable.setItems(recordlist);
        }
    }

    public void setTypeTable() throws SQLException {
        ObservableList typelist = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("SELECT MONTHNAME(Start) AS Month, appointments.Type, count(*) AS Number from appointments\n" +
                "GROUP BY Start, appointments.Type");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            typelist.add(new Record(resultSet.getString("Month"), resultSet.getString("Type"), resultSet.getInt("Number")));
        }
        TypeTableView.setItems(typelist);
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

    public void OnActionCancel(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerRecordsAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Add Appointment");
        stage.show();
    }
}
