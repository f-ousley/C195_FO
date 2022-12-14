package Controller;

import DBHelper.JDBC;
import FO_program.Main;
import Model.Customer;
import Model.NavigationLambda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
/** This class controls adding a customer to database functionality.*/
public class AddCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    public Button AddButton;
    public Button CancelButton;

    public Customer customer = new Customer();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /** I implemented this lambda to streamline repeating code.*/
    NavigationLambda navigationLambda = () ->{
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerRecordsAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("");
        stage.show();
    };
    public Label IDLabel;
    public Label NameLabel;
    public Label AddressLabel;
    public Label PostalCodeLabel;
    public Label PhoneLabel;
    public Label CountryLabel;
    public Label StateProvinceLabel;

    public TextField IDTextField;
    public ComboBox CountryCombo;
    public ComboBox DivisionCombo;
    public TextField NameTextField;
    public TextField AddressTextField;
    public TextField PostalTextField;
    public TextField PhoneTextField;


    @Override
    /** This method initializes the text in the scene.*/
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IDTextField.setEditable(false);
        try {
            CountryCombo.setItems(getCountryList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        IDLabel.setText(Main.resourceBundle.getString("ID"));
        NameLabel.setText(Main.resourceBundle.getString("Name"));
        AddressLabel.setText(Main.resourceBundle.getString("Address"));
        PostalCodeLabel.setText(Main.resourceBundle.getString("PostalCode"));
        PhoneLabel.setText(Main.resourceBundle.getString("Phone"));
        CountryLabel.setText(Main.resourceBundle.getString("Country"));
        StateProvinceLabel.setText(Main.resourceBundle.getString("StateProvince"));
        AddButton.setText(Main.resourceBundle.getString("Add"));
        CancelButton.setText(Main.resourceBundle.getString("Cancel"));


    }
    /** This method gets country strings from MySQL database.
     * @throws SQLException
     * @return ObservableList</String> countryList*/
    public ObservableList<String> getCountryList() throws SQLException {
        ObservableList<String> countryList = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("SELECT Country FROM Countries");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            countryList.add(resultSet.getString("Country"));
        }
        System.out.println(countryList);
        return countryList;
    }

    /** This method creates a list of Divisions from MySQL database.
     * @throws SQLException
     * @param country int*/
    public void getDivisionList(int country) throws SQLException {
        ObservableList<String> divisions =  FXCollections.observableArrayList();
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("select Division FROM first_level_divisions where Country_ID = ? ");
        preparedStatement.setInt(1,country);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(resultSet);
        while(resultSet.next()) {
            divisions.add(resultSet.getString("Division"));
        }
        setDivisionCombo(divisions);
    }

    /** This method sets DivisionCombo combobox with values.
     * @param divisionlist ObservableList</String>*/
    public void setDivisionCombo(ObservableList<String> divisionlist){
        DivisionCombo.setItems(divisionlist);
    }

    /** This method sets customer country from selection and calls getDivisionList method.
     * @throws SQLException
     * @param actionEvent Selection*/
    public void OnActionCountry(ActionEvent actionEvent) throws SQLException {
        String country = String.valueOf(CountryCombo.getSelectionModel().getSelectedItem());
        customer.setCountry(country);
        if (country.equals("U.S")){getDivisionList(1);}
        if (country.equals("UK")){getDivisionList(2);}
        if (country.equals("Canada")){getDivisionList(3);}
    }

    /** This method updates MySQL database Customer table and loads home scene. The use of navigationLambda simplifies writing repeating code.
     * @throws IOException
     * @throws SQLException
     * @param actionEvent Button Click*/
    public void OnActionAdd(ActionEvent actionEvent) throws SQLException, IOException {

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By," +
                " Last_Update, Last_Updated_By, Division_ID) VALUES (?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1, NameTextField.getText());
        preparedStatement.setString(2, AddressTextField.getText());
        preparedStatement.setString(3, PostalTextField.getText());
        preparedStatement.setString(4, PhoneTextField.getText());
        preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now().format(dateTimeFormatter)));
        preparedStatement.setString(6, LoginController.user.getUsername());
        preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now().format(dateTimeFormatter)));
        preparedStatement.setString(8, LoginController.user.getUsername());
        preparedStatement.setInt(9, getDivisionID(customer.getDivision()));
        System.out.println(preparedStatement);
        int resultSet =  preparedStatement.executeUpdate();
        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        navigationLambda.loadhome();
    }

    /** This method returns Division_ID from MySQL database.
     * @throws SQLException
     * @param division String
     * @return Division_ID int*/
    public int getDivisionID(String division) throws SQLException {
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement("SELECT Division_ID FROM First_Level_Divisions WHERE Division = ?");
        preparedStatement.setString(1, customer.getDivision());
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            customer.setDivision_ID(resultSet.getInt("Division_ID"));
        }
        return customer.getDivision_ID();
    }

    /** This method sets customer object division from DivisionCombo combobox selection.
     * @param actionEvent Selection*/
    public void OnActionDivision(ActionEvent actionEvent) {
        customer.setDivision(DivisionCombo.getSelectionModel().getSelectedItem().toString());
    }

    /** This method loads the home screen scene. The use of navigationLambda simplifies writing repeating code.
     * @param actionEvent Button Click*/
    public void OnActionCancelCustomer(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        navigationLambda.loadhome();
    }
}
