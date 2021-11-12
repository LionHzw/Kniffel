package sample;


import java.util.Arrays;
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

    public Check(Controller controller) {
        this.controller = controller;
    }

    /**
     *
     * p6 - Gesamt oben
     * p13 - Gesamt unten
     * p15 - Bonus
     * p16 - Gesamt
     */

    public void specPointsTrue() {
        specPoints = true;
    }

    public void specPointsFalse() {
        specPoints = false;
    }

    public boolean getSpecPoints() {
        return specPoints;
    }

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
        numberp0 = p0sum;
        checkTotal();
        return numberp0;
    }

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
        numberp1 = p1sum * 2;
        checkTotal();
        return numberp1;
    }

    /**
     * Checks for 3s
     * @param a array with rolls
     * @return gained points
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
        numberp2 = p2sum * 3;
        checkTotal();
        return numberp2;
    }

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
        numberp3 = p3sum * 4;
        checkTotal();
        return numberp3;
    }

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
        numberp4 = p4sum * 5;
        checkTotal();
        return numberp4;
    }

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
        numberp5 = p5sum * 6;
        checkTotal();
        return numberp5;
    }

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
     * 3er Pasch
     * @param a
     * @return
     */
    public int checkp7(int[] a) {
        if (specPoints || numberp7 != 0) return -1;
        for (int i = 0; i < 3; i++) {
            if (a[i] == a[i+1] && a[i] == a[i+2]) {
                numberp7 = a[0] + a[1] + a[2] + a[3] + a[4];
                checkTotal();
                return numberp7;
            }
        }
        return -1;
    }

    /**
     * 4er Pasch
     * @param a
     * @return
     */
    public int checkp8(int[] a) {
        if (specPoints || numberp8 != 0) return -1;
        for (int i = 0; i < 2; i++) {
            if (a[i] == a[i+1] && a[i] == a[i+2] && a[i] == a[i+3]) {
                numberp8 = a[0] + a[1] + a[2] + a[3] + a[4];
                checkTotal();
                return numberp8;
            }
        }
        return -1;
    }

    /**
     * Full House
     * @param a
     * @return
     */
    public int checkp9(int[] a) {
        if (specPoints || numberp9 != 0) return -1;
        if (a[0] == a[4]) return -1;
        if (a[0] == a[1]) {
            if (a[0] == a[2]) {
                if (a[3] == a[4]) {
                    numberp9 = 25;
                    checkTotal();
                    return numberp9;
                } else {
                    return -1;
                }
            }
            if (a[2] == a[3] && a[2] == a[4]) {
                numberp9 = 25;
                checkTotal();
                return numberp9;
            }
        }
        return -1;
    }

    /**
     * Kleine Straße
     * @param a
     * @return
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
                numberp10 = 30;
                checkTotal();
                return numberp10;
            }
        }
        return -1;
    }

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
     * Große Straße
     * @param a
     * @return
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
            numberp11 = p11sum;
        } else {
            return -1;
        }
        checkTotal();
        return p11sum;
    }

    /**
     * Kniffel
     * @param a
     * @return
     */
    public int checkp12(int[] a) {
        if (specPoints || numberp12 != 0) return -1;
        if (a[0] == a[4]) {
            numberp12 = 50;
            checkTotal();
            return numberp12;
        } else {
            return -1;
        }
    }

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

    public int checkp14(int[] a) {
        if (specPoints || numberp14 != 0) return -1;
        numberp14 = a[0] + a[1] + a[2] + a[3] + a[4];
        checkTotal();
        return numberp14;
    }

    public void checkp15() {
        if (numberp6 >= 63) {
            controller.update15(35);
            numberp15 = 35;
        } else {
            numberp15 = 0;
        }
    }

    public void checkTotal() {
        checkp6();
        checkp13();
        checkp15();
        numberp16 = numberp6 + numberp13 + numberp14 + numberp15;
        controller.update16(numberp16);
        log.info("Total is: " + numberp16);
    }

    public void printCurrentDices(int[] a) {
        System.out.println(a[0]);
        System.out.println(a[1]);
        System.out.println(a[2]);
        System.out.println(a[3]);
        System.out.println(a[4]);
    }
}
