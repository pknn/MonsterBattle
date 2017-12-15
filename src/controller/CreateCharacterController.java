package controller;

import component.Monster;
import component.Type;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

import static controller.ResourceController.gh;
import static controller.ResourceController.player;

/**
 * FXML Controller class for Create Character scene
 *
 * @author Pakanon Pantisawat
 */

public class CreateCharacterController {

    @FXML
    private AnchorPane createCharacterPane;

    @FXML
    private ProgressBar atkBar;

    @FXML
    private ProgressBar hpBar;

    @FXML
    private ProgressBar defBar;

    @FXML
    private ProgressBar spdBar;

    @FXML
    private Button createCharacterButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button nextType;

    @FXML
    private Button prevType;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField nameField;

    @FXML
    private Label atkLabel;

    @FXML
    private Label hpLabel;

    @FXML
    private Label spdLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label defLabel;

    @FXML
    void initialize() {
        player = new Monster("temp", Type.FIRE);
        createCharacterButton.setDisable(true);
        update();
    }

    @FXML
    void nameInputEvent() {
        if (nameField.getText().equals("") || nameField.getText().contains(" "))
            createCharacterButton.setDisable(true);
        else
            createCharacterButton.setDisable(false);
    }

    private int type = 1;
    @FXML
    void typeSelectEvent(ActionEvent event) {
        if (event.getSource().equals(prevType)) type--;
        else type++;
        if (type > 3) type = 1;
        else if (type < 1) type = 3;
        player.setType(type);
        update();
    }

    @FXML
    void createButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(cancelButton)) player = null;
        else {
            player.setName(nameField.getText());
            gh.addSave(player);
        }
        URL url = this.getClass().getClassLoader().getResource("fxml/LoadGame.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        createCharacterPane.getChildren().setAll(pane);
    }

    private void update() {
        imageView.setImage(player.getImage(false));
        typeLabel.setText(String.valueOf(player.getType()));
        Timeline timeline = new Timeline();

        KeyValue hpValue = new KeyValue(hpBar.progressProperty(), player.getStatus().getHp() / 1000);
        KeyValue atkValue = new KeyValue(atkBar.progressProperty(), player.getStatus().getAtk() / 250);
        KeyValue defValue = new KeyValue(defBar.progressProperty(), player.getStatus().getDef() / 150);
        KeyValue spdValue = new KeyValue(spdBar.progressProperty(), player.getStatus().getSpd() / 120);

        KeyFrame frame = new KeyFrame(new Duration(1000), hpValue, atkValue, defValue, spdValue);
        timeline.getKeyFrames().add(frame);
        timeline.play();
        hpLabel.setText(String.format("%.0f", player.getStatus().getHp()));
        atkLabel.setText(String.format("%.0f", player.getStatus().getAtk()));
        defLabel.setText(String.format("%.0f", player.getStatus().getDef()));
        spdLabel.setText(String.format("%.0f", player.getStatus().getSpd()));
    }
}
