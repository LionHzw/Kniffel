package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Highscore {

    private static ArrayList<SingleHighscore> arrayList;

    public Highscore() {
        arrayList = new ArrayList<>();
        readFile();
        writeToFile();
    }

    public void addHighscore(SingleHighscore newHighscore) {
        arrayList.add(newHighscore);
    }

    public ArrayList<SingleHighscore> getArrayList() {
        return arrayList;
    }

    public void createFile() {
        try {
            File highscore = new File("src/resources/highscores.txt");
            if (highscore.createNewFile()) {
                System.out.println("File created: " + highscore.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToFile() {
        sortScores();

        /*for (SingleHighscore singleHighscore : arrayList) {
            System.out.println(singleHighscore.getTotal() + " " + singleHighscore.getName() + "\n");
        }
        System.out.println("done sorting");*/

        try {
            FileWriter myWriter = new FileWriter("src/resources/highscores.txt");
            for (SingleHighscore singleHighscores : arrayList)
            myWriter.write(singleHighscores.getTotal() + " " + singleHighscores.getName() + " " + Arrays.toString(singleHighscores.getPoints()) + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readFile() {
        try {
            File highscore = new File("src/resources/highscores.txt");
            Scanner myReader = new Scanner(highscore);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] currentLineArray = line.split(" ");
                int total = Integer.parseInt(currentLineArray[0]);
                String name = currentLineArray[1];
                int[] points = createArrayFromString(currentLineArray);
                arrayList.add(new SingleHighscore(total, name, points));
            }
            for (SingleHighscore singleHighscore : arrayList) {
                System.out.println(singleHighscore.getTotal() + " " + singleHighscore.getName() + "\n");
            }
            System.out.println("done reading");
            myReader.close();
        } catch (FileNotFoundException e) {
            createFile();
            readFile();
        } catch (NumberFormatException nfe) {
            System.out.println("help");
        }

    }

    /**
     * Points Array is 17 big
     * @return
     */
    public int[] createArrayFromString(String[] stringarray) {
        int[] intarray = new int[17];
        for (int i = 0; i < 17; i++) {
            try {
                String str = stringarray[i+2];
                str = str.replaceAll("\\D+","");
                intarray[i] = Integer.parseInt(str);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
        return intarray;
    }

    /**
     * Transform arraylist into a hashmap
     * Key: total score
     * Value: Instance of SingleHighscore Object
     *
     * In case of collision: Apply linear probing
     */
    public void sortScores() {
        HashMap<Integer, SingleHighscore> hashMap = new HashMap<>();
        /*for (int i = 0; i < arrayList.size(); i++) {
            //Collision detection
            if (!hashMap.containsKey(arrayList.get(i).getTotal())) {
                hashMap.put(arrayList.get(i).getTotal(), arrayList.get(i));
            } else {
                int j = 0;
                while (hashMap.containsKey(arrayList.get(i).getTotal() + j)) { //Searching for the next free spot
                    j++;
                }
                hashMap.put(arrayList.get(i).getTotal() + j, arrayList.get(i));
            }
        }*/

        for (int i = 0; i < arrayList.size(); i++) {
            //Collision detection
            if (!hashMap.containsKey(arrayList.get(i).getTotal())) {
                hashMap.put(arrayList.get(i).getTotal(), arrayList.get(i));
            } else {
                int j = arrayList.get(i).getTotal();
                while (hashMap.containsKey(j)) {
                    j--;
                }
                hashMap.put(j, arrayList.get(i));
            }
        }
        /*for (SingleHighscore singleHighscore : hashMap.values()) {
            System.out.println(singleHighscore.getTotal() + " " + singleHighscore.getName());
        }*/
        for (int i = 375; i > 0; i--) {
            if (hashMap.containsKey(i)) {
                System.out.println("Key: " + i + ", HighscoreTotal: " + hashMap.get(i).getTotal() + ", HighscoreName: " + hashMap.get(i).getName());
            }
        }
        arrayList.clear();
        for (int i = 375; i >= 0; i--) {
            if (arrayList.size() == 10) return;
            if (hashMap.containsKey(i)) {
                arrayList.add(hashMap.get(i));
                //System.out.println(hashMap.get(i).getName());
            }
        }
    }
}
