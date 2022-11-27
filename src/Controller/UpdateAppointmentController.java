package Controller;

import DBHelper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {

    public TextField IDTextField;
    public TextField TitleTextField;
    public TextField DescriptionTextField;
    public TextField LocationTextField;
    public TextField ContactTextField;
    public TextField TypeTextField;
    public TextField CustomerIDTextField;
    public DatePicker StartTextField;
    public DatePicker EndTextField;
    public TextField UserIDTextField;
    public ComboBox StartTime;
    public ComboBox EndTime;
    Stage stage;
    Parent scene;

    ObservableList<String> startList = FXCollections.observableArrayList();
    ObservableList<String> endList =  FXCollections.observableArrayList();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IDTextField.setEditable(false);
        StartTime.setItems(fill_start_end_combo());
        EndTime.setItems(fill_start_end_combo());
        IDTextField.setText(String.valueOf(CustomerRecordsAppointmentsController.appointment.getAppointment_ID()));
        TitleTextField.setText(CustomerRecordsAppointmentsController.appointment.getTitle());
        DescriptionTextField.setText(CustomerRecordsAppointmentsController.appointment.getDescription());
        LocationTextField.setText(CustomerRecordsAppointmentsController.appointment.getLocation());
        ContactTextField.setText(String.valueOf(CustomerRecordsAppointmentsController.appointment.getContact_ID()));
        TypeTextField.setText(CustomerRecordsAppointmentsController.appointment.getType());
        CustomerIDTextField.setText(CustomerRecordsAppointmentsController.appointment.getCustomer_ID());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = CustomerRecordsAppointmentsController.appointment.getStart().toLocalDateTime().toLocalDate();
        LocalTime startTime = CustomerRecordsAppointmentsController.appointment.getStart().toLocalDateTime().toLocalTime();
        LocalTime endTime = CustomerRecordsAppointmentsController.appointment.getEnd().toLocalDateTime().toLocalTime();
        LocalDate endDate = CustomerRecordsAppointmentsController.appointment.getEnd().toLocalDateTime().toLocalDate();
        StartTextField.setValue(startDate);
        EndTextField.setValue(endDate);
        StartTime.getSelectionModel().select(startTime);
        EndTime.getSelectionModel().select(endTime);
        UserIDTextField.setText(CustomerRecordsAppointmentsController.appointment.getUser_ID());

    }

    public void OnActionCancel(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordsAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments");
        stage.show();
    }

    public void OnActionUpdate(ActionEvent actionEvent) throws SQLException, ParseException, IOException {
        LocalTime starttime = LocalTime.parse(StartTime.getSelectionModel().getSelectedItem().toString(), dateTimeFormatter);
        LocalTime endtime = LocalTime.parse(EndTime.getSelectionModel().getSelectedItem().toString(), dateTimeFormatter);
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?, Created_By = ?"
        + "Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?");
        preparedStatement.setString(1, TitleTextField.getText());
        preparedStatement.setString(2, DescriptionTextField.getText());
        preparedStatement.setString(3, LocationTextField.getText());
        preparedStatement.setString(4, TypeTextField.getText());
        preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.of(StartTextField.getValue(), starttime)));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.of(EndTextField.getValue(), endtime)));
        preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
        preparedStatement.setString(8, LoginController.user.getUsername());
        preparedStatement.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
        preparedStatement.setString(10, LoginController.user.getUsername());
        preparedStatement.setInt(11, Integer.parseInt(CustomerIDTextField.getText()));
        preparedStatement.setInt(12, LoginController.user.getUser_ID());
        preparedStatement.setInt(13, CustomerRecordsAppointmentsController.appointment.getContact_ID());
        preparedStatement.setInt(14, CustomerRecordsAppointmentsController.appointment.getAppointment_ID());
        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordsAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments");
        stage.show();
    }

    private ObservableList<String> fill_start_end_combo(){
        LocalTime localTime = LocalTime.of(1,0,0);
        while(localTime != LocalTime.of(23,0,0) ) {
            localTime = localTime.plusMinutes(15);
            startList.add(localTime.format(dateTimeFormatter));
            endList.add(localTime.format(dateTimeFormatter));
        }
        return startList;
    }
}
