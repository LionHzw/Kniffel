package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Highscore {

    private final String[] highscores = new String[10];

    public Highscore() {
        createFile();
        writeToFile("Lion", new int[] {1, 2, 3, 4});
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

    public void writeToFile(String name, int[] result) {
        try {
            FileWriter myWriter = new FileWriter("src/resources/highscores.txt");
            myWriter.write(name + "\n" + Arrays.toString(result));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
