package controller;

import component.Monster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

import static controller.ResourceController.*;

/**
 * FXML Controller Class for Battle Choice Scene
 *
 * @author Pakanon Pantisawat
 */
public class BattleChoiceController {
    private Monster[] opponents;

    @FXML
    private AnchorPane battleChoicePane;

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
    private Label expSlot1;

    @FXML
    private Label expSlot2;

    @FXML
    private Label expSlot3;

    @FXML
    void initialize() {
        opponents = gh.getOpponent(player.getLevel());

        nameSlot1.setText(opponents[0].getName());
        typeSlot1.setText(String.valueOf(opponents[0].getType()));
        goldSlot1.setText(String.valueOf(opponents[0].getGold()));
        expSlot1.setText(String.valueOf(opponents[0].getLevel()));
        imageSlot1.setImage(opponents[0].getImage(false));

        nameSlot2.setText(opponents[1].getName());
        typeSlot2.setText(String.valueOf(opponents[1].getType()));
        goldSlot2.setText(String.valueOf(opponents[1].getGold()));
        expSlot2.setText(String.valueOf(opponents[1].getLevel()));
        imageSlot2.setImage(opponents[1].getImage(false));

        nameSlot3.setText(opponents[2].getName());
        typeSlot3.setText(String.valueOf(opponents[2].getType()));
        goldSlot3.setText(String.valueOf(opponents[2].getGold()));
        expSlot3.setText(String.valueOf(opponents[2].getLevel()));
        imageSlot3.setImage(opponents[2].getImage(false));
    }

    @FXML
    void slotButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(buttonSlot1)) {

            opponent = opponents[0];
        } else if (event.getSource().equals(buttonSlot2)) {
            opponent = opponents[1];
        } else if (event.getSource().equals(buttonSlot3)) {
            opponent = opponents[2];
        }
        URL url = this.getClass().getClassLoader().getResource("fxml/Battle.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        battleChoicePane.getChildren().setAll(pane);
    }

    @FXML
    void backButtonEvent() throws IOException {
        URL url = this.getClass().getClassLoader().getResource("fxml/LockerRoom.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        battleChoicePane.getChildren().setAll(pane);
    }

}
