package sample;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    Check checker;
    Possibilities possibilities;
    Highscore highscore;

    @FXML public AnchorPane menuPane, gamePane, pointsPane, settingsPane, guidePane, resultPane, rankingPane, creditsPane, dialoguePane;
    @FXML public ImageView dice1IV, dice2IV, dice3IV, dice4IV, dice5IV;
    @FXML public ImageView selectDice1, selectDice2, selectDice3, selectDice4, selectDice5;
    @FXML public Label remainingLabel, remainingTurnsLabel;
    @FXML public Label p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16;
    @FXML public ImageView rolledDice1, rolledDice2, rolledDice3, rolledDice4, rolledDice5;
    @FXML public ImageView pointsButton;
    @FXML public ImageView guideIV;
    @FXML public Label resultLabel;
    @FXML public TextField enterNameField;
    @FXML public Label playLabel, settingsLabel, quitLabel, rankingLabel, creditsLabel;
    @FXML public ImageView playButton, menuQuitButton, rankingButton, settingsButton, menuCreditsButton;
    @FXML public Label hs1Label, hs2Label, hs3Label, hs4Label, hs5Label, hs6Label, hs7Label, hs8Label, hs9Label, hs10Label;
    @FXML public ImageView creditsLionHzwIV, creditsInstagramIV, creditsGithubIV, creditsTwitterIV, creditsBackIV;
    @FXML public Label creditsLionHzwLabel1, creditsLionHzwLabel2, creditsInstagramLabel1, creditsInstagramLabel2, creditsGithubLabel1, creditsGithubLabel2;
    @FXML public Label creditsTwitterLabel1,creditsTwitterLabel2, creditsBackLabel1, creditsBackLabel2, creditsBackLabel3;
    @FXML public ImageView settingsStick1, settingsStick2, settingsBackButton, settingsFullscreenButton, settingsMuteButton;
    @FXML public ImageView changeDiceStyleButton;
    @FXML public Label settingsBackLabel, settingsFullscreenLabel, settingsMuteLabel, settingsDiceStyleLabel;
    @FXML public Slider soundSlider, musicSlider;
    @FXML public Label soundsLabel, musicLabel;
    @FXML public ImageView gameExitButton, gameFinishButton;
    @FXML public Label gameExitLabel, gameFinishLabel;
    @FXML public Label pointsTurnsLeftLabel;
    @FXML public ImageView pointsGuideDice1IV, pointsGuideDice2IV, pointsGuideDice3IV, pointsGuideDice4IV, pointsGuideDice5IV, pointsGuideDice6IV;
    @FXML public ImageView style1IV, style2IV, style3IV, style4IV, style5IV, style6IV, style7IV, style8IV, style9IV;
    @FXML public ImageView songPrevIV, songPauseIV, songNextIV, songRestartIV;
    @FXML public ProgressBar songPGBar;
    @FXML public Label songNameLabel, songLabel;
    @FXML public Label rankingBackLabel;
    @FXML public Label dialogueHeader, dialogueText, dialogueButtonText;

    private ArrayList<File> songs;
    private int songNumber;
    private Timer timer;
    private boolean running;

    public int remaining;
    public int remainingTurns;
    public int[] rolls;
    public boolean inGame = false;
    public boolean isWatchingHighscore = false;
    public boolean isMuted = true;
    public boolean isDiceStyleUnfolded = false;

    public double globalSoundVolume;
    public double globalMusicVolume;
    private MediaPlayer mediaPlayerMusic;
    private Media mediaMusic;

    public boolean hasDecided = true;
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
    Image dice0Image = new Image(DiceFilePath.DICE.path());
    Image dice1Image = new Image(DiceFilePath.DICE1.path());
    Image dice2Image = new Image(DiceFilePath.DICE2.path());
    Image dice3Image = new Image(DiceFilePath.DICE3.path());
    Image dice4Image = new Image(DiceFilePath.DICE4.path());
    Image dice5Image = new Image(DiceFilePath.DICE5.path());
    Image dice6Image = new Image(DiceFilePath.DICE6.path());

    //BUTTONS
    Image skipImage = new Image(MiscFilePath.SKIP.getFilePath());
    Image backImage = new Image(MiscFilePath.BACK.getFilePath());
    Image continueImage = new Image(MiscFilePath.CONTINUE.getFilePath());
    //Image finishImage = new Image(MiscFilePath.FINISH.getFilePath());

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

    //MUSIC
    Image playMusicImage = new Image(MiscFilePath.PLAY.getFilePath());
    Image pauseMusicImage = new Image(MiscFilePath.PAUSE.getFilePath());

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
        setupMusic();
        rolls = new int[5];
        globalSoundVolume = 50.0;
        globalMusicVolume = 50.0;
        foldDiceStyles();
        settingsSoundClicked();
        updateDiceStyle();
        playMusic();
        running = true;

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
        creditsPane.setVisible(false);
        dialoguePane.setVisible(false);
        switch (scene) {
            case MENU -> menuPane.setVisible(true);
            case GAME -> gamePane.setVisible(true);
            case POINTS -> pointsPane.setVisible(true);
            case SETTINGS -> settingsPane.setVisible(true);
            case GUIDE -> guidePane.setVisible(true);
            case RESULT -> resultPane.setVisible(true);
            case RANKING -> rankingPane.setVisible(true);
            case CREDITS -> creditsPane.setVisible(true);
        }
    }

    /**
     * When the "Roll all" button gets hit the method gets called.
     * It gives all 5 dices a random int from 1 to 6.
     */
    @FXML
    public void rollAll() {
        if (remainingTurns == 0) {
            createDialogue(
                    "Warning",
                    "You have no remaining turns left",
                    "Okay"
            );
            return;
        }
        if (remaining == 0) {
            createDialogue(
                    "Warning",
                    "You have no remaining rolls left",
                    "Okay"
            );
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
        if (remainingTurns == 0) {
            createDialogue(
                    "Warning",
                    "You have no remaining turns left",
                    "Okay"
            );
            return;
        }
        if (remaining == 0) {
            createDialogue(
                    "Warning",
                    "You have no remaining rolls left",
                    "Okay"
            );
            return;
        } else if (remaining == 3) {
            createDialogue("Instruction", "You have to roll all dice first!", "Got it!");
            return;
        }
        remaining--;
        remainingLabel.setText("Remaining: " + remaining);
        Random rdm = new Random();


        if (select1Bool && select2Bool && select3Bool && select4Bool && select5Bool) {
            createDialogue(
                    "Warning",
                    "There's no dice selected! " +
                            "\n Please select one or more dice first",
                    "I'll retry!"
            );
        }

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
        pointsTurnsLeftLabel.setText("Turns Left: " + 13);
    }

    /**
     * A placeholder image gets displayed on the dice ImageView.
     * Plus the dice numbers are getting set to 0.
     */
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

    /**
     * Switches from the menu to the credits
     */
    @FXML
    public void menuCreditsClicked() {
        switchScene(Scene.CREDITS);
    }

    /**
     * Switches from the menu to the ranking
     */
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

    /**
     * This method gets called when a player clicks on a highscore in the highscore list.
     * The specific highscore the player clicked on, gets loaded and shown as a points-sheet
     * @param mouseEvent
     */
    @FXML
    public void scoreClicked(MouseEvent mouseEvent) {
        ArrayList<SingleHighscore> arrayList = highscore.getArrayList();
        int[] points;
        Label label = (Label) mouseEvent.getSource();
        try {
            if (label.equals(hs1Label)) points = arrayList.get(0).getPoints();
            else if (label.equals(hs2Label)) points = arrayList.get(1).getPoints();
            else if (label.equals(hs3Label)) points = arrayList.get(2).getPoints();
            else if (label.equals(hs4Label)) points = arrayList.get(3).getPoints();
            else if (label.equals(hs5Label)) points = arrayList.get(4).getPoints();
            else if (label.equals(hs6Label)) points = arrayList.get(5).getPoints();
            else if (label.equals(hs7Label)) points = arrayList.get(6).getPoints();
            else if (label.equals(hs8Label)) points = arrayList.get(7).getPoints();
            else if (label.equals(hs9Label)) points = arrayList.get(8).getPoints();
            else if (label.equals(hs10Label)) points = arrayList.get(9).getPoints();
            else points = null;
        } catch (IndexOutOfBoundsException e) {
            createDialogue("Notice", "There's not highscore here yet " +
                    "\n Why don't you try and make one?",
                    "I'm on it!");
            return;
        }
        pointsTurnsLeftLabel.setVisible(false);
        hideRolledDice();
        isWatchingHighscore = true;
        switchScene(Scene.POINTS);
        pointsButton.setImage(backImage);
        assert points != null;
        checker.fillPointsFromArray(points);
        for (int i = 0; i < 17; i++) {
            updateLabel(i, checker.returnValueOfLabel(i));
        }
    }

    /**
     * Hides the rolled dice on the points-Sheet
     */
    public void hideRolledDice() {
        rolledDice1.setVisible(false);
        rolledDice2.setVisible(false);
        rolledDice3.setVisible(false);
        rolledDice4.setVisible(false);
        rolledDice5.setVisible(false);
    }

    /**
     * Shows the rolled dice on the points-Sheet
     */
    public void showRolledDice() {
        rolledDice1.setVisible(true);
        rolledDice2.setVisible(true);
        rolledDice3.setVisible(true);
        rolledDice4.setVisible(true);
        rolledDice5.setVisible(true);
    }

    @FXML
    public void rankingBackClicked() {
        pointsTurnsLeftLabel.setVisible(true);
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
        assert thisScreen != null;
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
     * Returns from the settings to the menu
     */
    @FXML
    public void settingsBackPressed() {
        foldDiceStylesInstant();
        isDiceStyleUnfolded = false;
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
        if (remainingTurns == 0) {
            createDialogue(
                    "Warning",
                    "You have no remaining turns left",
                    "Okay"
            );
            return;
        }
        if (remaining == 3) {
            createDialogue(
                    "Instruction",
                    "You have never rolled the dice. Roll the dice by clicking the 'Roll all' button",
                    "Got it!"
            );
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
    }

    /**
     * The player has the ability to show his points while he is still in a turn in order to see which fields are still open.
     * The current rolls are getting displayed there too.
     */
    @FXML
    public void showPoints() {
        switchScene(Scene.POINTS);
        int[] tempSortedArray = {dice1num, dice2num, dice3num, dice4num, dice5num};
        Arrays.sort(tempSortedArray);
        rolledDice1.setImage(eyesReturner(tempSortedArray[0]));
        rolledDice2.setImage(eyesReturner(tempSortedArray[1]));
        rolledDice3.setImage(eyesReturner(tempSortedArray[2]));
        rolledDice4.setImage(eyesReturner(tempSortedArray[3]));
        rolledDice5.setImage(eyesReturner(tempSortedArray[4]));
        pointsButton.setImage(backImage);
        checker.setSpecPoints(true);
        possibilities.highlightAllRemaining();
    }

    public void updateDiceStyle() {
        dice0Image = new Image(DiceFilePath.DICE.path());
        dice1Image = new Image(DiceFilePath.DICE1.path());
        dice2Image = new Image(DiceFilePath.DICE2.path());
        dice3Image = new Image(DiceFilePath.DICE3.path());
        dice4Image = new Image(DiceFilePath.DICE4.path());
        dice5Image = new Image(DiceFilePath.DICE5.path());
        dice6Image = new Image(DiceFilePath.DICE6.path());
        pointsGuideDice1IV.setImage(dice1Image);
        pointsGuideDice2IV.setImage(dice2Image);
        pointsGuideDice3IV.setImage(dice3Image);
        pointsGuideDice4IV.setImage(dice4Image);
        pointsGuideDice5IV.setImage(dice5Image);
        pointsGuideDice6IV.setImage(dice6Image);
        dice1IV.setImage(eyesReturner(dice1num));
        dice2IV.setImage(eyesReturner(dice2num));
        dice3IV.setImage(eyesReturner(dice3num));
        dice4IV.setImage(eyesReturner(dice4num));
        dice5IV.setImage(eyesReturner(dice5num));
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
        showRolledDice();
        if (isWatchingHighscore) {
            switchScene(Scene.RANKING);
            isWatchingHighscore = false;
            return;
        }

        if (!checker.getSpecPoints() && remainingTurns == 1) {
            playSound(MiscFilePath.KNIFFEL_FINISH);
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
            if (isWatchingHighscore && src != pointsButton) return;
            src.setEffect(new DropShadow());
            playSound(MiscFilePath.BUTTON_HOVER);
        }
        if (mouseEvent.getSource() instanceof Label src) {
            if (src.getText().isEmpty()) {
                return;
            }
            if (isWatchingHighscore) {
                return;
            }
            if (hasDecided) {
                for (int i = 0; i < 17; i++) {
                    if (src.equals(returnLabel(i))) {
                        return;
                    }
                }
            }
            src.setEffect(new DropShadow());
            showScorePreview(src, 1);
            playSound(MiscFilePath.BUTTON_HOVER);
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
        playSound(MiscFilePath.BUTTON_PRESSED);
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
        if (score == -1) playSound(MiscFilePath.ERROR);
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
        if (score == -1) playSound(MiscFilePath.ERROR);
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
        if (score == -1) playSound(MiscFilePath.ERROR);
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
        if (score == -1) playSound(MiscFilePath.ERROR);
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
        if (score == -1) playSound(MiscFilePath.ERROR);
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
        if (score == -1) playSound(MiscFilePath.ERROR);
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
        if (score == -1) playSound(MiscFilePath.ERROR);
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
        if (score == -1) playSound(MiscFilePath.ERROR);
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
        if (score == -1) playSound(MiscFilePath.ERROR);
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
        if (score == -1) playSound(MiscFilePath.ERROR);
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
        if (score == -1) playSound(MiscFilePath.ERROR);
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
        if (score == -1) playSound(MiscFilePath.ERROR);
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
        if (score == -1) playSound(MiscFilePath.ERROR);
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
        pointsTurnsLeftLabel.setText("Turns Left: " + (remainingTurns - 1));
        playSound(MiscFilePath.ACCEPTED_TURN);
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

    /**
     * Prepares a mediaplayer to play a sound, and plays it
     * @param path: Path to the sound File
     */
    public void playSound(MiscFilePath path) {
        try {
            Media m = new Media(Objects.requireNonNull(getClass().getResource(path.getFilePath())).toString());
            MediaPlayer mediaPlayerButtonHover = new MediaPlayer(m);
            if (isMuted) {
                mediaPlayerButtonHover.setVolume(0);
            } else {
                mediaPlayerButtonHover.setVolume(globalSoundVolume / 100);
            }
            mediaPlayerButtonHover.play();
        } catch (NoClassDefFoundError | IllegalAccessError e) {
            System.out.println(e.getMessage());
        }
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

    /**
     * Gets called when the player selects the Three of a kind lettering on the Points-sheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void threeOfAKindClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.THREE_OAK);
    }

    /**
     * Gets called when the player selects the Four of a kind lettering on the Points-sheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void fourOfAKindClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.FOUR_OAK);
    }

    /**
     * Gets called when the player selects the FullHouse lettering on the Points-sheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void fullHouseClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.FULL_HOUSE);
    }

    /**
     * Gets called when the player selects the Small Straight lettering on the Points-sheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void smallStraightClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.SMALL_STRAIGHT);
    }

    /**
     * Gets called when the player selects the Large Straight lettering on the Points-sheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void largeStraightClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.LARGE_STRAIGHT);
    }

    /**
     * Gets called when the player selects the Kniffel lettering on the Points-sheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void kniffelClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.KNIFFEL);
    }

    /**
     * Gets called when the player selects the Chance lettering on the Points-sheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void chanceClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.CHANCE);
    }

    /**
     * Gets called when the player selects the Upper-Bonus lettering on the Points-sheet
     * Calls the switchToGuide method with the specific case
     */
    @FXML
    public void upperBonusClicked() {
        if (isWatchingHighscore) return;
        switchToGuide(Guide.UPPER_BONUS);
    }

    /**
     * Changes a label to a color
     * @param label: Label to be changed
     * @param color: Color which the label should appear in.
     */
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

    /**
     * Displayes your score in a color, based on your points
     * @param score: your score
     */
    public void finishGame(int score) {
        inGame = false;
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

    /**
     * After finishing a game, your name has to fulfill certain properties i.e. the length.
     * When everything's correct, your points with your name are getting saved in the highscore .txt file,
     * provided that you made it to the Top10.
     * After this, the game prepares all settings for a further game.
     */
    @FXML
    public void resultFinishClicked() {
        if (!enterNameField.getText().isEmpty()) {
            String withoutSpacing = enterNameField.getText();
            withoutSpacing = withoutSpacing.replaceAll("\\s+","");
            if (withoutSpacing.length() > 20) {
                createDialogue("Warning", "Your name is too long! \n Please make sure it has less than 20 characters", "Okay");
            } else {
                int[] finalPoints = new int[17];
                for (int i = 0; i < 17; i++) {
                    finalPoints[i] = checker.returnValueOfLabel(i);
                }
                SingleHighscore singleHighscore = new SingleHighscore(checker.numberp16, withoutSpacing, finalPoints);
                highscore.addHighscore(singleHighscore);
                switchScene(Scene.MENU);
                checker.setAllValuesToZero();
                highscore.writeToFile();
                setAllToBlack();
                remainingTurns = 13;
                remaining = 3;
            }
        } else {
            createDialogue("Warning", """
                    The field is empty.\s
                     You have to enter a name into the name field.\s
                     Please do so and try again.""", "I retry!");
        }
    }

    /**
     * After finishing a game, you can press the "Show Details" button to show the points sheet of your just finished game
     */
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
     * If you finished your move and are on the points sheet, you are given the chance
     * to display the possible score you would get for this turn
     * mode 0 = show original score
     * mode 1 = show possible score
     * @param src source
     * @param mode mode
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

    /**
     * OnMouseEntered method for the menu-AnchorPane
     * @param mouseEvent
     */
    @FXML
    public void menuOnMouseEntered(MouseEvent mouseEvent) {
        onMouseEntered(mouseEvent);
        ImageView src = (ImageView) mouseEvent.getSource();
        double translateValue;
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
            label = null;
            translateValue = 0;
        }
        assert label != null;
        label.setVisible(true);
        //FadeIn
        fadeAnimation(label, 0.0, 1.0, 0.2);
        //Translate
        if (src.equals(menuQuitButton) || src.equals(menuCreditsButton)) translateAnimation(label, 0, translateValue, 0.2);
        else translateAnimation(label, 1, translateValue, 0.2);
    }

    /**
     * OnMouseExited method for the menu-AnchorPane
     * @param mouseEvent
     */
    @FXML
    public void menuOnMouseExited(MouseEvent mouseEvent) {
        onMouseExited(mouseEvent);
        ImageView src = (ImageView) mouseEvent.getSource();
        double translateValue;
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
        fadeAnimation(label, 1.0, 0.0, 0.2);
        //Translate
        if (src.equals(menuQuitButton) || src.equals(menuCreditsButton)) translateAnimation(label, 0, translateValue, 0.2);
        else translateAnimation(label, 1, translateValue, 0.2);
    }

    /**
     * OnMouseEntered method for the credits-AnchorPane
     * @param mouseEvent
     */
    @FXML
    public void creditsOnMouseEntered(MouseEvent mouseEvent) {
        onMouseEntered(mouseEvent);
        ImageView src = (ImageView) mouseEvent.getSource();
        if (src.equals(creditsBackIV)) {
            Label label1 = creditsBackLabel1;
            Label label2 = creditsBackLabel2;
            Label label3 = creditsBackLabel3;
            label1.setVisible(true);
            label2.setVisible(true);
            label3.setVisible(true);

            fadeAnimation(label1, 0.0, 1.0, 0.2);
            fadeAnimation(label2, 0.0, 1.0, 0.2);
            fadeAnimation(label3, 0.0, 1.0, 0.2);

            translateAnimation(label1, 0, -20, 0.2);
            translateAnimation(label2, 1, -40, 0.2);
            translateAnimation(label3, 0, 20, 0.2);

        } else {
            Label label1 = null;
            Label label2 = null;
            //Default case
            if (src.equals(creditsLionHzwIV)) {
                label1 = creditsLionHzwLabel1;
                label2 = creditsLionHzwLabel2;
            } else if (src.equals(creditsInstagramIV)){
                label1 = creditsInstagramLabel1;
                label2 = creditsInstagramLabel2;
            } else if (src.equals(creditsGithubIV)) {
                label1 = creditsGithubLabel1;
                label2 = creditsGithubLabel2;
            } else if (src.equals(creditsTwitterIV)) {
                label1 = creditsTwitterLabel1;
                label2 = creditsTwitterLabel2;
            }
            assert label1 != null;
            assert label2 != null;
            label1.setVisible(true);
            label2.setVisible(true);
            fadeAnimation(label1, 0.0, 1.0, 0.2);
            fadeAnimation(label2, 0.0, 1.0, 0.2);

            translateAnimation(label1, 1, -40, 0.2);
            translateAnimation(label2, 1, 40, 0.2);
        }
    }

    /**
     * OnMouseExited method for the Credits-AnchorPane
     * @param mouseEvent
     */
    @FXML
    public void creditsOnMouseExited(MouseEvent mouseEvent) {
        onMouseExited(mouseEvent);
        ImageView src = (ImageView) mouseEvent.getSource();
        if (src.equals(creditsBackIV)) {
            Label label1 = creditsBackLabel1;
            Label label2 = creditsBackLabel2;
            Label label3 = creditsBackLabel3;

            fadeAnimation(label1, 1.0, 0.0, 0.2);
            fadeAnimation(label2, 1.0, 0.0, 0.2);
            fadeAnimation(label3, 1.0, 0.0, 0.2);

            translateAnimation(label1, 0, 0, 0.2);
            translateAnimation(label2, 1, 0, 0.2);
            translateAnimation(label3, 0, 0, 0.2);

        } else {
            Label label1 = null;
            Label label2 = null;
            //Default case
            if (src.equals(creditsLionHzwIV)) {
                label1 = creditsLionHzwLabel1;
                label2 = creditsLionHzwLabel2;
            } else if (src.equals(creditsInstagramIV)){
                label1 = creditsInstagramLabel1;
                label2 = creditsInstagramLabel2;
            } else if (src.equals(creditsGithubIV)) {
                label1 = creditsGithubLabel1;
                label2 = creditsGithubLabel2;
            } else if (src.equals(creditsTwitterIV)) {
                label1 = creditsTwitterLabel1;
                label2 = creditsTwitterLabel2;
            }
            fadeAnimation(label1, 1.0, 0.0, 0.2);
            fadeAnimation(label2, 1.0, 0.0, 0.2);

            translateAnimation(label1,1, 0, 0.2);
            translateAnimation(label2,1, 0, 0.2);
        }
    }

    /**
     * This method is a quick and easy way to apply a fade-animation on a label
     * @param label: Label which should get the fade animation
     * @param fromVal: From which Fade-Level the animation starts
     * @param toVal: At which Fade-Level the animations stops
     * @param dur: Duration (in seconds) how long the animation takes
     */
    public void fadeAnimation(Label label, double fromVal, double toVal, double dur) {
        FadeTransition ft = new FadeTransition(Duration.seconds(dur), label);
        ft.setFromValue(fromVal);
        ft.setToValue(toVal);
        ft.play();
    }

    /**
     * Same method as above, just for ImageViews instead of Labels
     */
    public void fadeAnimationImageView(ImageView imageView, double fromVal, double toVal, double dur) {
        FadeTransition ft = new FadeTransition(Duration.seconds(dur), imageView);
        ft.setFromValue(fromVal);
        ft.setToValue(toVal);
        ft.play();
    }

    /**
     * This method is a quick and easy way to give labels a nice translate-animation
     * @param label: Label to be moved
     * @param xyz: xyz is an integer which is indicating in which dimension the label should move.
     *           0: x, 1: y, 2: z,
     * @param setToVal: DoubleValue where the label should move to.
     * @param dur: Duration (in seconds) for the animation to last.
     */
    public void translateAnimation(Label label, int xyz, double setToVal, double dur) {
        TranslateTransition tt = new TranslateTransition(Duration.seconds(dur), label);
        switch (xyz) {
            case 0 -> tt.setToX(setToVal);
            case 1 -> tt.setToY(setToVal);
            case 2 -> tt.setToZ(setToVal);
        }
        tt.play();
    }

    /**
     * Same method as above, just for ImageViews instead of labels.
     */
    public void translateAnimationImageView(ImageView imageView, int xyz, double setToVal, double dur) {
        TranslateTransition tt = new TranslateTransition(Duration.seconds(dur), imageView);
        switch (xyz) {
            case 0 -> tt.setToX(setToVal);
            case 1 -> tt.setToY(setToVal);
            case 2 -> tt.setToZ(setToVal);
        }
        tt.play();
    }

    /**
     * Takes you from the Credits back to the Menu
     */
    @FXML
    public void creditsBackClicked() {
        switchScene(Scene.MENU);
    }

    /**
     * Following four methods are getting called when the player selects a specific icon in the credits-AnchorPane
     */
    @FXML
    public void creditsLionHzwClicked() {
        openURL("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
    }

    @FXML
    public void creditsInstagramClicked() {
        openURL("https://www.instagram.com/lion.hzw/");
    }

    @FXML
    public void creditsGitHubClicked() {
        openURL("https://github.com/LionHzw");
    }

    @FXML
    public void creditsTwitterClicked() {
        openURL("https://twitter.com/Lion_Hoelzl");
    }

    /**
     * Opens up a specific URL in your default browser. Mostly used by the credits-icons
     * @param url: URL to be led to
     */
    public void openURL(String url) {
        try {
            URI uri = new URI(url);
            java.awt.Desktop.getDesktop().browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * When a player clicks on the big speaker button on the settings menu,
     * there will be an animation right next to it, signaling the player whether the sounds are muted ot not
     */
    @FXML
    public void settingsSoundClicked() {
        if (isMuted) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.2), settingsStick2);
            tt.setToX(52);
            tt.setToY(-8);
            tt.play();

            ColorAdjust red = new ColorAdjust();
            red.setHue(0.5);
            settingsStick1.setEffect(red);
            settingsStick2.setEffect(red);

            mediaPlayerMusic.setVolume(globalMusicVolume / 100);

            isMuted = false;
        } else {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.2), settingsStick2);
            tt.setToX(0);
            tt.setToY(0);
            tt.play();

            ColorAdjust green = new ColorAdjust();
            green.setHue(0.0);
            settingsStick1.setEffect(green);
            settingsStick2.setEffect(green);

            mediaPlayerMusic.setVolume(0);

            isMuted = true;
        }
    }

    /**
     * MouseEnteredEvent for the settings-AnchorPane
     * @param mouseEvent
     */
    @FXML
    public void settingsOnMouseEntered(MouseEvent mouseEvent) {
        onMouseEntered(mouseEvent);
        ImageView src = (ImageView) mouseEvent.getSource();
        Label label = null;
        if (src.equals(settingsFullscreenButton)) label = settingsFullscreenLabel;
        else if (src.equals(settingsMuteButton)) label = settingsMuteLabel;
        else if (src.equals(changeDiceStyleButton)) {
            label = settingsDiceStyleLabel;
            if (!isDiceStyleUnfolded) {
                unfoldDiceStyles();
            }
        }
        else if (src.equals(settingsBackButton)) label = settingsBackLabel;
        assert label != null;
        label.setVisible(true);

        fadeAnimation(label, 0.0, 1.0, 0.2);
        if (src.equals(settingsBackButton)) translateAnimation(label, 0, 20, 0.2);
        else translateAnimation(label, 1, -20, 0.2);
    }

    /**
     * MouseExitedEvent for the Settings-AnchorPane
     * @param mouseEvent
     */
    @FXML
    public void settingsOnMouseExited(MouseEvent mouseEvent) {
        onMouseExited(mouseEvent);
        ImageView src = (ImageView) mouseEvent.getSource();
        Label label = null;
        if (src.equals(settingsFullscreenButton)) label = settingsFullscreenLabel;
        else if (src.equals(settingsMuteButton)) label = settingsMuteLabel;
        else if (src.equals(changeDiceStyleButton)) label = settingsDiceStyleLabel;
        else if (src.equals(settingsBackButton)) label = settingsBackLabel;
        assert label != null;

        fadeAnimation(label, 1.0, 0.0, 0.2);
        if (src.equals(settingsBackButton)) translateAnimation(label, 0, 0, 0.2);
        else translateAnimation(label, 1, 0, 0.2);
    }

    /**
     * Unfolds images of possible dice-styles from which the player can choose from.
     * Every image has an animation which will be shown when the player hovers over the big dice in the
     * settings menu
     */
    public void unfoldDiceStyles() {
        if (isDiceStyleUnfolded) return;
        else {
            isDiceStyleUnfolded = true;
            style1IV.setVisible(true);
            style2IV.setVisible(true);
            style3IV.setVisible(true);
            style4IV.setVisible(true);
            style5IV.setVisible(true);
            style6IV.setVisible(true);
            style7IV.setVisible(true);
            style8IV.setVisible(true);
            style9IV.setVisible(true);
            fadeAnimationImageView(style1IV, 0.0, 1.0, 1.3);
            fadeAnimationImageView(style2IV, 0.0, 1.0, 1.2);
            fadeAnimationImageView(style3IV, 0.0, 1.0, 1.1);
            fadeAnimationImageView(style4IV, 0.0, 1.0, 1.0);
            fadeAnimationImageView(style5IV, 0.0, 1.0, 0.9);
            fadeAnimationImageView(style6IV, 0.0, 1.0, 0.8);
            fadeAnimationImageView(style7IV, 0.0, 1.0, 0.7);
            fadeAnimationImageView(style8IV, 0.0, 1.0, 0.6);
            fadeAnimationImageView(style9IV, 0.0, 1.0, 0.5);
            translateAnimationImageView(style1IV, 1, 0, 1.3);
            translateAnimationImageView(style2IV, 1, 0, 1.2);
            translateAnimationImageView(style3IV, 1, 0, 1.1);
            translateAnimationImageView(style4IV, 1, 0, 1.0);
            translateAnimationImageView(style5IV, 1, 0, 0.9);
            translateAnimationImageView(style6IV, 1, 0, 0.8);
            translateAnimationImageView(style7IV, 1, 0, 0.7);
            translateAnimationImageView(style8IV, 1, 0, 0.6);
            translateAnimationImageView(style9IV, 1, 0, 0.5);
        }
    }

    /**
     * It lets all the possible dice style disappear again in a stylish way.
     */
    public void foldDiceStyles() {
        fadeAnimationImageView(style1IV, 1.0, 0.0, 0.5);
        fadeAnimationImageView(style2IV, 1.0, 0.0, 0.6);
        fadeAnimationImageView(style3IV, 1.0, 0.0, 0.7);
        fadeAnimationImageView(style4IV, 1.0, 0.0, 0.8);
        fadeAnimationImageView(style5IV, 1.0, 0.0, 0.9);
        fadeAnimationImageView(style6IV, 1.0, 0.0, 1.0);
        fadeAnimationImageView(style7IV, 1.0, 0.0, 1.1);
        fadeAnimationImageView(style8IV, 1.0, 0.0, 1.2);
        fadeAnimationImageView(style9IV, 1.0, 0.0, 1.3);
        translateAnimationImageView(style1IV, 1, -30, 0.5);
        translateAnimationImageView(style2IV, 1, -30, 0.6);
        translateAnimationImageView(style3IV, 1, -30, 0.7);
        translateAnimationImageView(style4IV, 1, -30, 0.8);
        translateAnimationImageView(style5IV, 1, -30, 0.9);
        translateAnimationImageView(style6IV, 1, -30, 1.0);
        translateAnimationImageView(style7IV, 1, -30, 1.1);
        translateAnimationImageView(style8IV, 1, -30, 1.2);
        translateAnimationImageView(style9IV, 1, -30, 1.3);
    }

    /**
     * Basically the same method as above, just that it only takes 0.1 seconds for the animations to happen'.
     * This is needed, so the options for the dice style are not visible, after reentering the settings menu
     * quickly after leaving it.
     */
    public void foldDiceStylesInstant() {
        fadeAnimationImageView(style1IV, 1.0, 0.0, 0.1);
        fadeAnimationImageView(style2IV, 1.0, 0.0, 0.1);
        fadeAnimationImageView(style3IV, 1.0, 0.0, 0.1);
        fadeAnimationImageView(style4IV, 1.0, 0.0, 0.1);
        fadeAnimationImageView(style5IV, 1.0, 0.0, 0.1);
        fadeAnimationImageView(style6IV, 1.0, 0.0, 0.1);
        fadeAnimationImageView(style7IV, 1.0, 0.0, 0.1);
        fadeAnimationImageView(style8IV, 1.0, 0.0, 0.1);
        fadeAnimationImageView(style9IV, 1.0, 0.0, 0.1);
        translateAnimationImageView(style1IV, 1, -30, 0.1);
        translateAnimationImageView(style2IV, 1, -30, 0.1);
        translateAnimationImageView(style3IV, 1, -30, 0.1);
        translateAnimationImageView(style4IV, 1, -30, 0.1);
        translateAnimationImageView(style5IV, 1, -30, 0.1);
        translateAnimationImageView(style6IV, 1, -30, 0.1);
        translateAnimationImageView(style7IV, 1, -30, 0.1);
        translateAnimationImageView(style8IV, 1, -30, 0.1);
        translateAnimationImageView(style9IV, 1, -30, 0.1);
    }

    /**
     * Locates which image of a specific dice style just got pressed and decides to which dice-style the game should change
     * @param mouseEvent MouseEvent to take on further actions of the source
     */
    @FXML
    public void styleOnMouseClicked(MouseEvent mouseEvent) {
        ImageView src = (ImageView) mouseEvent.getSource();
        if (src.equals(style1IV)) {
            changeDiceStyle(1);
        } else if (src.equals(style2IV)) {
            changeDiceStyle(2);
        } else if (src.equals(style3IV)) {
            changeDiceStyle(3);
        } else if (src.equals(style4IV)) {
            changeDiceStyle(4);
        } else if (src.equals(style5IV)) {
            changeDiceStyle(5);
        } else if (src.equals(style6IV)) {
            changeDiceStyle(6);
        } else if (src.equals(style7IV)) {
            changeDiceStyle(7);
        } else if (src.equals(style8IV)) {
            changeDiceStyle(8);
        } else if (src.equals(style9IV)) {
            changeDiceStyle(9);
        }
        foldDiceStyles();
        isDiceStyleUnfolded = false;
    }

    /**
     * This method gets called whenever the dice style has changed
     * @param style 1-3 Dice with eyes
     *              4-6 Dice with numbers
     *              7-9 Dice with letters (ugly af, needs rework)
     */
    public void changeDiceStyle(int style) {
        DiceFilePath.setVersion(style);
        updateDiceStyle();
    }

    /**
     * Method lets you change the volume of the sound by changing the slider.
     */
    @FXML
    public void updateSoundsVolumeLive() {
        soundSlider
                .valueProperty()
                .addListener(
                        (observable, oldValue, newValue) -> globalSoundVolume = soundSlider.getValue() / 100);
        globalSoundVolume = soundSlider.getValue();
        soundsLabel.setText(Integer.toString((int) globalSoundVolume));
    }

    /**
     * Method lets you change the volume of the music by changing the slider.
     */
    @FXML
    public void updateMusicVolumeLive() {
        musicSlider
                .valueProperty()
                .addListener(
                        (observable, oldValue, newValue) -> globalMusicVolume = musicSlider.getValue() / 100);
        globalMusicVolume = musicSlider.getValue();
        mediaPlayerMusic.setVolume(globalMusicVolume / 100);
        musicLabel.setText(Integer.toString((int) globalMusicVolume));
    }

    /*
    public void setSoundsVolume(double volumeValue) {
        globalSoundVolume = volumeValue;
    }

    public void setMusicVolume(double volumeValue) {
        globalMusicVolume = volumeValue;
        mediaPlayerMusic.setVolume(volumeValue / 100);
    }
    */

    /**
     * This method sets up the media player for the music.
     * The Mediaplayer gets a String with the Music-File URL
     */
    public void setupMusic() {
        songs = addAllSongs();
        Random rdm = new Random();
        songNumber = rdm.nextInt(9);
        mediaMusic = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayerMusic = new MediaPlayer(mediaMusic);
        changeSongLabels(songs.get(songNumber).getName());
    }

    /**
     * Adding all songs to an ArrayList
     * @return ArrayList with Files (Songs)
     */
    public ArrayList<File> addAllSongs() {
        ArrayList<File> temp = new ArrayList<>();
        for (SongFilePath song : SongFilePath.values()) {
            temp.add(prepareFile(song.getFilePath()));
        }
        return temp;
    }

    /**
     * This method is a workaround to add songs to the game
     * The song path gets saved as an Image so the URL to the song is correct
     * Next, the URL to the image gets changed to have the correct syntax ("%20" -> " ") & remove "\file" at the beginning of the URL
     * Lastly, the URL String is being used to create a file which contains the music
     * @param songPath Path to a song
     * @return File with the song in it
     */
    public File prepareFile(String songPath) {
        Image tempImage = new Image(songPath);
        String tempString = tempImage.getUrl();
        tempString = tempString.replaceAll("%20", " ");
        tempString = tempString.substring(5);
        return new File(tempString);
    }

    /**
     * Starts the musicplayer
     */
    public void playMusic() {
        beginTimer();
        songPauseIV.setImage(pauseMusicImage);
        mediaPlayerMusic.setVolume(musicSlider.getValue() * 0.01);
        mediaPlayerMusic.play();
    }

    /**
     * Lets a label with either "finish" or "back" appear, based on with arrow you hover over
     * @param mouseEvent MouseEvent to take on further actions on the source
     */
    @FXML
    public void gameOnMouseEntered(MouseEvent mouseEvent) {
        onMouseEntered(mouseEvent);
        ImageView src = (ImageView) mouseEvent.getSource();
        Label label;
        if (src.equals(gameFinishButton)) {
            label = gameFinishLabel;
            translateAnimation(label, 0, -50, 0.2);
        }
        else {
            label = gameExitLabel;
            translateAnimation(label, 0, 50, 0.2);
        }
        label.setVisible(true);
        fadeAnimation(label, 0.0, 1.0, 0.2);
    }

    /**
     * Lets the corresponding label disappear again.
     * @param mouseEvent MouseEvent to take on further actions on the source
     */
    @FXML
    public void gameOnMouseExited(MouseEvent mouseEvent) {
        onMouseExited(mouseEvent);
        ImageView src = (ImageView) mouseEvent.getSource();
        Label label;
        if (src.equals(gameFinishButton)) {
            label = gameFinishLabel;
        }
        else {
            label = gameExitLabel;
        }
        translateAnimation(label, 0, 0, 0.2);
        fadeAnimation(label, 1.0, 0.0, 0.2);
    }


    /**
     * Lets a label appear with some fancy animations.
     * Plus calls the usual onMouseEntered method
     * @param mouseEvent MouseEvent to take actions on the source
     */
    @FXML
    public void rankingOnMouseEntered(MouseEvent mouseEvent) {
        onMouseEntered(mouseEvent);
        rankingBackLabel.setVisible(true);
        translateAnimation(rankingBackLabel, 0, 25, 0.2);
        fadeAnimation(rankingBackLabel, 0.0, 1.0, 0.2);
    }

    /**
     * Lets the "Back"-label fade out with some fancy animations.
     * Plus calls the usual onMouseExited method
     * @param mouseEvent MouseEvent to take actions on the source
     */
    @FXML
    public void rankingOnMouseExited(MouseEvent mouseEvent) {
        onMouseExited(mouseEvent);
        translateAnimation(rankingBackLabel, 0, 0, 0.2);
        fadeAnimation(rankingBackLabel, 1.0, 0.0, 0.2);
    }

    //Next up: Song Player

    public void changeSongLabels(String title) {
        String titleWithoutSuffix = title.substring(0,title.lastIndexOf("."));
        try {
            Platform.runLater(() -> {
                songNameLabel.setText(titleWithoutSuffix);
                songLabel.setText(titleWithoutSuffix);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void songPrev() {
        if (songNumber > 0) songNumber--;
        else songNumber = songs.size() - 1;
        mediaPlayerMusic.stop();
        if (running) cancelTimer();
        mediaMusic = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayerMusic = new MediaPlayer(mediaMusic);
        changeSongLabels(songs.get(songNumber).getName());
        playMusic();
    }

    @FXML
    public void songPause() {
        if (running) {
            cancelTimer();
            mediaPlayerMusic.pause();
            songPauseIV.setImage(playMusicImage);
        } else {
            playMusic();
        }
    }

    @FXML
    public void songNext() {
        if (songNumber < songs.size() - 1) songNumber++;
        else songNumber = 0;
        mediaPlayerMusic.stop();
        if (running) cancelTimer();
        mediaMusic = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayerMusic = new MediaPlayer(mediaMusic);
        changeSongLabels(songs.get(songNumber).getName());
        playMusic();
    }

    @FXML
    public void songRestart() {
        songPGBar.setProgress(0.02);
        mediaPlayerMusic.seek(Duration.seconds(0.0));
    }

    public void beginTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double current = mediaPlayerMusic.getCurrentTime().toSeconds();
                double end = mediaMusic.getDuration().toSeconds();
                songPGBar.setProgress(Math.max(current / end, 0.02));

                if (current / end == 1) {
                    cancelTimer();
                    songNext();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 10);
    }

    public void cancelTimer() {
        running = false;
        timer.cancel();
    }

    public void createDialogue(String header, String text, String button) {
        dialoguePane.setVisible(true);
        dialogueHeader.setText(header);
        dialogueText.setText(text);
        dialogueButtonText.setText(button);
    }

    @FXML
    public void closeDialogue() {
        dialoguePane.setVisible(false);
    }
}
