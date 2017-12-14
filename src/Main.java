import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for running and launching start scene
 *
 * @author Pakanon Pantisawat
 */

public class Main extends Application {
    final private static String START_SCENE = "fxml/Start.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = this.getClass().getClassLoader().getResource(START_SCENE);
        Parent root = FXMLLoader.load(url);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Monster Battle");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
