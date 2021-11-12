package sample;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.core.Logger;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Controller {

    Check checker;
    Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @FXML public AnchorPane menuPane;
    @FXML public AnchorPane gamePane;
    @FXML public AnchorPane pointsPane;
    @FXML public AnchorPane settingsPane;
    @FXML public ImageView dice1IV;
    @FXML public ImageView dice2IV;
    @FXML public ImageView dice3IV;
    @FXML public ImageView dice4IV;
    @FXML public ImageView dice5IV;
    @FXML public ImageView selectDice1;
    @FXML public ImageView selectDice2;
    @FXML public ImageView selectDice3;
    @FXML public ImageView selectDice4;
    @FXML public ImageView selectDice5;
    @FXML public Label remainingLabel;
    @FXML public Label remainingTurnsLabel;
    @FXML public Label p0;
    @FXML public Label p1;
    @FXML public Label p2;
    @FXML public Label p3;
    @FXML public Label p4;
    @FXML public Label p5;
    @FXML public Label p6;
    @FXML public Label p7;
    @FXML public Label p8;
    @FXML public Label p9;
    @FXML public Label p10;
    @FXML public Label p11;
    @FXML public Label p12;
    @FXML public Label p13;
    @FXML public Label p14;
    @FXML public Label p15;
    @FXML public Label p16;
    @FXML public ImageView rolledDice1;
    @FXML public ImageView rolledDice2;
    @FXML public ImageView rolledDice3;
    @FXML public ImageView rolledDice4;
    @FXML public ImageView rolledDice5;
    @FXML public ImageView pointsButton;
    @FXML public Label specLabel1;
    @FXML public Label specLabel2;

    public int remaining;
    public int remainingTurns;
    public int[] rolls;
    public boolean isMuted = false;

    public boolean hasDecided = false;
    public boolean select1Bool = true;
    public boolean select2Bool = true;
    public boolean select3Bool = true;
    public boolean select4Bool = true;
    public boolean select5Bool = true;
    public int dice1num;
    public int dice2num;
    public int dice3num;
    public int dice4num;
    public int dice5num;

    Image dice0Image = new Image(MiscFilePath.DICE.getFilePath());
    Image dice1Image = new Image(MiscFilePath.DICE1.getFilePath());
    Image dice2Image = new Image(MiscFilePath.DICE2.getFilePath());
    Image dice3Image = new Image(MiscFilePath.DICE3.getFilePath());
    Image dice4Image = new Image(MiscFilePath.DICE4.getFilePath());
    Image dice5Image = new Image(MiscFilePath.DICE5.getFilePath());
    Image dice6Image = new Image(MiscFilePath.DICE6.getFilePath());
    Image skipImage = new Image(MiscFilePath.SKIP.getFilePath());
    Image backImage = new Image(MiscFilePath.BACK.getFilePath());
    Image continueImage = new Image(MiscFilePath.CONTINUE.getFilePath());


    public Controller() {

    }

    public void startClient() {
        checker = new Check(this);
        remainingTurns = 13;
        switchScene(Scene.MENU);
        setAllToUnselected();
        rolls = new int[5];
    }

    public void windowClosed() {
        System.exit(1);
    }

    public void setAllSelectsToTrue() {
        select1Bool = true;
        select2Bool = true;
        select3Bool = true;
        select4Bool = true;
        select5Bool = true;
    }

    public void setAllToUnselected() {
        selectDice1.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        selectDice2.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        selectDice3.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        selectDice4.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        selectDice5.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
    }

    public void switchScene(Scene scene) {
        menuPane.setVisible(false);
        gamePane.setVisible(false);
        pointsPane.setVisible(false);
        settingsPane.setVisible(false);
        switch (scene) {
            case MENU -> menuPane.setVisible(true);
            case GAME -> gamePane.setVisible(true);
            case POINTS -> pointsPane.setVisible(true);
            case SETTINGS -> settingsPane.setVisible(true);
        }
    }

    @FXML
    public void rollAll() {
        if (remainingTurns == 0) return;
        if (remaining == 0) {
            return;
        }
        remaining--;
        remainingLabel.setText("Remaining: " + remaining);

        Random rdm = new Random();

        int dice1 = rdm.nextInt(6) + 1;
        dice1num = dice1;
        diceDecider(1, dice1);
        int dice2 = rdm.nextInt(6) + 1;
        dice2num = dice2;
        diceDecider(2, dice2);
        int dice3 = rdm.nextInt(6) + 1;
        dice3num = dice3;
        diceDecider(3, dice3);
        int dice4 = rdm.nextInt(6) + 1;
        dice4num = dice4;
        diceDecider(4, dice4);
        int dice5 = rdm.nextInt(6) + 1;
        dice5num = dice5;
        diceDecider(5, dice5);
    }

    @FXML
    public void rollSelected() {
        if (remainingTurns == 0) return;
        if (remaining == 0) {
            return;
        } else if (remaining == 3) {
            System.out.println("Gotta roll first");
            return;
        }
        remaining--;
        remainingLabel.setText("Remaining: " + remaining);
        Random rdm = new Random();

        if (!select1Bool) {
            int dice1 = rdm.nextInt(6) + 1;
            dice1num = dice1;
            diceDecider(1, dice1);
        }
        if (!select2Bool) {
            int dice2 = rdm.nextInt(6) + 1;
            dice2num = dice2;
            diceDecider(2, dice2);
        }
        if (!select3Bool) {
            int dice3 = rdm.nextInt(6) + 1;
            dice3num = dice3;
            diceDecider(3, dice3);
        }
        if (!select4Bool) {
            int dice4 = rdm.nextInt(6) + 1;
            dice4num = dice4;
            diceDecider(4, dice4);
        }
        if (!select5Bool) {
            int dice5 = rdm.nextInt(6) + 1;
            dice5num = dice5;
            diceDecider(5, dice5);
        }
    }

    public void diceDecider(int dice, int number) {
        switch (dice) {
            case 1 -> numberDecider(number, dice1IV);
            case 2 -> numberDecider(number, dice2IV);
            case 3 -> numberDecider(number, dice3IV);
            case 4 -> numberDecider(number, dice4IV);
            case 5 -> numberDecider(number, dice5IV);
        }
    }

    private void numberDecider(int number, ImageView diceImage) {
        switch (number) {
            case 0 -> diceImage.setImage(dice0Image);
            case 1 -> diceImage.setImage(dice1Image);
            case 2 -> diceImage.setImage(dice2Image);
            case 3 -> diceImage.setImage(dice3Image);
            case 4 -> diceImage.setImage(dice4Image);
            case 5 -> diceImage.setImage(dice5Image);
            case 6 -> diceImage.setImage(dice6Image);
        }
    }

    public void resetDices() {
        for (int i = 1; i < 7; i++) {
            diceDecider(i, 0);
        }
        remaining = 3;
        remainingLabel.setText("Remaining: 3");
    }

    @FXML
    public void menuPlayClicked() {
        playButtonClickedSound();
        switchScene(Scene.GAME);
        remaining = 3;
        remainingTurns = 13;
        remainingTurnsLabel.setText("Remaining Turns: 13");
        setAllToUnselected();
        rolledDice1.setImage(dice0Image);
        rolledDice2.setImage(dice0Image);
        rolledDice3.setImage(dice0Image);
        rolledDice4.setImage(dice0Image);
        rolledDice5.setImage(dice0Image);
        for (int i = 0; i < 5; i++) {
            rolls[i] = 0;
        }
        for (int i = 0; i < 17; i++) {
            updateLabel(i, 0);
        }

    }

    @FXML
    public void menuSettingsClicked() {
        switchScene(Scene.SETTINGS);
    }

    @FXML
    public void menuQuitClicked() {
        System.exit(0);
    }

    @FXML
    public void settingsFullscreenPressed() {
        Stage stage = (Stage) settingsPane.getScene().getWindow();
        Screen thisScreen = null;
        for (Screen screen : Screen.getScreensForRectangle(stage.getX() + stage.getWidth() / 2, stage.getY() + stage.getHeight() / 2, 1, 1)) {
            thisScreen = screen;
        }
        Rectangle2D screenRec = thisScreen.getBounds();
        Scale scale;
        if (stage.isFullScreen()) {
            scale = new Scale(1, 1);
        } else {
            scale = new Scale(screenRec.getWidth() / 1280, screenRec.getHeight() / 720);
        }
        settingsPane.getScene().getRoot().getTransforms().setAll(scale);
        stage.setFullScreen(!stage.isFullScreen());
    }

    @FXML
    public void settingsMuteSoundsPressed() {
        isMuted = !isMuted;
    }

    @FXML
    public void settingsCreditsPressed() {
        //TODO
    }

    @FXML
    public void settingsBackPressed() {
        switchScene(Scene.MENU);
    }

    @FXML
    public void selectDice1Clicked() {
        select1Bool = !select1Bool;
        if (select1Bool) {
            selectDice1.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        } else {
            selectDice1.setImage(new Image(MiscFilePath.SELECTED.getFilePath()));
        }
    }

    @FXML
    public void selectDice2Clicked() {
        select2Bool = !select2Bool;
        if (select2Bool) {
            selectDice2.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        } else {
            selectDice2.setImage(new Image(MiscFilePath.SELECTED.getFilePath()));
        }
    }

    @FXML
    public void selectDice3Clicked() {
        select3Bool = !select3Bool;
        if (select3Bool) {
            selectDice3.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        } else {
            selectDice3.setImage(new Image(MiscFilePath.SELECTED.getFilePath()));
        }
    }

    @FXML
    public void selectDice4Clicked() {
        select4Bool = !select4Bool;
        if (select4Bool) {
            selectDice4.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        } else {
            selectDice4.setImage(new Image(MiscFilePath.SELECTED.getFilePath()));
        }
    }

    @FXML
    public void selectDice5Clicked() {
        select5Bool = !select5Bool;
        if (select5Bool) {
            selectDice5.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        } else {
            selectDice5.setImage(new Image(MiscFilePath.SELECTED.getFilePath()));
        }
    }

    @FXML
    public void finishRolling() {
        if (remainingTurns == 0) return;
        if (remaining == 3) {
            System.out.println("Please roll first!");
            return;
        }

        rolls[0] = dice1num;
        rolls[1] = dice2num;
        rolls[2] = dice3num;
        rolls[3] = dice4num;
        rolls[4] = dice5num;
        Arrays.sort(rolls);
        switchScene(Scene.POINTS);
        rolledDice1.setImage(eyesReturner(rolls[0]));
        rolledDice2.setImage(eyesReturner(rolls[1]));
        rolledDice3.setImage(eyesReturner(rolls[2]));
        rolledDice4.setImage(eyesReturner(rolls[3]));
        rolledDice5.setImage(eyesReturner(rolls[4]));
        pointsButton.setImage(skipImage);
        hasDecided = false;
        System.out.println(Arrays.toString(rolls));
    }

    @FXML
    public void showPoints() {
        switchScene(Scene.POINTS);
        rolledDice1.setImage(eyesReturner(dice1num));
        rolledDice2.setImage(eyesReturner(dice2num));
        rolledDice3.setImage(eyesReturner(dice3num));
        rolledDice4.setImage(eyesReturner(dice4num));
        rolledDice5.setImage(eyesReturner(dice5num));
        pointsButton.setImage(backImage);
        checker.specPointsTrue();
    }

    @FXML
    public void pointsSkipButtonPressed() {
        if (checker.getSpecPoints()) {
            switchScene(Scene.GAME);
            checker.specPointsFalse();
        } else {
            switchScene(Scene.GAME);
            setAllToUnselected();
            setAllSelectsToTrue();
            resetDices();
            remaining = 3;
            hasDecided = true;
            decreaseRemainingTurns();
        }
    }

    public Image eyesReturner(int num) {
        return switch (num) {
            case 0 -> dice0Image;
            case 1 -> dice1Image;
            case 2 -> dice2Image;
            case 3 -> dice3Image;
            case 4 -> dice4Image;
            case 5 -> dice5Image;
            case 6 -> dice6Image;
            default -> null;
        };
    }

    @FXML
    public void onMouseEntered(MouseEvent mouseEvent) {
        playButtonHoverSound();
        ImageView src = (ImageView) mouseEvent.getSource();
        src.setEffect(new DropShadow());
    }

    @FXML
    public void onMouseExited(MouseEvent mouseEvent) {
        ImageView src = (ImageView) mouseEvent.getSource();
        src.setEffect(null);
    }

    @FXML
    public void onMousePressed(MouseEvent mouseEvent) {
        ImageView src = (ImageView) mouseEvent.getSource();
        src.setEffect(new InnerShadow());
    }

    @FXML
    public void onMouseReleased(MouseEvent mouseEvent) {
        ImageView src = (ImageView) mouseEvent.getSource();
        src.setEffect(new DropShadow());
    }

    @FXML
    public void onMouseEnteredLabel(MouseEvent mouseEvent) {
        Label src = (Label) mouseEvent.getSource();
        src.setEffect(new DropShadow());
    }

    @FXML
    public void onMouseExitedLabel(MouseEvent mouseEvent) {
        Label src = (Label) mouseEvent.getSource();
        src.setEffect(null);
    }

    @FXML
    public void p0Pressed() {
        if (hasDecided) return;
        int score = checker.checkp0(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(0, score);
        }

    }

    @FXML
    public void p1Pressed() {
        if (hasDecided) return;
        int score = checker.checkp1(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(1, score);
        }
    }

    @FXML
    public void p2Pressed() {
        if (hasDecided) return;
        int score = checker.checkp2(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(2, score);
        }
    }

    @FXML
    public void p3Pressed() {
        if (hasDecided) return;
        int score = checker.checkp3(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(3, score);
        }
    }

    @FXML
    public void p4Pressed() {
        if (hasDecided) return;
        int score = checker.checkp4(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(4, score);
        }
    }

    @FXML
    public void p5Pressed() {
        if (hasDecided) return;
        int score = checker.checkp5(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(5, score);
        }
    }

    public void update6(int num) {
        p6.setText(Integer.toString(num));
    }

    @FXML
    public void p7Pressed() {
        if (hasDecided) return;
        int score = checker.checkp7(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(7, score);
        }
    }

    @FXML
    public void p8Pressed() {
        if (hasDecided) return;
        int score = checker.checkp8(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(8, score);
        }
    }

    @FXML
    public void p9Pressed() {
        if (hasDecided) return;
        int score = checker.checkp9(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(9, score);
        }
    }

    @FXML
    public void p10Pressed() {
        if (hasDecided) return;
        int score = checker.checkp10(rolls);
        if (score == -1) log.info("Not matching");
        else  {
            enclosureTurn(10, score);
        }
    }

    @FXML
    public void p11Pressed() {
        if (hasDecided) return;
        int score = checker.checkp11(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(11, score);
        }
    }

    @FXML
    public void p12Pressed() {
        if (hasDecided) return;
        int score = checker.checkp12(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(12, score);
        }
    }

    public void update13(int num) {
        p13.setText(Integer.toString(num));
    }

    @FXML
    public void p14Pressed() {
        if (hasDecided) return;
        int score = checker.checkp14(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(14, score);
        }
    }

    public void update15(int num) {
        p15.setText(Integer.toString(num));
    }

    public void update16(int num) {
        p16.setText(Integer.toString(num));
    }

    public void decreaseRemainingTurns() {
        remainingTurns--;
        remainingTurnsLabel.setText("Remaining Turns: " + remainingTurns);
    }

    public void enclosureTurn(int num, int score) {
        pointsButton.setImage(continueImage);
        hasDecided = true;
        updateLabel(num, score);
    }

    public void updateLabel(int num, int score) {
        switch (num) {
            case 0 -> p0.setText(Integer.toString(score));
            case 1 -> p1.setText(Integer.toString(score));
            case 2 -> p2.setText(Integer.toString(score));
            case 3 -> p3.setText(Integer.toString(score));
            case 4 -> p4.setText(Integer.toString(score));
            case 5 -> p5.setText(Integer.toString(score));
            case 6 -> p6.setText(Integer.toString(score));
            case 7 -> p7.setText(Integer.toString(score));
            case 8 -> p8.setText(Integer.toString(score));
            case 9 -> p9.setText(Integer.toString(score));
            case 10 -> p10.setText(Integer.toString(score));
            case 11 -> p11.setText(Integer.toString(score));
            case 12 -> p12.setText(Integer.toString(score));
            case 13 -> p13.setText(Integer.toString(score));
            case 14 -> p14.setText(Integer.toString(score));
            case 15 -> p15.setText(Integer.toString(score));
            case 16 -> p16.setText(Integer.toString(score));
        }
    }

    @FXML
    public void exitButtonPressed() {
        switchScene(Scene.MENU);
        checker.setAllValuesToZero();
    }

    public void playButtonHoverSound() {
        /*try {
            Media m = new Media(getClass().getResource(MiscFilePath.BUTTON_HOVER.getFilePath()).toString());
            MediaPlayer mediaPlayerButtonHover = new MediaPlayer(m);
            if (isMuted) {
                mediaPlayerButtonHover.setVolume(0);
            } else {
                mediaPlayerButtonHover.setVolume(1);
            }
            mediaPlayerButtonHover.play();
        } catch (Exception e) {
            log.error(e.getMessage());
        }*/
    }

    public void playButtonClickedSound() {
        /*Media h =
                new Media(getClass().getResource(MiscFilePath.BUTTON_PRESSED.getFilePath()).toString());
        MediaPlayer mediaPlayerButtonClicked = new MediaPlayer(h);
        if (isMuted) {
            mediaPlayerButtonClicked.setVolume(0);
        } else {
            mediaPlayerButtonClicked.setVolume(1.0);
        }
        mediaPlayerButtonClicked.play();*/
    }
}
