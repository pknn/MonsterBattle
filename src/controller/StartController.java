package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class StartController {
    @FXML
    private AnchorPane startPane;

    @FXML
    void startButtonEvent() throws IOException {
        URL url;
        url = this.getClass().getClassLoader().getResource("fxml/LoadGame.fxml");
        if (url == null) {
            System.out.println("NULL");
            return;
        }
        AnchorPane pane = FXMLLoader.load(url);
        this.startPane.getChildren().setAll(pane);
    }
}
