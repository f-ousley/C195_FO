package Controller;

import DBHelper.JDBC;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IDTextField.setEditable(false);
        IDTextField.setText(String.valueOf(CustomerRecordsAppointmentsController.appointment.getAppointment_ID()));
        TitleTextField.setText(CustomerRecordsAppointmentsController.appointment.getTitle());
        DescriptionTextField.setText(CustomerRecordsAppointmentsController.appointment.getDescription());
        LocationTextField.setText(CustomerRecordsAppointmentsController.appointment.getLocation());
        ContactTextField.setText(CustomerRecordsAppointmentsController.appointment.getContact_ID());
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

    public void OnActionUpdate(ActionEvent actionEvent) throws SQLException {
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("UPDATE APPOINTMENTS SET");
    }
}
