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
    @FXML public AnchorPane guidePane;
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
    @FXML public ImageView guideIV;

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

    /**
     * Images for the dice and the buttons and the guides
     */
    //SURFACE OF DICE
    Image dice0Image = new Image(MiscFilePath.DICE.getFilePath());
    Image dice1Image = new Image(MiscFilePath.DICE1.getFilePath());
    Image dice2Image = new Image(MiscFilePath.DICE2.getFilePath());
    Image dice3Image = new Image(MiscFilePath.DICE3.getFilePath());
    Image dice4Image = new Image(MiscFilePath.DICE4.getFilePath());
    Image dice5Image = new Image(MiscFilePath.DICE5.getFilePath());
    Image dice6Image = new Image(MiscFilePath.DICE6.getFilePath());

    //BUTTONS
    Image skipImage = new Image(MiscFilePath.SKIP.getFilePath());
    Image backImage = new Image(MiscFilePath.BACK.getFilePath());
    Image continueImage = new Image(MiscFilePath.CONTINUE.getFilePath());

    //GUIDES
    Image acesGuide = new Image(MiscFilePath.ACES.getFilePath());
    Image twosGuide = new Image(MiscFilePath.TWOS.getFilePath());
    Image threesGuide = new Image(MiscFilePath.THREES.getFilePath());
    Image foursGuide = new Image(MiscFilePath.FOURS.getFilePath());
    Image fivesGuide = new Image(MiscFilePath.FIVES.getFilePath());
    Image sixesGuide = new Image(MiscFilePath.SIXES.getFilePath());
    Image threeofakindGuide = new Image(MiscFilePath.THREE_OAK.getFilePath());
    Image fourofakindGuide = new Image(MiscFilePath.FOUR_OAK.getFilePath());
    Image fullhouseGuide = new Image(MiscFilePath.FULL_HOUSE.getFilePath());
    Image smallstraightGuide = new Image(MiscFilePath.SMALL_STRAIGHT.getFilePath());
    Image largestraightGuide = new Image(MiscFilePath.LARGE_STRAIGHT.getFilePath());
    Image kniffelGuide = new Image(MiscFilePath.KNIFFEL.getFilePath());
    Image chanceGuide = new Image(MiscFilePath.CHANCE.getFilePath());
    Image upperBonusGuide = new Image(MiscFilePath.UPPER_BONUS.getFilePath());


    public Controller() {

    }

    /**
     * Sets up some important things for the game
     */
    public void startClient() {
        checker = new Check(this);
        remainingTurns = 13;
        switchScene(Scene.MENU);
        setAllToUnselected();
        rolls = new int[5];
    }

    /**
     * When the window gets closed
     */
    public void windowClosed() {
        System.exit(1);
    }

    /**
     * Selects all dices
     */
    public void setAllSelectsToTrue() {
        select1Bool = true;
        select2Bool = true;
        select3Bool = true;
        select4Bool = true;
        select5Bool = true;
    }

    /**
     * Resets the select boxes for the dices and puts empty box pictures under the dices
     */
    public void setAllToUnselected() {
        selectDice1.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        selectDice2.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        selectDice3.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        selectDice4.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        selectDice5.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
    }

    /**
     * method to switch between anchorpane scenes
     * @param scene scene to be switched to
     */
    public void switchScene(Scene scene) {
        menuPane.setVisible(false);
        gamePane.setVisible(false);
        pointsPane.setVisible(false);
        settingsPane.setVisible(false);
        guidePane.setVisible(false);
        switch (scene) {
            case MENU -> menuPane.setVisible(true);
            case GAME -> gamePane.setVisible(true);
            case POINTS -> pointsPane.setVisible(true);
            case SETTINGS -> settingsPane.setVisible(true);
            case GUIDE -> guidePane.setVisible(true);
        }
    }

    /**
     * When the "Roll all" button gets hit the method gets called.
     * It gives all 5 dices a random int from 1 to 6.
     */
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

    /**
     * Looks which dice is selected and rolls the selected ones
     */
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

    /**
     * Gets a dice and an eye number. Calls another method "numberDecider" based on the dice it gets
     * @param dice 1...5
     * @param number 1...6
     */
    public void diceDecider(int dice, int number) {
        switch (dice) {
            case 1 -> numberDecider(number, dice1IV);
            case 2 -> numberDecider(number, dice2IV);
            case 3 -> numberDecider(number, dice3IV);
            case 4 -> numberDecider(number, dice4IV);
            case 5 -> numberDecider(number, dice5IV);
        }
    }

    /**
     * Gets an eye number and an ImageView
     * @param number eye number 1...6
     * @param diceImage The ImageView so the controller knows where to display the eye number
     */
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

    /**
     * gives every dice the "kniffel" image.
     */
    public void resetDices() {
        for (int i = 1; i < 7; i++) {
            diceDecider(i, 0);
        }
        remaining = 3;
        remainingLabel.setText("Remaining: 3");
    }

    /**
     * Sets up the game / Resets it
     * Gets called when the player clicks play in the main menu
     */
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

    /**
     * Switches to the settingspane
     */
    @FXML
    public void menuSettingsClicked() {
        switchScene(Scene.SETTINGS);
    }

    /**
     * Closes the game
     */
    @FXML
    public void menuQuitClicked() {
        System.exit(0);
    }

    /**
     * Gets called when the fullscreen button in the settings gets pressed
     * Checks if the game is already in fullscreen.
     * Switches between fullscreen and windowed mode
     */
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

    /**
     * Mutes the sound which are yet to come
     */
    @FXML
    public void settingsMuteSoundsPressed() {
        isMuted = !isMuted;
    }

    /**
     * Switches to the credits pane
     */
    @FXML
    public void settingsCreditsPressed() {
        //TODO
    }

    /**
     * Returns from the settings to the menu
     */
    @FXML
    public void settingsBackPressed() {
        switchScene(Scene.MENU);
    }

    /**
     * When the box under dice 1 gets clicked it changes to selected or unselected based on the pre-status
     */
    @FXML
    public void selectDice1Clicked() {
        select1Bool = !select1Bool;
        if (select1Bool) {
            selectDice1.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        } else {
            selectDice1.setImage(new Image(MiscFilePath.SELECTED.getFilePath()));
        }
    }

    /**
     * When the box under dice 2 gets clicked it changes to selected or unselected based on the pre-status
     */
    @FXML
    public void selectDice2Clicked() {
        select2Bool = !select2Bool;
        if (select2Bool) {
            selectDice2.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        } else {
            selectDice2.setImage(new Image(MiscFilePath.SELECTED.getFilePath()));
        }
    }

    /**
     * When the box under dice 3 gets clicked it changes to selected or unselected based on the pre-status
     */
    @FXML
    public void selectDice3Clicked() {
        select3Bool = !select3Bool;
        if (select3Bool) {
            selectDice3.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        } else {
            selectDice3.setImage(new Image(MiscFilePath.SELECTED.getFilePath()));
        }
    }

    /**
     * When the box under dice 4 gets clicked it changes to selected or unselected based on the pre-status
     */
    @FXML
    public void selectDice4Clicked() {
        select4Bool = !select4Bool;
        if (select4Bool) {
            selectDice4.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        } else {
            selectDice4.setImage(new Image(MiscFilePath.SELECTED.getFilePath()));
        }
    }

    /**
     * When the box under dice 5 gets clicked it changes to selected or unselected based on the pre-status
     */
    @FXML
    public void selectDice5Clicked() {
        select5Bool = !select5Bool;
        if (select5Bool) {
            selectDice5.setImage(new Image(MiscFilePath.UNSELECTED.getFilePath()));
        } else {
            selectDice5.setImage(new Image(MiscFilePath.SELECTED.getFilePath()));
        }
    }

    /**
     * Gets called when the player finished his turn
     * Sorts the eye numbers and switches to the pointsheet
     */
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

    /**
     * The player has the ability to show his points while he is still in a turn in order to see which fields are still open.
     * The current rolls are getting displayed there too.
     */
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

    /**
     * When the player is not happy with his roll, he can skip it and no action will be done except he loses a turn.
     */
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

    /**
     * Returns an Image with either the "Kniffel image" or the image with the according eye number
     * @param num 0 -> Kniffel image | 1...6 image with the eye number
     * @return returns an image of the face value
     */
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

    /**
     * Effect when the player hovers over a button
     */
    @FXML
    public void onMouseEntered(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView src) {
            src.setEffect(new DropShadow());
        }
        if (mouseEvent.getSource() instanceof Label src) {
            src.setEffect(new DropShadow());
        }
    }

    /**
     * Effect when the player hovers off a button
     */
    @FXML
    public void onMouseExited(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView src) {
            src.setEffect(null);
        }
        if (mouseEvent.getSource() instanceof Label src) {
            src.setEffect(null);
        }
    }

    /**
     * Effect when the player presses a button
     */
    @FXML
    public void onMousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView src) {
            src.setEffect(new InnerShadow());
        }
        if (mouseEvent.getSource() instanceof Label src) {
            src.setEffect(new InnerShadow());
        }
    }

    /**
     * Effect when the player releases a button
     */
    @FXML
    public void onMouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView src) {
            src.setEffect(null);
        }
        if (mouseEvent.getSource() instanceof Label src) {
            src.setEffect(null);
        }
    }

    /**
     * Aces label pressed
     * If the player clicks the specific label of a field this method gets called.
     * According to the label he pressed, the checker checks if the conditions for this case are fulfilled.
     * If this is the case, the checker returns an int with the points he gained extra for the case.
     * If the conditions are not getting fulfilled, the checker returns -1 and the player can decide again.
     */
    @FXML
    public void p0Pressed() {
        if (hasDecided) return;
        int score = checker.checkp0(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(0, score);
        }

    }

    /**
     * Twos label pressed
     * If the player clicks the specific label of a field this method gets called.
     * According to the label he pressed, the checker checks if the conditions for this case are fulfilled.
     * If this is the case, the checker returns an int with the points he gained extra for the case.
     * If the conditions are not getting fulfilled, the checker returns -1 and the player can decide again.
     */
    @FXML
    public void p1Pressed() {
        if (hasDecided) return;
        int score = checker.checkp1(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(1, score);
        }
    }

    /**
     * Threes label pressed
     * If the player clicks the specific label of a field this method gets called.
     * According to the label he pressed, the checker checks if the conditions for this case are fulfilled.
     * If this is the case, the checker returns an int with the points he gained extra for the case.
     * If the conditions are not getting fulfilled, the checker returns -1 and the player can decide again.
     */
    @FXML
    public void p2Pressed() {
        if (hasDecided) return;
        int score = checker.checkp2(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(2, score);
        }
    }

    /**
     * Fours label pressed
     * If the player clicks the specific label of a field this method gets called.
     * According to the label he pressed, the checker checks if the conditions for this case are fulfilled.
     * If this is the case, the checker returns an int with the points he gained extra for the case.
     * If the conditions are not getting fulfilled, the checker returns -1 and the player can decide again.
     */
    @FXML
    public void p3Pressed() {
        if (hasDecided) return;
        int score = checker.checkp3(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(3, score);
        }
    }

    /**
     * Fives label pressed
     * If the player clicks the specific label of a field this method gets called.
     * According to the label he pressed, the checker checks if the conditions for this case are fulfilled.
     * If this is the case, the checker returns an int with the points he gained extra for the case.
     * If the conditions are not getting fulfilled, the checker returns -1 and the player can decide again.
     */
    @FXML
    public void p4Pressed() {
        if (hasDecided) return;
        int score = checker.checkp4(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(4, score);
        }
    }

    /**
     * Sixes label pressed
     * If the player clicks the specific label of a field this method gets called.
     * According to the label he pressed, the checker checks if the conditions for this case are fulfilled.
     * If this is the case, the checker returns an int with the points he gained extra for the case.
     * If the conditions are not getting fulfilled, the checker returns -1 and the player can decide again.
     */
    @FXML
    public void p5Pressed() {
        if (hasDecided) return;
        int score = checker.checkp5(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(5, score);
        }
    }

    /**
     * This method gets an int with the total score of the upper row.
     * It displays the score on a label
     * @param num upper total score
     */
    public void update6(int num) {
        p6.setText(Integer.toString(num));
    }

    /**
     * Checks if the player has 3 of a kind
     * If the player clicks the specific label of a field this method gets called.
     * According to the label he pressed, the checker checks if the conditions for this case are fulfilled.
     * If this is the case, the checker returns an int with the points he gained extra for the case.
     * If the conditions are not getting fulfilled, the checker returns -1 and the player can decide again.
     */
    @FXML
    public void p7Pressed() {
        if (hasDecided) return;
        int score = checker.checkp7(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(7, score);
        }
    }

    /**
     * Checks if the player has 4 of a kind
     * If the player clicks the specific label of a field this method gets called.
     * According to the label he pressed, the checker checks if the conditions for this case are fulfilled.
     * If this is the case, the checker returns an int with the points he gained extra for the case.
     * If the conditions are not getting fulfilled, the checker returns -1 and the player can decide again.
     */
    @FXML
    public void p8Pressed() {
        if (hasDecided) return;
        int score = checker.checkp8(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(8, score);
        }
    }

    /**
     * Checks if the player has a full house
     * If the player clicks the specific label of a field this method gets called.
     * According to the label he pressed, the checker checks if the conditions for this case are fulfilled.
     * If this is the case, the checker returns an int with the points he gained extra for the case.
     * If the conditions are not getting fulfilled, the checker returns -1 and the player can decide again.
     */
    @FXML
    public void p9Pressed() {
        if (hasDecided) return;
        int score = checker.checkp9(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(9, score);
        }
    }

    /**
     * Checks if the player has a small straight
     * If the player clicks the specific label of a field this method gets called.
     * According to the label he pressed, the checker checks if the conditions for this case are fulfilled.
     * If this is the case, the checker returns an int with the points he gained extra for the case.
     * If the conditions are not getting fulfilled, the checker returns -1 and the player can decide again.
     */
    @FXML
    public void p10Pressed() {
        if (hasDecided) return;
        int score = checker.checkp10(rolls);
        if (score == -1) log.info("Not matching");
        else  {
            enclosureTurn(10, score);
        }
    }

    /**
     * Checks if the player has a large straight
     * If the player clicks the specific label of a field this method gets called.
     * According to the label he pressed, the checker checks if the conditions for this case are fulfilled.
     * If this is the case, the checker returns an int with the points he gained extra for the case.
     * If the conditions are not getting fulfilled, the checker returns -1 and the player can decide again.
     */
    @FXML
    public void p11Pressed() {
        if (hasDecided) return;
        int score = checker.checkp11(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(11, score);
        }
    }

    /**
     * Checks if the player has 5 of a kind, also known as Kniffel
     * If the player clicks the specific label of a field this method gets called.
     * According to the label he pressed, the checker checks if the conditions for this case are fulfilled.
     * If this is the case, the checker returns an int with the points he gained extra for the case.
     * If the conditions are not getting fulfilled, the checker returns -1 and the player can decide again.
     */
    @FXML
    public void p12Pressed() {
        if (hasDecided) return;
        int score = checker.checkp12(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(12, score);
        }
    }

    /**
     * This method gets an int with the total score of the lower row.
     * It displays the score on a label
     * @param num lower total score
     */
    public void update13(int num) {
        p13.setText(Integer.toString(num));
    }

    /**
     * The chance case.
     * This field does not have any preconditions.
     * It just sums up the eye numbers of the dices.
     */
    @FXML
    public void p14Pressed() {
        if (hasDecided) return;
        int score = checker.checkp14(rolls);
        if (score == -1) log.info("Not matching");
        else {
            enclosureTurn(14, score);
        }
    }

    /**
     * This method displays if the player is worthy of the upper score bonus.
     * If so, it displays the gained bonus on a label
     * @param num extra points (35) or 0
     */
    public void update15(int num) {
        p15.setText(Integer.toString(num));
    }

    /**
     * Total score.
     * It displays the total score the player has
     * @param num total score
     */
    public void update16(int num) {
        p16.setText(Integer.toString(num));
    }

    /**
     * Decreases the players remaining turns and displays the remaining turns
     */
    public void decreaseRemainingTurns() {
        remainingTurns--;
        remainingTurnsLabel.setText("Remaining Turns: " + remainingTurns);
    }

    /**
     * Enclosures the players turn.
     * Gets called when a player has decided for a field which is possible
     * @param num number of the field (aces, twos, ..., three of a kind, ..., Kniffel)
     * @param score Points gained for that case so it can be displayed on the according label
     */
    public void enclosureTurn(int num, int score) {
        pointsButton.setImage(continueImage);
        hasDecided = true;
        updateLabel(num, score);
    }

    /**
     * Method gets a number, so it knows which label to update and a score to know with which number it should display on the label
     * @param num number of the label
     * @param score score which gets displayed
     */
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

    /**
     * When the player presses the exit button during the game, he gets send back to the menu
     */
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

    /**
     * Gets called when the "Got it" Button in the guide gets clicked.
     * Returns you back to the points sheet
     */
    @FXML
    public void gotItClicked() {
        switchScene(Scene.POINTS);
    }

    /**
     * Switches to the guide scene and displays the guide according to the parameter
     * @param guide guide to be shown
     */
    public void switchToGuide(Guide guide) {
        switchScene(Scene.GUIDE);
        guideIV.setImage(null);
        switch (guide) {
            case ACES -> guideIV.setImage(acesGuide);
            case TWOS -> guideIV.setImage(twosGuide);
            case THREES -> guideIV.setImage(threesGuide);
            case FOURS -> guideIV.setImage(foursGuide);
            case FIVES -> guideIV.setImage(fivesGuide);
            case SIXES -> guideIV.setImage(sixesGuide);
            case THREE_OAK -> guideIV.setImage(threeofakindGuide);
            case FOUR_OAK -> guideIV.setImage(fourofakindGuide);
            case FULL_HOUSE -> guideIV.setImage(fullhouseGuide);
            case SMALL_STRAIGHT -> guideIV.setImage(smallstraightGuide);
            case LARGE_STRAIGHT -> guideIV.setImage(largestraightGuide);
            case KNIFFEL -> guideIV.setImage(kniffelGuide);
            case CHANCE -> guideIV.setImage(chanceGuide);
            case UPPER_BONUS -> guideIV.setImage(upperBonusGuide);
        }
    }

    /**
     * Gets called when the player selects the aces image on the pointssheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void acesClicked() {
        switchToGuide(Guide.ACES);
    }

    /**
     * Gets called when the player selects the twos image on the pointssheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void twosClicked() {
        switchToGuide(Guide.TWOS);
    }

    /**
     * Gets called when the player selects the threes image on the pointssheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void threesClicked() {
        switchToGuide(Guide.THREES);
    }

    /**
     * Gets called when the player selects the fours image on the pointssheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void foursClicked() {
        switchToGuide(Guide.FOURS);
    }

    /**
     * Gets called when the player selects the fives image on the pointssheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void fivesClicked() {
        switchToGuide(Guide.FIVES);
    }

    /**
     * Gets called when the player selects the sixes image on the pointssheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void sixesClicked() {
        switchToGuide(Guide.SIXES);
    }

    @FXML
    public void threeOfAKindClicked() {
        switchToGuide(Guide.THREE_OAK);
    }

    @FXML
    public void fourOfAKindClicked() {
        switchToGuide(Guide.FOUR_OAK);
    }

    @FXML
    public void fullHouseClicked() {
        switchToGuide(Guide.FULL_HOUSE);
    }

    @FXML
    public void smallStraightClicked() {
        switchToGuide(Guide.SMALL_STRAIGHT);
    }

    @FXML
    public void largeStraightClicked() {
        switchToGuide(Guide.LARGE_STRAIGHT);
    }

    @FXML
    public void kniffelClicked() {
        switchToGuide(Guide.KNIFFEL);
    }

    @FXML
    public void chanceClicked() {
        switchToGuide(Guide.CHANCE);
    }

    @FXML
    public void upperBonusClicked() {
        switchToGuide(Guide.UPPER_BONUS);
    }
}
