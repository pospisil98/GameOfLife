package GameOfLife;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Utils {
    public static void createInput(int rows, int cols, int generations, String worldName) {
        String world = "";
        Random rng = new Random();


        world += generations;
        world += "\n";
        world += rows + " " + cols;
        world += "\n";

        StringBuilder worldBuilder = new StringBuilder(world);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (rng.nextFloat() < 0.5f) {
                    worldBuilder.append(Config.DEAD);
                } else {
                    worldBuilder.append(Config.ALIVE);
                }
            }
            worldBuilder.append("\n");
        }
        world = worldBuilder.toString();

        String filename = "./resources/" + worldName + ".txt";

        try {
            PrintWriter pw = new PrintWriter(filename);
            pw.print(world);
            pw.close();

            System.out.println("World successfully generated and saved.");
        } catch (FileNotFoundException e) {
            System.out.println("Error ocured while saving generated world. Try it again.");
            e.printStackTrace();
        }
    }
}
