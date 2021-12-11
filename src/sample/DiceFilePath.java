package sample;

public enum DiceFilePath {
    DICE("/resources/Dice/Style", "/Dice.png"),
    DICE1("/resources/Dice/Style", "/Dice1.png"),
    DICE2("/resources/Dice/Style", "/Dice2.png"),
    DICE3("/resources/Dice/Style", "/Dice3.png"),
    DICE4("/resources/Dice/Style", "/Dice4.png"),
    DICE5("/resources/Dice/Style", "/Dice5.png"),
    DICE6("/resources/Dice/Style", "/Dice6.png");


    private final String path1;
    private final String path2;

    /**
     * Version 1 : old cards Version 2: confetti cards Version 3: dark mode Version 4:
     * colorblind with colors cards
     */
    private String version;

    /**
     * Constructor for the CardFilePath Enum
     *
     * @param path1 the first part of the path
     * @param path2 the second part of the path
     */
    DiceFilePath(String path1, String path2) {
        this.path1 = path1;
        this.path2 = path2;
        this.version = "1";
    }

    /**
     * Returns the path
     *
     * @return path in String form
     */
    public String path() {
        return this.path1 + this.version + this.path2;
    }

    /**
     * Sets the version of all enums
     *
     * @param v version in Integer form: 1 is old dice, 2 is colorful dice, 3 is dark mode dice
     */
    public static void setVersion(int v) {
        for (DiceFilePath dfp : DiceFilePath.values()) {
            dfp.version = Integer.toString(v);
        }
    }

    public void setNonStaticVersion(int v) {
        this.version = Integer.toString(v);
    }
}
