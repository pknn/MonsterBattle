package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

import static controller.ResourceController.gh;
import static controller.ResourceController.opponent;
import static controller.ResourceController.player;

public class BattleSummaryController {

    @FXML
    private AnchorPane battleSummaryPane;

    @FXML
    private Text statusText;

    @FXML
    private Text goldRewardText;

    @FXML
    private Text expRewardText;

    @FXML
    private Text specialText;

    @FXML
    void initialize() {
        int reward = 0;
        int exp = 0;
        if (player.isDead()) {
            statusText.setText("YOU LOSE");
            exp = opponent.getLevel() * 2;
        } else if (opponent.isDead()) {
            statusText.setText("YOU WIN");
            reward = opponent.getGold();
            exp = opponent.getLevel() * 5;
        } else {
            statusText.setText("YOU'VE RAN AWAY");
        }

        if (player.plusExp(exp) && player.getLevel() >= 7) {
            specialText.setText("Level up & Skill unlocked!!!!");
        } else if (player.plusExp(exp)) {
            specialText.setText("Level up!!!");
        } else specialText.setText(null);
        player.increaseGold(reward);

        goldRewardText.setText(String.valueOf(reward));
        expRewardText.setText(String.valueOf(exp));
    }

    @FXML
    void continueButtonEvent() throws IOException {
        player.reset();
        gh.addSave(player);
        URL url = this.getClass().getClassLoader().getResource("fxml/LockerRoom.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        battleSummaryPane.getChildren().setAll(pane);
    }
}
