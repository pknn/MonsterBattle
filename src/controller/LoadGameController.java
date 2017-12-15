package controller;

import component.Monster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import static controller.ResourceController.gh;
import static controller.ResourceController.player;

/**
 * FXML Controller class for Load game Scene
 * This class check for available slot or empty slot
 * and shift direction between locker room and create character scene
 *
 * @author Pakanon Pantisawat
 */

public class LoadGameController {

    private Monster[] monsters;

    @FXML
    private AnchorPane loadGamePane;

    @FXML
    private ImageView imageSlot1;

    @FXML
    private ImageView imageSlot2;

    @FXML
    private ImageView imageSlot3;

    @FXML
    private Button buttonSlot1;

    @FXML
    private Button buttonSlot2;

    @FXML
    private Button buttonSlot3;

    @FXML
    private Label nameSlot1;

    @FXML
    private Label nameSlot2;

    @FXML
    private Label nameSlot3;

    @FXML
    private Label typeSlot1;

    @FXML
    private Label typeSlot2;

    @FXML
    private Label typeSlot3;

    @FXML
    private Label goldSlot1;

    @FXML
    private Label goldSlot2;

    @FXML
    private Label goldSlot3;

    @FXML
    private Label levelSlot1;

    @FXML
    private Label levelSlot2;

    @FXML
    private Label levelSlot3;

    @FXML
    private Button deleteSlot1;

    @FXML
    private Button deleteSlot2;

    @FXML
    private Button deleteSlot3;

    @FXML
    private Label labelSlot1;

    @FXML
    private Label labelSlot2;

    @FXML
    private Label labelSlot3;

    @FXML
    private void initialize() {
        update();
    }

    @FXML
    void slotButtonEvent(ActionEvent event) throws IOException {
        int slot = 0;
        if (event.getSource().equals(buttonSlot1)) slot = 1;
        else if (event.getSource().equals(buttonSlot2)) slot = 2;
        else if (event.getSource().equals(buttonSlot3)) slot = 3;

        URL url;
        if (monsters.length >= slot) {
            player = monsters[slot - 1];
            url = this.getClass().getClassLoader().getResource("fxml/LockerRoom.fxml");
        } else {
            url = this.getClass().getClassLoader().getResource("fxml/CreateCharacter.fxml");
        }
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        loadGamePane.getChildren().setAll(pane);
    }

    @FXML
    void deleteButtonEvent(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Save Confirmation");
        alert.setHeaderText("Delete comfirmation");
        alert.setContentText("This is irreverable process. Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.CANCEL) return;

        if (event.getSource().equals(deleteSlot1)) gh.deleteSave(monsters[0]);
        else if (event.getSource().equals(deleteSlot2)) gh.deleteSave(monsters[1]);
        else gh.deleteSave(monsters[2]);
        update();
    }

    @FXML
    void backButtonEvent() throws IOException {
        URL url = this.getClass().getClassLoader().getResource("fxml/Start.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        loadGamePane.getChildren().setAll(pane);
    }

    private void update() {
        clear();
        gh.loadSave();
        monsters = gh.getMonsters();
        if (monsters.length > 0) {
            imageSlot1.setImage(monsters[0].getImage(false));
            nameSlot1.setText(monsters[0].getName());
            typeSlot1.setText(monsters[0].getType().toString());
            goldSlot1.setText(String.valueOf(monsters[0].getGold()));
            levelSlot1.setText(String.valueOf(monsters[0].getLevel()));
            labelSlot1.setText("LV");
            deleteSlot1.setDisable(false);
        }
        if (monsters.length > 1) {
            imageSlot2.setImage(monsters[1].getImage(false));
            nameSlot2.setText(monsters[1].getName());
            typeSlot2.setText(monsters[1].getType().toString());
            goldSlot2.setText(String.valueOf(monsters[1].getGold()));
            levelSlot2.setText(String.valueOf(monsters[1].getLevel()));
            labelSlot2.setText("LV");
            deleteSlot2.setDisable(false);
        }
        if (monsters.length > 2) {
            imageSlot3.setImage(monsters[2].getImage(false));
            nameSlot3.setText(monsters[2].getName());
            typeSlot3.setText(monsters[2].getType().toString());
            goldSlot3.setText(String.valueOf(monsters[2].getGold()));
            levelSlot3.setText(String.valueOf(monsters[2].getLevel()));
            labelSlot3.setText("LV");
            deleteSlot3.setDisable(false);
        }
    }

    private void clear() {
        imageSlot1.setImage(null);
        nameSlot1.setText("EMPTY");
        typeSlot1.setText("Create");
        goldSlot1.setText(null);
        levelSlot1.setText(null);
        deleteSlot1.setDisable(true);
        labelSlot1.setText(null);

        imageSlot2.setImage(null);
        nameSlot2.setText("EMPTY");
        typeSlot2.setText("Create");
        goldSlot2.setText(null);
        levelSlot2.setText(null);
        deleteSlot2.setDisable(true);
        labelSlot2.setText(null);

        imageSlot3.setImage(null);
        nameSlot3.setText("EMPTY");
        typeSlot3.setText("Create");
        goldSlot3.setText(null);
        levelSlot3.setText(null);
        deleteSlot3.setDisable(true);
        labelSlot3.setText(null);
    }
}
