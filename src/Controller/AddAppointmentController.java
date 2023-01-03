package Controller;

import DBHelper.JDBC;
import FO_program.Main;
import Model.Appointment;
import Model.Customer;
import Model.User;
import com.sun.javafx.menu.MenuItemBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
/** This class controls adding appointments to database functionality.*/
public class AddAppointmentController implements Initializable {


    @FXML
    public Button AddAppointmentButton;
    Stage stage;
    Parent scene;


    public Button CancelButton;

    public Label AppointmentIDLabel;
    public Label TitleLabel;
    public Label DescriptionLabel;
    public Label LocationLabel;
    public Label ContactLabel;
    public Label TypeLabel;
    public Label CustomerIDLabel;
    public Label StartTimeLabel;
    public Label StartDateLabel;
    public Label EndTimeLabel;
    public Label EndDateLabel;
    public Label UserIDLabel;

    public TextField UserIDField;
    public TextField IDField;
    public TextField TitleField;
    public TextField DescriptionField;
    public TextField LocationField;
    public ComboBox ContactCombo;
    public TextField TypeField;
    public DatePicker StartDate;
    public DatePicker EndDate;

    private ObservableList<LocalTime> startList = FXCollections.observableArrayList();
    private ObservableList<LocalTime> endList = FXCollections.observableArrayList();
    private ObservableList<Integer> custList = FXCollections.observableArrayList();
    private ObservableList<String> contactList = FXCollections.observableArrayList();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @FXML
    private ComboBox<LocalTime> ComboStartTime;
    @FXML
    private ComboBox<LocalTime> ComboEndTime;
    @FXML
    private ComboBox<Integer> ComboCustomerID;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        User user = LoginController.user;
        int userid = user.getUser_ID();

        AppointmentIDLabel.setText(Main.resourceBundle.getString("Appointment") + " " + Main.resourceBundle.getString("ID"));
        TitleLabel.setText(Main.resourceBundle.getString("Title"));
        DescriptionLabel.setText(Main.resourceBundle.getString("Description"));
        LocationLabel.setText(Main.resourceBundle.getString("Location"));
        ContactLabel.setText(Main.resourceBundle.getString("Contact"));
        TypeLabel.setText(Main.resourceBundle.getString("Type"));
        CustomerIDLabel.setText(Main.resourceBundle.getString("Customer") + " " + Main.resourceBundle.getString("ID"));
        StartTimeLabel.setText(Main.resourceBundle.getString("StartTime"));
        StartDateLabel.setText(Main.resourceBundle.getString("StartDate"));
        EndTimeLabel.setText(Main.resourceBundle.getString("EndTime"));
        EndDateLabel.setText(Main.resourceBundle.getString("EndDate"));
        UserIDLabel.setText(Main.resourceBundle.getString("User") + " " + Main.resourceBundle.getString("ID"));
        AddAppointmentButton.setText(Main.resourceBundle.getString("Add"));
        CancelButton.setText(Main.resourceBundle.getString("Cancel"));
        AddAppointmentButton.setDisable(true);

        try {
            ContactCombo.setItems(fill_contact_Combo());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ComboStartTime.setItems(fill_start_end_combo());
        ComboEndTime.setItems(fill_start_end_combo());

        UserIDField.setText(String.valueOf(userid));
        UserIDField.setEditable(false);
        IDField.setEditable(false);

        try {
            ComboCustomerID.setItems(fill_customer_combo());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static boolean checkOverlap(Timestamp appointment_start, Timestamp appointment_end, boolean isError) throws SQLException  {

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("SELECT * FROM Appointments");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            if (appointment_start.before(resultSet.getTimestamp("Start")) && appointment_end.before(resultSet.getTimestamp("Start")) || appointment_start.after(resultSet.getTimestamp("End")) && appointment_end.after(
                    (resultSet.getTimestamp("End")))) {
                isError = false;
            } else{
                isError = true;
            }

            }
    return isError;
    }

    public static void checkTime(LocalTime t){
        if(t.isBefore(LocalTime.of(8,00)) || t.isAfter(LocalTime.of(22,00))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(Main.resourceBundle.getString("checktimeAlertText"));
            alert.setTitle(Main.resourceBundle.getString("checktimeAlertTitle"));
            alert.setContentText(Main.resourceBundle.getString("checktimeAlertHeader"));
            alert.showAndWait();

        }

    }
    public void OnActionAdd(ActionEvent actionEvent) throws SQLException, IOException {

        LocalDate localDate = LocalDate.now();

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement( "INSERT INTO APPOINTMENTS (title, description, location, type, start, end, create_date, created_by,last_update, last_updated_by, customer_id, user_id, contact_id)"
        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        preparedStatement.setString(1, TitleField.getText());
        preparedStatement.setString(2, DescriptionField.getText());
        preparedStatement.setString(3, LocationField.getText());
        preparedStatement.setString(4, TypeField.getText());
        preparedStatement.setTimestamp(5,Timestamp.valueOf(LocalDateTime.of(StartDate.getValue(),ComboStartTime.getValue())));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.of(EndDate.getValue(), ComboEndTime.getValue())));
        preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.now())));
        preparedStatement.setString(8,LoginController.user.getUsername());
        preparedStatement.setTimestamp(9, Timestamp.valueOf(LocalDateTime.of(LocalDate.now(),LocalTime.now())));
        preparedStatement.setString(10, LoginController.user.getUsername());
        preparedStatement.setInt(11,ComboCustomerID.getValue());
        preparedStatement.setInt(12,LoginController.user.getUser_ID());
        preparedStatement.setInt(13,LoginController.user.getUser_ID());

        boolean result = preparedStatement.execute();
        System.out.println(preparedStatement);
        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordsAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments");
        stage.show();
    }

    public void OnActionCancel(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordsAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("");
        stage.show();

    }

    private ObservableList<LocalTime> fill_start_end_combo(){

        LocalTime localTime = LocalTime.of(1,0,0);
        while(localTime != LocalTime.of(23,0,0) ) {
            localTime = localTime.plusMinutes(15);
            startList.add(LocalTime.parse(localTime.format(dateTimeFormatter)));
            endList.add(LocalTime.parse(localTime.format(dateTimeFormatter)));
        }
        return startList;
    }
    private ObservableList<String> fill_contact_Combo() throws SQLException {
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("SELECT Contact_ID FROM CONTACTS");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            contactList.add(String.valueOf(resultSet.getInt("Contact_ID")));
        }
        return contactList;
    }
    private ObservableList<Integer> fill_customer_combo() throws SQLException {

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("SELECT CUSTOMER_ID FROM CUSTOMERS");
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            custList.add(resultSet.getInt("CUSTOMER_ID"));
        }
    return custList;
    }

    public void OnActionStartTime(ActionEvent actionEvent){
        checkTime(ComboStartTime.getValue());

    }

    public void OnActionEndDate(ActionEvent actionEvent) throws SQLException {
            boolean isError = false;
            Timestamp appointment_start = Timestamp.valueOf(LocalDateTime.of(StartDate.getValue(), ComboStartTime.getValue()));
        Timestamp appointment_end = Timestamp.valueOf(LocalDateTime.of(EndDate.getValue(), ComboEndTime.getValue()));
       isError = checkOverlap(appointment_start, appointment_end, isError);
       if(isError) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setHeaderText(Main.resourceBundle.getString("checkoverlapAlertHeader"));
           alert.setTitle(Main.resourceBundle.getString("checkoverlapAlertTitle"));
           alert.setContentText(Main.resourceBundle.getString("checkoverlapAlertText"));
           alert.showAndWait();
       } else {
           AddAppointmentButton.setDisable(false);
       }
    }
}
