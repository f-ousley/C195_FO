package FO_program;

import DBHelper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;
/** This class creates an app that schedules appointments.*/
public class Main extends Application {

    public static ResourceBundle resourceBundle;

    @Override
    /** This method starts the stage and sets the scene. This method creates resource bundle for localization.*/
    public void start(Stage primaryStage) throws Exception{
        resourceBundle = ResourceBundle.getBundle("resources/Nat", Locale.getDefault());
        Parent root = FXMLLoader.load(getClass().getResource("../View/Login.fxml"));
        primaryStage.setTitle(resourceBundle.getString("Login"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        JDBC.openConnection();

    }
    /** This is the main method. This is the first method that gets called when you run your java program.*/
    public static void main(String[] args) {
        launch(args);
    }

}
