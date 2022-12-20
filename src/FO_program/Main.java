package FO_program;

import DBHelper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    public static ResourceBundle resourceBundle;

    @Override
    public void start(Stage primaryStage) throws Exception{
        resourceBundle = ResourceBundle.getBundle("resources/Nat", Locale.getDefault());
        Parent root = FXMLLoader.load(getClass().getResource("../View/Login.fxml"));
        primaryStage.setTitle(resourceBundle.getString("Login"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        JDBC.openConnection();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
