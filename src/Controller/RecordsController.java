package Controller;

import DBHelper.JDBC;
import FO_program.Main;
import Model.Contact;
import Model.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
/** This class creates records for rubric requirement.*/
public class RecordsController implements Initializable {

    public ComboBox MonthComboBox;
    public ComboBox TypeComboBox;
    public Label TotalLabel;
    Stage stage;
    Parent scene;

    public TableView CustomRecordTable;
    public TableColumn CustomNameColumn;
    public TableColumn CustomTotalColumn;
    public TableView<Contact> ContactTableView;
    public TableColumn ContactAppointmentColumn;
    public TableColumn ContactTitleColumn;
    public TableColumn ContactTypeColumn;
    public TableColumn ContactDescriptionColumn;
    public TableColumn ContactStartColumn;
    public TableColumn ContactEndColumn;
    public TableColumn ContactCustomerColumn;
    public TableColumn ContactNameColumn;
    public ComboBox ContactCombo;
    public TableColumn TypeMonthColumn;
    public TableColumn TypeColumn;
    public TableColumn TypeTotalColumn;
    public TableView TypeTableView;
    public Button CancelButton;


    @Override
    /** This method initializes the text in the scene.*/
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CancelButton.setText(Main.resourceBundle.getString("Cancel"));
        ContactNameColumn.setCellValueFactory(new PropertyValueFactory<>("Contact_Name"));
        ContactAppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        ContactTitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        ContactTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        ContactDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        ContactStartColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
        ContactEndColumn.setCellValueFactory(new PropertyValueFactory<>("End"));
        ContactCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));

        CustomNameColumn.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        CustomTotalColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));

        CancelButton.setText(Main.resourceBundle.getString("Cancel"));
        ContactNameColumn.setText(Main.resourceBundle.getString("Contact"));
        ContactAppointmentColumn.setText(Main.resourceBundle.getString("Appointment") + " " + Main.resourceBundle.getString("ID"));
        ContactTitleColumn.setText(Main.resourceBundle.getString("Title"));
        ContactTypeColumn.setText(Main.resourceBundle.getString("Type"));
        ContactDescriptionColumn.setText(Main.resourceBundle.getString("Description"));
        ContactStartColumn.setText(Main.resourceBundle.getString("StartTime"));
        ContactEndColumn.setText(Main.resourceBundle.getString("EndTime"));
        ContactCustomerColumn.setText(Main.resourceBundle.getString("Customer"));


        try {
            setCombo();
            setRecordTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /** This method sets the Record TableView.
     * @throws SQLException*/
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
    /** This method sets the Type TableView.
     * @throws SQLException*/
    public void setTypeNumber() throws SQLException {
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("SELECT Count(*) AS Total from Appointments where ? = MONTHNAME(Start) AND Type = ?");
        preparedStatement.setString(1, MonthComboBox.getValue().toString());
        preparedStatement.setString(2, TypeComboBox.getValue().toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(preparedStatement);
        int total = 1;
        while(resultSet.next()){
            total = resultSet.getInt("Total");

        }
        TotalLabel.setText(String.valueOf(total));
    }
    /** This method sets the contact combobox with Contact_Name from MySQL database.
     * @throws SQLException*/
    public void setCombo() throws SQLException {
        ObservableList<String> contact_names = FXCollections.observableArrayList();
        ObservableList<String> month_names = FXCollections.observableArrayList();
        ObservableList<String> meeting_type_names = FXCollections.observableArrayList();
        month_names.addAll("January","February","March","April","May","June","July","August","September","October", "November","December");
        MonthComboBox.setItems(month_names);
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("Select Distinct Contact_Name From Contacts");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            contact_names.add(resultSet.getString("Contact_Name"));
        }
        ContactCombo.setItems(contact_names);
        PreparedStatement preparedStatement1 = JDBC.connection.prepareStatement("Select Type from Appointments");
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        while(resultSet1.next()){
            meeting_type_names.add(resultSet1.getString("Type"));
        }
        TypeComboBox.setItems(meeting_type_names);
    }
    /** This method fills Contact TableView with contacts from MySQL database.
     * @throws SQLException
     * @param actionEvent Selection*/
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
    /** This method sets scene to home.
     * @param actionEvent Button Click*/
    public void OnActionCancel(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerRecordsAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("");
        stage.show();
    }

    public void OnActionType(ActionEvent actionEvent) throws SQLException {
        setTypeNumber();
    }
}
