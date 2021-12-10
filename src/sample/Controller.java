package sample;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.core.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

public class Controller {

    Check checker;
    Possibilities possibilities;
    Highscore highscore;
    Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @FXML public AnchorPane menuPane;
    @FXML public AnchorPane gamePane;
    @FXML public AnchorPane pointsPane;
    @FXML public AnchorPane settingsPane;
    @FXML public AnchorPane guidePane;
    @FXML public AnchorPane resultPane;
    @FXML public AnchorPane rankingPane;
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
    @FXML public ImageView guideIV;
    @FXML public Label resultLabel;
    @FXML public TextField enterNameField;
    @FXML public Label playLabel;
    @FXML public Label settingsLabel;
    @FXML public Label quitLabel;
    @FXML public Label rankingLabel;
    @FXML public Label creditsLabel;
    @FXML public ImageView playButton;
    @FXML public ImageView menuQuitButton;
    @FXML public ImageView rankingButton;
    @FXML public ImageView settingsButton;
    @FXML public ImageView menuCreditsButton;
    @FXML public Label hs1Label;
    @FXML public Label hs2Label;
    @FXML public Label hs3Label;
    @FXML public Label hs4Label;
    @FXML public Label hs5Label;
    @FXML public Label hs6Label;
    @FXML public Label hs7Label;
    @FXML public Label hs8Label;
    @FXML public Label hs9Label;
    @FXML public Label hs10Label;

    public int remaining;
    public int remainingTurns;
    public int[] rolls;
    public boolean isMuted = false;
    public boolean inGame = false;
    public boolean isWatchingHighscore = false;

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
    Image finishImage = new Image(MiscFilePath.FINISH.getFilePath());

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
        possibilities = new Possibilities(this, this.checker);
        highscore = new Highscore();
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

    public Label returnLabel(int label) {
        Label temp;
        switch(label) {
            case 0 -> temp = p0;
            case 1 -> temp = p1;
            case 2 -> temp = p2;
            case 3 -> temp = p3;
            case 4 -> temp = p4;
            case 5 -> temp = p5;
            case 6 -> temp = p6;
            case 7 -> temp = p7;
            case 8 -> temp = p8;
            case 9 -> temp = p9;
            case 10 -> temp = p10;
            case 11 -> temp = p11;
            case 12 -> temp = p12;
            case 13 -> temp = p13;
            case 14 -> temp = p14;
            case 15 -> temp = p15;
            case 16 -> temp = p16;
            default -> throw new IllegalStateException("Unexpected value: " + label);
        }
        return temp;
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

    /*public void setAllSelectsToFalse() {
        select1Bool = true;
        select2Bool = true;
        select3Bool = true;
        select4Bool = true;
        select5Bool = true;
    }*/

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
        resultPane.setVisible(false);
        rankingPane.setVisible(false);
        switch (scene) {
            case MENU -> menuPane.setVisible(true);
            case GAME -> gamePane.setVisible(true);
            case POINTS -> pointsPane.setVisible(true);
            case SETTINGS -> settingsPane.setVisible(true);
            case GUIDE -> guidePane.setVisible(true);
            case RESULT -> resultPane.setVisible(true);
            case RANKING -> rankingPane.setVisible(true);
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
        inGame = true;
        playButtonClickedSound();
        switchScene(Scene.GAME);
        remaining = 3;
        remainingTurns = 13;
        remainingLabel.setText("Remaining: 3");
        remainingTurnsLabel.setText("Remaining Turns: 13");
        resetGameAndDices();
        setAllToUnselected();
        setAllSelectsToTrue();
        checker.setAllValuesToZero();
        for (int i = 0; i < 5; i++) {
            rolls[i] = 0;
        }
        for (int i = 0; i < 17; i++) {
            updateLabel(i, 0);
        }

    }

    public void resetGameAndDices() {
        rolledDice1.setImage(dice0Image);
        rolledDice2.setImage(dice0Image);
        rolledDice3.setImage(dice0Image);
        rolledDice4.setImage(dice0Image);
        rolledDice5.setImage(dice0Image);
        dice1IV.setImage(dice0Image);
        dice2IV.setImage(dice0Image);
        dice3IV.setImage(dice0Image);
        dice4IV.setImage(dice0Image);
        dice5IV.setImage(dice0Image);
        dice1num = 0;
        dice2num = 0;
        dice3num = 0;
        dice4num = 0;
        dice5num = 0;
    }

    /**
     * Switches to the settingspane
     */
    @FXML
    public void menuSettingsClicked() {
        switchScene(Scene.SETTINGS);
    }

    @FXML
    public void menuCreditsClicked() {
        //TODO
    }

    @FXML
    public void menuRankingClicked() {
        switchScene(Scene.RANKING);
        ArrayList<SingleHighscore> arrayList = highscore.getArrayList();
        if (arrayList.size() > 0) hs1Label.setText(arrayList.get(0).getTotal() + " " + arrayList.get(0).getName());
        else hs1Label.setText("");
        if (arrayList.size() > 1) hs2Label.setText(arrayList.get(1).getTotal() + " " + arrayList.get(1).getName());
        else hs2Label.setText("");
        if (arrayList.size() > 2) hs3Label.setText(arrayList.get(2).getTotal() + " " + arrayList.get(2).getName());
        else hs3Label.setText("");
        if (arrayList.size() > 3) hs4Label.setText(arrayList.get(3).getTotal() + " " + arrayList.get(3).getName());
        else hs4Label.setText("");
        if (arrayList.size() > 4) hs5Label.setText(arrayList.get(4).getTotal() + " " + arrayList.get(4).getName());
        else hs5Label.setText("");
        if (arrayList.size() > 5) hs6Label.setText(arrayList.get(5).getTotal() + " " + arrayList.get(5).getName());
        else hs6Label.setText("");
        if (arrayList.size() > 6) hs7Label.setText(arrayList.get(6).getTotal() + " " + arrayList.get(6).getName());
        else hs7Label.setText("");
        if (arrayList.size() > 7) hs8Label.setText(arrayList.get(7).getTotal() + " " + arrayList.get(7).getName());
        else hs8Label.setText("");
        if (arrayList.size() > 8) hs9Label.setText(arrayList.get(8).getTotal() + " " + arrayList.get(8).getName());
        else hs9Label.setText("");
        if (arrayList.size() > 9) hs10Label.setText(arrayList.get(9).getTotal() + " " + arrayList.get(9).getName());
        else hs10Label.setText("");
    }

    @FXML
    public void scoreClicked(MouseEvent mouseEvent) {
        ArrayList<SingleHighscore> arrayList = highscore.getArrayList();
        int[] points = null;
        Label label = (Label) mouseEvent.getSource();
        try {
            if (label.equals(hs1Label)) {
                label = hs1Label;
                points = arrayList.get(0).getPoints();
            }
            else if (label.equals(hs2Label)) {
                label = hs2Label;
                points = arrayList.get(1).getPoints();
            }
            else if (label.equals(hs3Label)) {
                label = hs3Label;
                points = arrayList.get(2).getPoints();
            }
            else if (label.equals(hs4Label)) {
                label = hs4Label;
                points = arrayList.get(3).getPoints();
            }
            else if (label.equals(hs5Label)) {
                label = hs5Label;
                points = arrayList.get(4).getPoints();
            }
            else if (label.equals(hs6Label)) {
                label = hs6Label;
                points = arrayList.get(5).getPoints();
            }
            else if (label.equals(hs7Label)) {
                label = hs7Label;
                points = arrayList.get(6).getPoints();
            }
            else if (label.equals(hs8Label)) {
                label = hs8Label;
                points = arrayList.get(7).getPoints();
            }
            else if (label.equals(hs9Label)) {
                label = hs9Label;
                points = arrayList.get(8).getPoints();
            }
            else if (label.equals(hs10Label)) {
                label = hs10Label;
                points = arrayList.get(9).getPoints();
            }
            else {
                label = null;
                points = null;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("MF clicked on a non existing label");
            return;
        }
        hideRolledDice();
        isWatchingHighscore = true;
        switchScene(Scene.POINTS);
        pointsButton.setImage(backImage);
        checker.fillPointsFromArray(points);
        for (int i = 0; i < 17; i++) {
            updateLabel(i, checker.returnValueOfLabel(i));
        }

    }

    public void hideRolledDice() {
        rolledDice1.setVisible(false);
        rolledDice2.setVisible(false);
        rolledDice3.setVisible(false);
        rolledDice4.setVisible(false);
        rolledDice5.setVisible(false);
    }

    public void showRolledDice() {
        rolledDice1.setVisible(true);
        rolledDice2.setVisible(true);
        rolledDice3.setVisible(true);
        rolledDice4.setVisible(true);
        rolledDice5.setVisible(true);
    }

    @FXML
    public void rankingBackClicked() {
        switchScene(Scene.MENU);
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
        if (inGame) {
            switchScene(Scene.GAME);
        } else {
            switchScene(Scene.MENU);
        }
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
        possibilities.checkForPossibilities(rolls);
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
        checker.setSpecPoints(true);
        possibilities.highlightAllRemaining();
    }

    @FXML
    public void gameSettingsButtonPressed() {
        switchScene(Scene.SETTINGS);
    }

    /**
     * When the player is not happy with his roll, he can skip it and no action will be done except he loses a turn.
     */
    @FXML
    public void pointsSkipButtonPressed() {
        if (isWatchingHighscore) {
            switchScene(Scene.RANKING);
            isWatchingHighscore = false;
            return;
        }
        showRolledDice();
        if (!checker.getSpecPoints() && remainingTurns == 1) {
            finishGame(checker.numberp16);
            return;
        }
        if (checker.getSpecPoints()) {
            setAllToBlack();
            switchScene(Scene.GAME);
            checker.setSpecPoints(false);
        } else {
            setAllToBlack();
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
            if (src.equals(pointsButton)) src.setEffect(new DropShadow());
            if (isWatchingHighscore) return;
            src.setEffect(new DropShadow());
        }
        if (mouseEvent.getSource() instanceof Label src) {
            if (isWatchingHighscore) return;
            if (hasDecided) {
                for (int i = 0; i < 17; i++) {
                    if (src.equals(returnLabel(i))) {
                        return;
                    }
                }
            }
            src.setEffect(new DropShadow());
            showScorePreview(src, 1);
        }
    }

    /**
     * Effect when the player hovers off a button
     */
    @FXML
    public void onMouseExited(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView src) {
            if (src.equals(pointsButton)) src.setEffect(null);
            if (isWatchingHighscore) return;
            src.setEffect(null);
        }
        if (mouseEvent.getSource() instanceof Label src) {
            if (isWatchingHighscore) return;
            src.setEffect(null);
            showScorePreview(src, 0);
        }
    }

    /**
     * Effect when the player presses a button
     */
    @FXML
    public void onMousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView src) {
            if (src.equals(pointsButton)) src.setEffect(new InnerShadow());
            if (isWatchingHighscore) return;
            src.setEffect(new InnerShadow());
        }
        if (mouseEvent.getSource() instanceof Label src) {
            if (isWatchingHighscore) return;
            if (hasDecided) {
                for (int i = 0; i < 17; i++) {
                    if (src.equals(returnLabel(i))) {
                        return;
                    }
                }
            }
            src.setEffect(new InnerShadow());
        }
    }

    /**
     * Effect when the player releases a button
     */
    @FXML
    public void onMouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView src) {
            if (src.equals(pointsButton)) src.setEffect(null);
            if (isWatchingHighscore) return;
            src.setEffect(null);
        }
        if (mouseEvent.getSource() instanceof Label src) {
            if (isWatchingHighscore) return;
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
        setAllToBlack();
        changeColor(num, Color.BLUE);
        pointsButton.setImage(continueImage);
        hasDecided = true;
        updateLabel(num, score);
        possibilities.setAllValuesToTheirOriginal();
    }

    /**
     * Method gets a number, so it knows which label to update and a score to know with which number it should display on the label
     * @param num number of the label
     * @param score score which gets displayed
     */
    public void updateLabel(int num, int score) {
        for (int i = 0; i < 17; i++) {
            if (i == num) {
                returnLabel(i).setText(Integer.toString(score));
            }
        }
    }

    /**
     * When the player presses the exit button during the game, he gets send back to the menu
     */
    @FXML
    public void exitButtonPressed() {
        inGame = false;
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
        mediaPlayerButtonClicked.play(); */
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
        if (isWatchingHighscore) return;
        switchToGuide(Guide.ACES);
    }

    /**
     * Gets called when the player selects the twos image on the pointssheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void twosClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.TWOS);
    }

    /**
     * Gets called when the player selects the threes image on the pointssheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void threesClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.THREES);
    }

    /**
     * Gets called when the player selects the fours image on the pointssheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void foursClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.FOURS);
    }

    /**
     * Gets called when the player selects the fives image on the pointssheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void fivesClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.FIVES);
    }

    /**
     * Gets called when the player selects the sixes image on the pointssheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void sixesClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.SIXES);
    }

    @FXML
    public void threeOfAKindClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.THREE_OAK);
    }

    @FXML
    public void fourOfAKindClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.FOUR_OAK);
    }

    @FXML
    public void fullHouseClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.FULL_HOUSE);
    }

    @FXML
    public void smallStraightClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.SMALL_STRAIGHT);
    }

    @FXML
    public void largeStraightClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.LARGE_STRAIGHT);
    }

    @FXML
    public void kniffelClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.KNIFFEL);
    }

    @FXML
    public void chanceClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.CHANCE);
    }

    @FXML
    public void upperBonusClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.UPPER_BONUS);
    }

    public void changeColor(int label, Color color) {
        for (int i = 0; i < 17; i++) {
            if (i == label) returnLabel(i).setTextFill(color);
        }
    }

    /**
     * Sets all labels back to black
     */
    public void setAllToBlack() {
        for (int i = 0; i < 17; i++) {
            changeColor(i, Color.BLACK);
        }
    }

    public void finishGame(int score) {
        enterNameField.setFont(Font.font("Bauhaus 93", FontWeight.NORMAL, 12));
        switchScene(Scene.RESULT);
        resultLabel.setText(Integer.toString(score));
        if (score < 100) resultLabel.setTextFill(Color.RED);
        if (score >= 100 && score < 150) resultLabel.setTextFill(Color.ORANGE);
        if (score >= 150 && score < 200) resultLabel.setTextFill(Color.YELLOW);
        if (score >= 200 && score < 250) resultLabel.setTextFill(Color.GREEN);
        if (score >= 250 && score < 300) resultLabel.setTextFill(Color.DARKGREEN);
        if (score >= 300 && score < 350) resultLabel.setTextFill(Color.BLUE);
        if (score >= 350 && score < 375) resultLabel.setTextFill(Color.PURPLE);
        if (score == 375) resultLabel.setTextFill(Color.GOLD);

    }

    @FXML
    public void resultFinishClicked() {
        if (!enterNameField.getText().isEmpty()) {
            String withoutSpacing = enterNameField.getText();
            withoutSpacing = withoutSpacing.replaceAll("\\s+","");
            if (withoutSpacing.length() > 20) {
                System.out.println("This name is too long!");
            } else {
                int[] finalPoints = {
                        checker.returnValueOfLabel(0),
                        checker.returnValueOfLabel(1),
                        checker.returnValueOfLabel(2),
                        checker.returnValueOfLabel(3),
                        checker.returnValueOfLabel(4),
                        checker.returnValueOfLabel(5),
                        checker.returnValueOfLabel(6),
                        checker.returnValueOfLabel(7),
                        checker.returnValueOfLabel(8),
                        checker.returnValueOfLabel(9),
                        checker.returnValueOfLabel(10),
                        checker.returnValueOfLabel(11),
                        checker.returnValueOfLabel(12),
                        checker.returnValueOfLabel(13),
                        checker.returnValueOfLabel(14),
                        checker.returnValueOfLabel(15),
                        checker.returnValueOfLabel(16),
                };
                SingleHighscore singleHighscore = new SingleHighscore(checker.numberp16, withoutSpacing, finalPoints);
                highscore.addHighscore(singleHighscore);
                switchScene(Scene.MENU);
                checker.setAllValuesToZero();
                highscore.writeToFile();
                setAllToBlack();
            }
        } else {
            System.out.println("Please enter your name");
        }
    }

    @FXML
    public void resultShowDetails() {
        pointsButton.setImage(backImage);
        checker.setSpecPoints(true);
        setAllToBlack();
        switchScene(Scene.POINTS);
        checker.setSpecPoints(false);
        rolledDice1.setImage(dice0Image);
        rolledDice2.setImage(dice0Image);
        rolledDice3.setImage(dice0Image);
        rolledDice4.setImage(dice0Image);
        rolledDice5.setImage(dice0Image);
    }

    /**
     * mode 0 = show original score
     * mode 1 = show possible score
     * @param src
     * @param mode
     */
    public void showScorePreview(Label src, int mode) {
        checker.changeIsCheckingForPossibilities(true);
        for (int i = 0; i < 17; i++) {
            if (src.equals(returnLabel(i))) {
                if (mode == 0) {
                    updateLabel(i, checker.returnValueOfLabel(i));
                }
                if (mode == 1) {
                    if (checker.checkForIndividualI(i, rolls) == -1 && checker.returnValueOfLabel(i) == 0) {
                        updateLabel(i, 0);
                    } else if (checker.checkForIndividualI(i, rolls) == -1 && checker.returnValueOfLabel(i) != 0) {
                        updateLabel(i, checker.returnValueOfLabel(i));
                    } else if (checker.checkForIndividualI(i, rolls) == checker.returnValueOfLabel(i)) {
                        updateLabel(i, checker.returnValueOfLabel(i));
                    } else {
                        updateLabel(i, checker.checkForIndividualI(i, rolls));
                    }
                }
            }
        }
        checker.changeIsCheckingForPossibilities(false);
    }

    @FXML
    public void menuOnMouseEntered(MouseEvent mouseEvent) {
        onMouseEntered(mouseEvent);
        ImageView src = (ImageView) mouseEvent.getSource();
        double translateValue = 0;
        Label label;
        if (src.equals(playButton)) {
            label = playLabel;
            translateValue = 30;
        } else if (src.equals(settingsButton)) {
            label = settingsLabel;
            translateValue = -20;
        } else if (src.equals(rankingButton)) {
            label = rankingLabel;
            translateValue = -20;
        } else if (src.equals(menuQuitButton)) {
            label = quitLabel;
            translateValue = 30;
        } else if (src.equals(menuCreditsButton)) {
            label = creditsLabel;
            translateValue = -20;
        } else {
            //Default case
            label = null;
            translateValue = 0;
        }
        label.setVisible(true);
        //FadeIn
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.2), label);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        //Translate
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.2), label);
        if (src.equals(menuQuitButton) || src.equals(menuCreditsButton)) {
            translate.setToX(translateValue);
        } else {
            translate.setToY(translateValue);
        }
        translate.play();
    }

    @FXML
    public void menuOnMouseExited(MouseEvent mouseEvent) {
        onMouseExited(mouseEvent);
        ImageView src = (ImageView) mouseEvent.getSource();
        double translateValue = 0;
        Label label;
        if (src.equals(playButton)) {
            label = playLabel;
            translateValue = -20;
        } else if (src.equals(settingsButton)) {
            label = settingsLabel;
            translateValue = 30;
        } else if (src.equals(rankingButton)) {
            label = rankingLabel;
            translateValue = 30;
        } else if (src.equals(menuQuitButton)) {
            label = quitLabel;
            translateValue = -20;
        } else if (src.equals(menuCreditsButton)) {
            label = creditsLabel;
            translateValue = 30;
        } else {
            //Default case
            label = null;
            translateValue = 0;
        }
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.2), label);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();
        //Translate
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.2), label);
        if (src.equals(menuQuitButton) || src.equals(menuCreditsButton)) {
            translate.setToX(translateValue);
        } else {
            translate.setToY(translateValue);
        }
        translate.play();
    }
}
