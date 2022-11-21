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
    private ObservableList<String> startList = FXCollections.observableArrayList();
    private ObservableList<String> endList = FXCollections.observableArrayList();
    private ObservableList<Integer> custList = FXCollections.observableArrayList();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    @FXML
    private ComboBox<String> ComboStartTime;
    @FXML
    private ComboBox<String> ComboEndTime;
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
        preparedStatement.setDate(5,java.sql.Date.valueOf(StartDate.getValue()));
        preparedStatement.setDate(6, java.sql.Date.valueOf(EndDate.getValue()));
        preparedStatement.setDate(7, Date.valueOf(LocalDate.now()));
        preparedStatement.setString(8,LoginController.user.getUsername());
        preparedStatement.setDate(9, Date.valueOf(LocalDate.now()));
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

    private ObservableList<String> fill_start_end_combo(){
        LocalTime localTime = LocalTime.of(8,0,0);
        while(localTime != LocalTime.of(17,0,0) ) {
            localTime = localTime.plusHours(1);
            startList.add(localTime.format(dateTimeFormatter));
            endList.add(localTime.format(dateTimeFormatter));
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
