package FO_program;

import DBHelper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
        JDBC.openConnection();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
