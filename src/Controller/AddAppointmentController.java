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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    public TextField UserIDField;
    public TextField IDField;
    public TextField TitleField;
    public TextField DescriptionField;
    public TextField LocationField;
    public TextField ContactField;
    public TextField TypeField;
    public DatePicker StartDate;
    public DatePicker EndDate;
    Stage stage;
    Parent scene;
    private ObservableList<LocalTime> startList = FXCollections.observableArrayList();
    private ObservableList<LocalTime> endList = FXCollections.observableArrayList();
    private ObservableList<Integer> custList = FXCollections.observableArrayList();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    @FXML
    private ComboBox<LocalTime> ComboStartTime;
    @FXML
    private ComboBox<LocalTime> ComboEndTime;
    @FXML
    private ComboBox<Integer> ComboCustomerID;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboStartTime.setItems(fill_start_end_combo());
        ComboEndTime.setItems(fill_start_end_combo());
        User user = LoginController.user;
        int userid = user.getUser_ID();
        UserIDField.setText(String.valueOf(userid));
        UserIDField.setEditable(false);
        IDField.setEditable(false);
        try {
            ComboCustomerID.setItems(fill_customer_combo());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        System.out.println("Appointment Added");
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
        stage.setTitle("Appointments");
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
    private ObservableList<Integer> fill_customer_combo() throws SQLException {
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("SELECT CUSTOMER_ID FROM CUSTOMERS");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            custList.add(resultSet.getInt("CUSTOMER_ID"));
        }
    return custList;
    }
}
