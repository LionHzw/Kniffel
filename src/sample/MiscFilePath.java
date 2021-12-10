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
    FINISH("/resources/Buttons/Finish.png"),
    BUTTON_HOVER("/resources/Sounds/buttonHover1.wav"),
    BUTTON_PRESSED("/resources/Sounds/buttonPressed.wav"),
    ERROR("/resources/Sounds/error.wav"),
    ACCEPTED_TURN("/resources/Sounds/acceptedTurn.wav"),
    KNIFFEL_FINISH("/resources/Sounds/kniffelFinish.wav"),
    ACES("/resources/Guides/Aces.png"),
    TWOS("/resources/Guides/Twos.png"),
    THREES("/resources/Guides/Threes.png"),
    FOURS("/resources/Guides/Fours.png"),
    FIVES("/resources/Guides/Fives.png"),
    SIXES("/resources/Guides/Sixes.png"),
    THREE_OAK("/resources/Guides/3ofakind.png"),
    FOUR_OAK("/resources/Guides/4ofakind.png"),
    FULL_HOUSE("/resources/Guides/FullHouse.png"),
    SMALL_STRAIGHT("/resources/Guides/SmallStraight.png"),
    LARGE_STRAIGHT("/resources/Guides/LargeStraight.png"),
    KNIFFEL("/resources/Guides/Kniffel.png"),
    CHANCE("/resources/Guides/Chance.png"),
    UPPER_BONUS("/resources/Guides/UpperBonus.png"),
    FONT("/resources/Font/BAUHAUS93.TTF");


    String filePath;

    MiscFilePath(String filePath) {
        this.filePath = filePath;
    }

    String getFilePath() {
        return filePath;
    }
}
