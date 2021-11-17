package sample;

import java.util.logging.*;

public class Check {

    Controller controller;
    Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public int numberp0;
    public int numberp1;
    public int numberp2;
    public int numberp3;
    public int numberp4;
    public int numberp5;
    public int numberp6;
    public int numberp7;
    public int numberp8;
    public int numberp9;
    public int numberp10;
    public int numberp11;
    public int numberp12;
    public int numberp13;
    public int numberp14;
    public int numberp15;
    public int numberp16;

    private boolean specPoints = false;
    private boolean isCheckingForPossibilities = false;

    /**
     * constructor of the Check class
     * @param controller controller for the FXML
     */
    public Check(Controller controller) {
        this.controller = controller;
    }

    /**
     * specPoints gets set to the specific state
     */
    public void setSpecPoints(boolean state) {
        specPoints = state;
    }

    /**
     * returns if the player is spectating the points right now
     * @return boolean: true: player is spectating the pointssheet
     *                  false: player isn't
     */
    public boolean getSpecPoints() {
        return specPoints;
    }

    public void changeIsCheckingForPossibilities(boolean state) {
        isCheckingForPossibilities = state;
    }

    public boolean getIsCheckingForPossibilities() {
        return isCheckingForPossibilities;
    }

    /**
     * sets all values to zero (when a game gets started or restarted)
     */
    public void setAllValuesToZero() {
        numberp0 = 0;
        numberp1 = 0;
        numberp2 = 0;
        numberp3 = 0;
        numberp4 = 0;
        numberp5 = 0;
        numberp6 = 0;
        numberp7 = 0;
        numberp8 = 0;
        numberp9 = 0;
        numberp10 = 0;
        numberp11 = 0;
        numberp12 = 0;
        numberp13 = 0;
        numberp14 = 0;
        numberp15 = 0;
        numberp16 = 0;
    }

    /**
     * Checks for amount of aces.
     * Multiplies the amount by 1.
     * @param a int array with the current roll
     * @return returns -1 when the current roll does not fulfill the preconditions for this case
     *         returns the amount of points you get for this case when x > 0
     */
    public int checkp0(int[] a) {
        if (specPoints || numberp0 != 0) return -1;
        int p0sum = 0;
        int i = 0;
        if (a[0] != 1) return -1;
        while (a[i] == 1) {
            p0sum++;
            if (i == 4) break;
            i++;
        }
        if (isCheckingForPossibilities) return p0sum;
        numberp0 = p0sum;
        checkTotal();
        return numberp0;
    }

    /**
     * Checks for amount of twos.
     * Multiplies the amount by 2.
     * @param a int array with the current roll
     * @return returns -1 when the current roll does not fulfill the preconditions for this case
     *         returns the amount of points you get for this case when x > 0
     */
    public int checkp1(int[] a) {
        if (specPoints || numberp1 != 0) return -1;
        int p1sum = 0;
        int firstAppear = -1;
        for (int i = 0; i < 5; i++) {
            if (a[i] == 2) {
                firstAppear = i;
                break;
            }
        }
        if (firstAppear == -1) return -1;
        while (a[firstAppear] == 2) {
            p1sum++;
            if (firstAppear == 4) break;
            firstAppear++;
        }
        if (isCheckingForPossibilities) return p1sum * 2;
        numberp1 = p1sum * 2;
        checkTotal();
        return numberp1;
    }

    /**
     * Checks for amount of threes.
     * Multiplies the amount by 3.
     * @param a int array with the current roll
     * @return returns -1 when the current roll does not fulfill the preconditions for this case
     *         returns the amount of points you get for this case when x > 0
     */
    public int checkp2(int[] a) {
        if (specPoints || numberp2 != 0) return -1;
        int p2sum = 0;
        int firstAppear = -1;
        for (int i = 0; i < 5; i++) {
            if (a[i] == 3) {
                firstAppear = i;
                break;
            }
        }
        if (firstAppear == -1) return -1;
        while (a[firstAppear] == 3) {
            p2sum++;
            if (firstAppear == 4) break;
            firstAppear++;
        }
        if (isCheckingForPossibilities) return p2sum * 3;
        numberp2 = p2sum * 3;
        checkTotal();
        return numberp2;
    }

    /**
     * Checks for amount of fours.
     * Multiplies the amount by 4.
     * @param a int array with the current roll
     * @return returns -1 when the current roll does not fulfill the preconditions for this case
     *         returns the amount of points you get for this case when x > 0
     */
    public int checkp3(int[] a) {
        if (specPoints || numberp3 != 0) return -1;
        int p3sum = 0;
        int firstAppear = -1;
        for (int i = 0; i < 5; i++) {
            if (a[i] == 4) {
                firstAppear = i;
                break;
            }
        }
        if (firstAppear == -1) return -1;
        while (a[firstAppear] == 4) {
            p3sum++;
            if (firstAppear == 4) break;
            firstAppear++;
        }
        if (isCheckingForPossibilities) return p3sum * 4;
        numberp3 = p3sum * 4;
        checkTotal();
        return numberp3;
    }

    /**
     * Checks for amount of fives.
     * Multiplies the amount by 5.
     * @param a int array with the current roll
     * @return returns -1 when the current roll does not fulfill the preconditions for this case
     *         returns the amount of points you get for this case when x > 0
     */
    public int checkp4(int[] a) {
        if (specPoints || numberp4 != 0) return -1;
        int p4sum = 0;
        int firstAppear = -1;
        for (int i = 0; i < 5; i++) {
            if (a[i] == 5) {
                firstAppear = i;
                break;
            }
        }
        if (firstAppear == -1) return -1;
        while (a[firstAppear] == 5) {
            p4sum++;
            if (firstAppear == 4) break;
            firstAppear++;
        }
        if (isCheckingForPossibilities) return p4sum * 5;
        numberp4 = p4sum * 5;
        checkTotal();
        return numberp4;
    }

    /**
     * Checks for amount of sixes.
     * Multiplies the amount by 6.
     * @param a int array with the current roll
     * @return returns -1 when the current roll does not fulfill the preconditions for this case
     *         returns the amount of points you get for this case when x > 0
     */
    public int checkp5(int[] a) {
        if (specPoints || numberp5 != 0) return -1;
        int p5sum = 0;
        int firstAppear = -1;
        for (int i = 0; i < 5; i++) {
            if (a[i] == 6) {
                firstAppear = i;
                break;
            }
        }
        if (firstAppear == -1) return -1;
        while (a[firstAppear] == 6) {
            p5sum++;
            if (firstAppear == 4) break;
            firstAppear++;
        }
        if (isCheckingForPossibilities) return p5sum * 6;
        numberp5 = p5sum * 6;
        checkTotal();
        return numberp5;
    }

    /**
     * p6 is the total score of the first row
     */
    public void checkp6() {
        int p6sum = numberp0
                + numberp1
                + numberp2
                + numberp3
                + numberp4
                + numberp5;
        controller.update6(p6sum);
        numberp6 = p6sum;
    }

    /**
     * Checks if there are 3 of a kind
     * Counts the sum of all eyes
     * @param a int array with the current roll
     * @return returns -1 when the current roll does not fulfill the preconditions for this case
     *         returns the amount of points you get for this case when x > 0
     */
    public int checkp7(int[] a) {
        if (specPoints || numberp7 != 0) return -1;
        for (int i = 0; i < 3; i++) {
            if (a[i] == a[i+1] && a[i] == a[i+2]) {
                int p7sum = a[0] + a[1] + a[2] + a[3] + a[4];
                if (isCheckingForPossibilities) return p7sum;
                numberp7 = p7sum;
                checkTotal();
                return numberp7;
            }
        }
        return -1;
    }

    /**
     * Checks if there are 4 of a kind
     * Counts the sum of all eyes
     * @param a int array with the current roll
     * @return returns -1 when the current roll does not fulfill the preconditions for this case
     *         returns the amount of points you get for this case when x > 0
     */
    public int checkp8(int[] a) {
        if (specPoints || numberp8 != 0) return -1;
        for (int i = 0; i < 2; i++) {
            if (a[i] == a[i+1] && a[i] == a[i+2] && a[i] == a[i+3]) {
                int p8sum = a[0] + a[1] + a[2] + a[3] + a[4];
                if (isCheckingForPossibilities) return p8sum;
                numberp8 = p8sum;
                checkTotal();
                return numberp8;
            }
        }
        return -1;
    }

    /**
     * Checks if there are 2 of one kind, and 3 of another kind
     * Would give 25 points
     * @param a int array with the current roll
     * @return returns -1 when the current roll does not fulfill the preconditions for this case
     *         returns the amount of points you get for this case when x > 0
     */
    public int checkp9(int[] a) {
        if (specPoints || numberp9 != 0) return -1;
        if (a[0] == a[4]) return -1;
        if (a[0] == a[1]) {
            if (a[0] == a[2]) {
                if (a[3] == a[4]) {
                    if (isCheckingForPossibilities) return 25;
                    numberp9 = 25;
                    checkTotal();
                    return numberp9;
                } else {
                    return -1;
                }
            }
            if (a[2] == a[3] && a[2] == a[4]) {
                if (isCheckingForPossibilities) return 25;
                numberp9 = 25;
                checkTotal();
                return numberp9;
            }
        }
        return -1;
    }

    /**
     * Checks if there is a small straight
     * Would give 30 points
     * @param a int array with the current roll
     * @return returns -1 when the current roll does not fulfill the preconditions for this case
     *         returns the amount of points you get for this case when x > 0
     */
    public int checkp10(int[] a) {
        if (specPoints || numberp10 != 0) return -1;
        int[] newA = removeDuplicates(a);
        if (newA.length < 3) {
            return -1;
        }
        int startNumber;
        for (int i = 0; i < 3; i++) {
            startNumber = newA[i];
            if (newA[i + 1] == startNumber + 1 && newA[i + 2] == startNumber + 2) {
                if (isCheckingForPossibilities) return 30;
                numberp10 = 30;
                checkTotal();
                return numberp10;
            }
        }
        return -1;
    }

    /**
     * Removes the duplicates of a given array
     * This method gets used by checkp10 (small straight)
     * @param a array to remove all duplicates from
     * @return array with no duplicates
     */
    public int[] removeDuplicates(int[] a) {
        int n = a.length;
        int[] temp = new int[n];
        int j = 0;
        for (int i=0; i<n-1; i++){
            if (a[i] != a[i+1]){
                temp[j++] = a[i];
            }
        }
        temp[j++] = a[n-1];
        return temp;
    }

    /**
     * Checks if there is a large straight
     * Would give 40 points
     * @param a int array with the current roll
     * @return returns -1 when the current roll does not fulfill the preconditions for this case
     *         returns the amount of points you get for this case when x > 0
     */
    public int checkp11(int[] a) {
        if (specPoints || numberp11 != 0) return -1;
        int p11sum;
        if ((a[0] == 1
                && a[1] == 2
                && a[2] == 3
                && a[3] == 4
                && a[4] == 5)
        || (a[0] == 2
                && a[1] == 3
                && a[2] == 4
                && a[3] == 5
                && a[4] == 6)) {
            p11sum = 40;
            if (isCheckingForPossibilities) return p11sum;
            checkTotal();
            numberp11 = p11sum;
            return p11sum;
        } else {
            return -1;
        }
        //checkTotal();
        //return p11sum;
    }

    /**
     * Checks if there are 5 of a kind
     * Would give 50
     * @param a int array with the current roll
     * @return returns -1 when the current roll does not fulfill the preconditions for this case
     *         returns the amount of points you get for this case when x > 0
     */
    public int checkp12(int[] a) {
        if (specPoints || numberp12 != 0) return -1;
        if (a[0] == a[4]) {
            if (isCheckingForPossibilities) return 50;
            numberp12 = 50;
            checkTotal();
            return numberp12;
        } else {
            return -1;
        }
    }

    /**
     * p13 is the total score of the second row
     */
    public void checkp13() {
        int p13sum = numberp7
                + numberp8
                + numberp9
                + numberp10
                + numberp11
                + numberp12;
        controller.update13(p13sum);
        numberp13 = p13sum;
    }

    /**
     * Sums up all eyes.
     * There are no pre-conditions for this case.
     * @param a int array with the current roll
     * @return returns the amount of points you get for this case when x > 0
     */
    public int checkp14(int[] a) {
        if (specPoints || numberp14 != 0) return -1;
        if (isCheckingForPossibilities) return a[0] + a[1] + a[2] + a[3] + a[4];
        numberp14 = a[0] + a[1] + a[2] + a[3] + a[4];
        checkTotal();
        return numberp14;
    }

    /**
     * Checks if the total score of the upper row (p6) is greater or equal than 63.
     * If so, the player will get a bonus of 35 points.
     * If not, no action will be done.
     */
    public void checkp15() {
        if (numberp6 >= 63) {
            controller.update15(35);
            numberp15 = 35;
        } else {
            numberp15 = 0;
        }
    }

    /**
     * Checks the total amount of points for each row
     */
    public void checkTotal() {
        checkp6();
        checkp13();
        checkp15();
        numberp16 = numberp6 + numberp13 + numberp14 + numberp15;
        controller.update16(numberp16);
        log.info("Total is: " + numberp16);
    }
}
