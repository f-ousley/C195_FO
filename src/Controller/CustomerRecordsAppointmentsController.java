package Controller;

import DBHelper.JDBC;
import FO_program.Main;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class CustomerRecordsAppointmentsController implements Initializable {

    public Button AddCustomerButton;
    public Button UpdateCustomerButton;
    public Button DeleteCustomerButton;
    public Button AddAppointmentButton;
    public Button UpdateAppointmentButton;
    public Button DeleteAppointmentButton;
    public Label CustomerLabel;
    public Label AppointmentsLabel;
    public Button RecordsButton;
    Stage stage;
    Parent scene;
    public static Customer customer;
    public static Appointment appointment;
    private static boolean isWeekly;
    public static boolean isWarning = false;
    public RadioButton AppointmentMonthRadio;
    public ToggleGroup MonthWeekTG;
    public RadioButton AppointmentWeekRadio;

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
    public TableColumn<Customer, String> CustomerCountryIDColumn;
    public TableColumn<Customer, String> CustomerCountryColumn;
    public TableColumn<Customer, String> CustomerDivisionColumn;

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

        MonthWeekTG = new ToggleGroup();
        AppointmentMonthRadio.setToggleGroup(MonthWeekTG);
        AppointmentWeekRadio.setToggleGroup(MonthWeekTG);
        AppointmentWeekRadio.setSelected(true);
        AppointmentMonthRadio.setSelected(false);
        User user = LoginController.user;

        CustomerIDColumn.setText(Main.resourceBundle.getString("CustomerIDColumn"));
        CustomerNameColumn.setText(Main.resourceBundle.getString("CustomerNameColumn"));
        CustomerAddressColumn.setText(Main.resourceBundle.getString("CustomerAddressColumn"));
        CustomerPostalCodeColumn.setText(Main.resourceBundle.getString("CustomerPostalCodeColumn"));
        CustomerPhoneColumn.setText(Main.resourceBundle.getString("CustomerPhoneColumn"));
        CustomerCreateDateColumn.setText(Main.resourceBundle.getString("CustomerCreateDateColumn"));
        CustomerCreatedByColumn.setText(Main.resourceBundle.getString("CustomerCreatedByColumn"));
        CustomerLastUpdateColumn.setText(Main.resourceBundle.getString("CustomerLastUpdateColumn"));
        CustomerUpdatedByColumn.setText(Main.resourceBundle.getString("CustomerUpdatedByColumn"));
        CustomerDivisionIDColumn.setText(Main.resourceBundle.getString("CustomerDivisionIDColumn"));
        CustomerCountryIDColumn.setText(Main.resourceBundle.getString("CustomerCountryIDColumn"));
        CustomerCountryColumn.setText(Main.resourceBundle.getString("CustomerCountryColumn"));
        CustomerCountryColumn.setText(Main.resourceBundle.getString("CustomerCountryColumn"));
        CustomerDivisionColumn.setText(Main.resourceBundle.getString("CustomerDivisionColumn"));

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
        CustomerCountryIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Country_ID"));
        CustomerCountryColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Country"));
        CustomerDivisionColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Division"));
        CustomerTableView.setItems(getDataCustomers());

        AppointmentIDColumn.setText(Main.resourceBundle.getString("AppointmentIDColumn"));
        AppointmentTitleColumn.setText(Main.resourceBundle.getString("AppointmentTitleColumn"));
        AppointmentDescriptionColumn.setText(Main.resourceBundle.getString("AppointmentDescriptionColumn"));
        AppointmentLocationColumn.setText(Main.resourceBundle.getString("AppointmentLocationColumn"));
        AppointmentTypeColumn.setText(Main.resourceBundle.getString("AppointmentTypeColumn"));
        AppointmentStartDateColumn.setText(Main.resourceBundle.getString("AppointmentStartDateColumn"));
        AppointmentEndDateColumn.setText(Main.resourceBundle.getString("AppointmentEndDateColumn"));
        AppointmentCreateDateColumn.setText(Main.resourceBundle.getString("AppointmentCreateDateColumn"));
        AppointmentCreatedByColumn.setText(Main.resourceBundle.getString("AppointmentCreatedByColumn"));
        AppointmentLastUpdateColumn.setText(Main.resourceBundle.getString("AppointmentLastUpdateColumn"));
        AppointmentUpdatedByColumn.setText(Main.resourceBundle.getString("AppointmentUpdatedByColumn"));
        AppointmentCustomerIDColumn.setText(Main.resourceBundle.getString("AppointmentCustomerIDColumn"));
        AppointmentUserIDColumn.setText(Main.resourceBundle.getString("AppointmentUserIDColumn"));
        AppointmentContactIDColumn.setText(Main.resourceBundle.getString("AppointmentContactIDColumn"));

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
        isWeekly = true;
        AppointmentsTableView.setItems(getDataAppointments(user));
        AddCustomerButton.setText(Main.resourceBundle.getString("Add"));
        UpdateCustomerButton.setText(Main.resourceBundle.getString("Update"));
        DeleteCustomerButton.setText(Main.resourceBundle.getString("Delete"));
        AddAppointmentButton.setText(Main.resourceBundle.getString("Add"));
        UpdateAppointmentButton.setText(Main.resourceBundle.getString("Update"));
        DeleteAppointmentButton.setText(Main.resourceBundle.getString("Delete"));
        CustomerLabel.setText(Main.resourceBundle.getString("Customer"));
        AppointmentsLabel.setText(Main.resourceBundle.getString("Appointments"));
        RecordsButton.setText(Main.resourceBundle.getString("Records"));
        AppointmentMonthRadio.setText(Main.resourceBundle.getString("Month"));
        AppointmentWeekRadio.setText(Main.resourceBundle.getString("Week"));
    }

    public static ObservableList<Customer> getDataCustomers() {

        ObservableList<Customer> observableList = FXCollections.observableArrayList();
        ObservableList<Customer> custlist = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = JDBC.connection.prepareStatement("SELECT * FROM customers JOIN first_level_divisions ON customers.Division_ID =  first_level_divisions.Division_ID JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                observableList.add(new Customer((resultSet.getInt("Customer_ID")),resultSet.getString("Customer_Name"),
                        resultSet.getString("Address"),resultSet.getString("Postal_Code"), resultSet.getString("Phone"),
                        resultSet.getTimestamp("Create_Date"), resultSet.getString("Created_By"),
                        resultSet.getTimestamp("Last_Update"), resultSet.getString("Last_Updated_By"),
                        (resultSet.getInt("Division_ID")),resultSet.getString("Country"), resultSet.getString("Division"), resultSet.getInt("Country_ID")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  observableList;
    }
    public static ObservableList<Appointment> getDataAppointments(User user) {

        LocalDate datenow = LocalDate.now();
        LocalDate oneweekout = datenow.plusWeeks(1);
        LocalDate onemonthout = datenow.plusMonths(1);
        System.out.println(datenow);
        ObservableList<Appointment> observableList = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = JDBC.connection.prepareStatement("SELECT * FROM APPOINTMENTS WHERE USER_ID = ?");
            int UID = user.getUser_ID();
            preparedStatement.setString(1, Integer.toString(UID));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                observableList.add(new Appointment((resultSet.getInt("Appointment_ID")), resultSet.getString("Title")
                        , resultSet.getString("Description"), resultSet.getString("Location"), resultSet.getString("Type"),
                        resultSet.getTimestamp("Start"), resultSet.getTimestamp("End"), resultSet.getTimestamp("Create_Date"),
                        resultSet.getString("Created_By"), resultSet.getTimestamp("Last_update"), resultSet.getString("Last_Updated_By"),
                        (resultSet.getString("Customer_ID")), (resultSet.getString("User_ID")), (resultSet.getInt("Contact_ID"))));
                }
            checkMinuteWarning(observableList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (isWeekly) {
            ObservableList<Appointment> weeklylist = FXCollections.observableArrayList();
            for (Appointment appt : observableList
            ) {
                if (appt.getStart().toLocalDateTime().isAfter(LocalDateTime.now()) && appt.getStart().toLocalDateTime().isBefore(LocalDateTime.of(oneweekout,LocalTime.now()))) {
                    weeklylist.add(appt);
                }

            }
            return weeklylist;
        } else {
            ObservableList<Appointment> monthlylist = FXCollections.observableArrayList();
            for(Appointment appt : observableList) {
                if(appt.getStart().toLocalDateTime().isAfter(LocalDateTime.now()) && appt.getStart().toLocalDateTime().isBefore(LocalDateTime.of(onemonthout, LocalTime.now()))){
                    monthlylist.add(appt);
                }
            }
            System.out.println(monthlylist);
            return monthlylist;
        }
    }
    public void OnActionAddCustomer(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle(Main.resourceBundle.getString("Add") + " " + Main.resourceBundle.getString("Customer"));
        stage.show();
    }

    public void OnActionUpdateCustomer(ActionEvent actionEvent) throws IOException {

        customer = new Customer();
        customer.setCustomer_ID(CustomerTableView.getSelectionModel().getSelectedItem().getCustomer_ID());
        customer.setCustomer_Name(CustomerTableView.getSelectionModel().getSelectedItem().getCustomer_Name());
        customer.setAddress(CustomerTableView.getSelectionModel().getSelectedItem().getAddress());
        customer.setPostal_Code(CustomerTableView.getSelectionModel().getSelectedItem().getPostal_Code());
        customer.setPhone(CustomerTableView.getSelectionModel().getSelectedItem().getPhone());
        customer.setCreate_Date(CustomerTableView.getSelectionModel().getSelectedItem().getCreate_Date());
        customer.setCreated_By(LoginController.user.getUsername());
        customer.setLast_Update(Timestamp.valueOf(LocalDateTime.now()));
        customer.setLast_Updated_By(LoginController.user.getUsername());
        customer.setDivision_ID(CustomerTableView.getSelectionModel().getSelectedItem().getDivision_ID());
        customer.setCountry_ID(CustomerTableView.getSelectionModel().getSelectedItem().getCountry_ID());

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/UpdateCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle(Main.resourceBundle.getString("Update" ) + " " + Main.resourceBundle.getString("Customer"));
        stage.show();
    }

    public void OnActionAddAppointment(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle(Main.resourceBundle.getString("Add") + " " + Main.resourceBundle.getString("Appointment"));
        stage.show();

    }

    public void OnActionUpdateAppointment(ActionEvent actionEvent) throws IOException {

        appointment = new Appointment();
        appointment.setAppointment_ID(AppointmentsTableView.getSelectionModel().getSelectedItem().getAppointment_ID());
        appointment.setTitle(AppointmentsTableView.getSelectionModel().getSelectedItem().getTitle());
        appointment.setDescription(AppointmentsTableView.getSelectionModel().getSelectedItem().getDescription());
        appointment.setLocation(AppointmentsTableView.getSelectionModel().getSelectedItem().getLocation());
        appointment.setType(AppointmentsTableView.getSelectionModel().getSelectedItem().getType());
        appointment.setStart(AppointmentsTableView.getSelectionModel().getSelectedItem().getStart());
        appointment.setEnd(AppointmentsTableView.getSelectionModel().getSelectedItem().getEnd());
        appointment.setCreate_Date(AppointmentsTableView.getSelectionModel().getSelectedItem().getCreate_Date());
        appointment.setCreated_By(AppointmentsTableView.getSelectionModel().getSelectedItem().getCreated_By());
        appointment.setLast_Update(AppointmentsTableView.getSelectionModel().getSelectedItem().getLast_Update());
        appointment.setLast_Updated_By(AppointmentsTableView.getSelectionModel().getSelectedItem().getLast_Updated_By());
        appointment.setCustomer_ID(AppointmentsTableView.getSelectionModel().getSelectedItem().getCustomer_ID());
        appointment.setUser_ID(AppointmentsTableView.getSelectionModel().getSelectedItem().getUser_ID());
        appointment.setContact_ID(AppointmentsTableView.getSelectionModel().getSelectedItem().getContact_ID());
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/UpdateAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle(Main.resourceBundle.getString("Update") + " " + Main.resourceBundle.getString("Appointment"));
        stage.show();

    }

    public void OnActionDeleteAppointment(ActionEvent actionEvent) throws SQLException {
        int appt;
        appt = AppointmentsTableView.getSelectionModel().getSelectedItem().getAppointment_ID();
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement( "DELETE FROM Appointments WHERE Appointment_ID = ?");
        preparedStatement.setInt(1, appt);
        System.out.println(preparedStatement);
        boolean result = preparedStatement.execute();
        AppointmentsTableView.setItems(getDataAppointments(LoginController.user));
    }

    public void OnActionDeleteCustomer(ActionEvent actionEvent) throws SQLException {
        int cust;
        cust = CustomerTableView.getSelectionModel().getSelectedItem().getCustomer_ID();
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("DELETE FROM CUSTOMERS WHERE Customer_ID = ?");
        preparedStatement.setInt(1, cust);
        System.out.println(preparedStatement);
        boolean result = preparedStatement.execute();
        CustomerTableView.setItems(getDataCustomers());


    }
    public static void checkMinuteWarning(ObservableList<Appointment> appts) {
        for (Appointment apt : appts) {
            if (apt.getStart().toLocalDateTime().isAfter(LocalDateTime.now()) && apt.getStart().toLocalDateTime().isBefore(LocalDateTime.now().plusMinutes(15))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(Main.resourceBundle.getString("alert2title"));
                alert.setContentText(Main.resourceBundle.getString("alert2text") + apt.getAppointment_ID() + Main.resourceBundle.getString("alert2text2") + apt.getStart().toLocalDateTime());
                alert.setHeaderText(Main.resourceBundle.getString("alert2header"));
                alert.showAndWait();
                isWarning = true;
            }
        }
        if(!isWarning) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(Main.resourceBundle.getString("alert1title"));
            alert.setContentText(Main.resourceBundle.getString("alert1text"));
            alert.setHeaderText(Main.resourceBundle.getString("alert1header"));
            alert.showAndWait();
        }
    }

    public void CustomerTableOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void OnActionMonthly(ActionEvent actionEvent) {
        isWeekly = false;
        AppointmentsTableView.setItems(getDataAppointments(LoginController.user));


    }

    public void OnActionWeekly(ActionEvent actionEvent) {
        isWeekly = true;
        AppointmentsTableView.setItems(getDataAppointments(LoginController.user));
    }

    public void OnActionRecords(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Records.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle(Main.resourceBundle.getString("Records"));
        stage.show();
    }
}