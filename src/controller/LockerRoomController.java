package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

import static controller.ResourceController.player;

/**
 * FXML Controller class for Locker Room Scene
 * This class check for battle and back button event
 *
 * @author Pakanon Pantisawat
 */
public class LockerRoomController {

    @FXML
    private AnchorPane lockerPane;

    @FXML
    private ProgressBar expBar;

    @FXML
    private ProgressBar hpBar;

    @FXML
    private ProgressBar atkBar;

    @FXML
    private ProgressBar defBar;

    @FXML
    private ProgressBar spdBar;

    @FXML
    private Text nameText;

    @FXML
    private Text levelText;

    @FXML
    private Text goldText;

    @FXML
    private Text expCriteriaText;

    @FXML
    private Text expCurrentText;

    @FXML
    private Text hpText;

    @FXML
    private Text atkText;

    @FXML
    private Text defText;

    @FXML
    private Text spdText;

    @FXML
    private Button battleButton;

    @FXML
    private Button backButton;

    @FXML
    private ImageView imageView;

    @FXML
    void initialize() {
        imageView.setImage(player.getImage(false));
        nameText.setText(player.getName());
        levelText.setText(String.valueOf(player.getLevel()));
        goldText.setText(String.valueOf(player.getGold()));
        expCurrentText.setText(String.format("%d", player.getExp()));
        expCriteriaText.setText(String.format("/%d", player.getCriteria()));
        expBar.setProgress(player.getExp() / player.getCriteria());
        hpText.setText(String.format("%.0f", player.getStatus().getHp()));
        hpBar.setProgress(player.getStatus().getHp() / 1000);
        atkText.setText(String.format("%.0f", player.getStatus().getAtk()));
        atkBar.setProgress(player.getStatus().getAtk() / 250);
        defText.setText(String.format("%.0f", player.getStatus().getDef()));
        defBar.setProgress(player.getStatus().getDef() / 150);
        spdText.setText(String.format("%.0f", player.getStatus().getSpd()));
        spdBar.setProgress(player.getStatus().getSpd() / 120);
    }

    @FXML
    void buttonEvent(ActionEvent event) throws IOException {
        URL url = null;
        if (event.getSource().equals(battleButton)) {
            url = this.getClass().getClassLoader().getResource("fxml/BattleChoice.fxml");
        } else if (event.getSource().equals(backButton)) {
            url = this.getClass().getClassLoader().getResource("fxml/LoadGame.fxml");
            player = null;
        }
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        lockerPane.getChildren().setAll(pane);
    }
}
