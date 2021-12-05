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
        Color color = Color.GREEN;
        if (checker.checkp0(a) > 0) {controller.changeColor(0, color);
        }
        if (checker.checkp1(a) > 0) {
            controller.changeColor(1, color);
        }
        if (checker.checkp2(a) > 0) {
            controller.changeColor(2, color);
        }
        if (checker.checkp3(a) > 0) controller.changeColor(3, color);
        if (checker.checkp4(a) > 0) controller.changeColor(4, color);
        if (checker.checkp5(a) > 0) controller.changeColor(5, color);
        if (checker.checkp7(a) > 0) controller.changeColor(7, color);
        if (checker.checkp8(a) > 0) controller.changeColor(8, color);
        if (checker.checkp9(a) > 0) controller.changeColor(9, color);
        if (checker.checkp10(a) > 0) controller.changeColor(10, color);
        if (checker.checkp11(a) > 0) controller.changeColor(11, color);
        if (checker.checkp12(a) > 0) controller.changeColor(12, color);
        if (checker.checkp14(a) > 0) controller.changeColor(14, color);
        checker.changeIsCheckingForPossibilities(false);
    }

    public void setAllValuesToTheirOriginal() {
        for (int i = 0; i < 17; i++) {
            controller.updateLabel(i, checker.returnValueOfLabel(i));
        }
    }
}
