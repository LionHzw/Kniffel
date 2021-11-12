package sample;

public enum MiscFilePath {
    DICE("/resources/Dice/Dice.png"),
    DICE1("/resources/Dice/Dice1.png"),
    DICE2("/resources/Dice/Dice2.png"),
    DICE3("/resources/Dice/Dice3.png"),
    DICE4("/resources/Dice/Dice4.png"),
    DICE5("/resources/Dice/Dice5.png"),
    DICE6("/resources/Dice/Dice6.png"),
    SELECTED("/resources/Buttons/Selected.png"),
    UNSELECTED("/resources/Buttons/Unselected.png"),
    SKIP("/resources/Buttons/Skip.png"),
    CONTINUE("/resources/Buttons/Continue.png"),
    BACK("/resources/Buttons/Back.png"),
    BUTTON_HOVER("/resources/Sounds/buttonHover1.wav"),
    BUTTON_PRESSED("/resources/Sounds/buttonPressed.wav"),
    ERROR("/resources/Sounds/error.wav"),
    ACCEPTED_TURN("/resources/Sounds/acceptedTurn.wav");


    String filePath;

    MiscFilePath(String filePath) {
        this.filePath = filePath;
    }

    String getFilePath() {
        return filePath;
    }
}
