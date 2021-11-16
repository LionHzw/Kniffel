package sample;

import javafx.scene.paint.Color;

/**
 * Checks all possibilities the player has for his move
 */
public class Possibilities {

    Controller controller;
    Check checker;

    /**
     * Constructor
     * @param controller controller
     * @param check checker
     */
    public Possibilities(Controller controller, Check check) {
        this.controller = controller;
        this.checker = check;
    }

    /**
     * Goes through all cases and checks if it is a possibility
     * @param a rolls
     */
    public void checkForPossibilities(int[] a) {
        checker.changeIsCheckingForPossibilities(true);
        if (checker.checkp0(a) > 0) controller.changeColor(0, Color.GREEN);
        if (checker.checkp1(a) > 0) controller.changeColor(1, Color.GREEN);
        if (checker.checkp2(a) > 0) controller.changeColor(2, Color.GREEN);
        if (checker.checkp3(a) > 0) controller.changeColor(3, Color.GREEN);
        if (checker.checkp4(a) > 0) controller.changeColor(4, Color.GREEN);
        if (checker.checkp5(a) > 0) controller.changeColor(5, Color.GREEN);
        if (checker.checkp7(a) > 0) controller.changeColor(7, Color.GREEN);
        if (checker.checkp8(a) > 0) controller.changeColor(8, Color.GREEN);
        if (checker.checkp9(a) > 0) controller.changeColor(9, Color.GREEN);
        if (checker.checkp10(a) > 0) controller.changeColor(10, Color.GREEN);
        if (checker.checkp11(a) > 0) controller.changeColor(11, Color.GREEN);
        if (checker.checkp12(a) > 0) controller.changeColor(12, Color.GREEN);
        if (checker.checkp14(a) > 0) controller.changeColor(14, Color.GREEN);
        checker.changeIsCheckingForPossibilities(false);
    }
}
