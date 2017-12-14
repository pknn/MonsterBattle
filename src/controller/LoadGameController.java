package controller;

import component.Monster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class LoadGameController extends ResourceController {

    private Monster[] players;

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
        if (players.length >= slot) {
            player = players[slot - 1];
            url = this.getClass().getClassLoader().getResource("fxml/LockerRoom.fxml");
        } else {
            url = this.getClass().getClassLoader().getResource("fxml/CreateCharacter.fxml");
        }
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

        if (event.getSource().equals(deleteSlot1)) gh.deleteSave(players[0]);
        else if (event.getSource().equals(deleteSlot2)) gh.deleteSave(players[1]);
        else gh.deleteSave(players[2]);
        update();
    }

    @FXML
    void backButtonEvent() throws IOException {
        URL url = this.getClass().getClassLoader().getResource("fxml/Start.fxml");
        AnchorPane pane = FXMLLoader.load(url);
        loadGamePane.getChildren().setAll(pane);
    }

    private void update() {
        clear();
        gh.loadSave();
        players = gh.getMonsters();
        if (players.length > 0) {
            imageSlot1.setImage(players[0].getImage(false));
            nameSlot1.setText(players[0].getName());
            typeSlot1.setText(players[0].getType().toString());
            goldSlot1.setText(String.valueOf(players[0].getGold()));
            levelSlot1.setText(String.valueOf(players[0].getLevel()));
            labelSlot1.setText("LV");
            deleteSlot1.setDisable(false);
        }
        if (players.length > 1) {
            imageSlot2.setImage(players[1].getImage(false));
            nameSlot2.setText(players[1].getName());
            typeSlot2.setText(players[1].getType().toString());
            goldSlot2.setText(String.valueOf(players[1].getGold()));
            levelSlot2.setText(String.valueOf(players[1].getLevel()));
            labelSlot2.setText("LV");
            deleteSlot2.setDisable(false);
        }
        if (players.length > 2) {
            imageSlot3.setImage(players[2].getImage(false));
            nameSlot3.setText(players[2].getName());
            typeSlot3.setText(players[2].getType().toString());
            goldSlot3.setText(String.valueOf(players[2].getGold()));
            levelSlot3.setText(String.valueOf(players[2].getLevel()));
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
