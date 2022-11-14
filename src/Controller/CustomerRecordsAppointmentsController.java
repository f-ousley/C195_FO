package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerRecordsAppointmentsController implements Initializable {

    Stage stage;
    Parent scene;

    public TableView <Appointments> AppointmentsTableView;
    public TableColumn<Appointments, Integer> AppointmentsIDColumn;
    public TableColumn AppointmentsTitleColumn;
    public TableColumn AppointmentDescriptionColumn;
    public TableColumn AppointmentLocationColumn;
    public TableColumn AppointmentsContactColumn;
    public TableColumn AppointmentsTypeColumn;
    public TableColumn AppointmentsStartColumn;
    public TableColumn AppointmentsEndColumn;
    public TableColumn AppointmentsCustomerIDColumn;
    public TableColumn AppointmentsUserIDColumn;
    public TableView<Customers> CustomerTableView;
    public TableColumn CustomerIDColumn;
    public TableColumn CustomerNameColumn;
    public TableColumn CustomerAddressColumn;
    public TableColumn CustomerPostalCodeColumn;
    public TableColumn CustomerPhoneColumn;
    public TableColumn CustomerStateColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void OnActionAddCustomer(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Add Customer");
        stage.show();
    }

    public void OnActionUpdateCustomer(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/UpdateCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Update Customer");
        stage.show();
    }

    public void OnActionAddAppointment(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Add Appointment");
        stage.show();
    }

    public void OnActionUpdateAppointment(ActionEvent actionEvent) throws IOException {

        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/UpdateAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Update Appointment");
        stage.show();
    }
}
