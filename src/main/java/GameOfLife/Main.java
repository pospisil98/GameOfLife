package GameOfLife;

import java.util.concurrent.TimeUnit;

import static GameOfLife.Config.*;

/**
 * Main class of GameOfLife project created for EmbedIT practice
 *
 * Input files are located in resources folder.
 * All the important config stuff is in Config.java file
 *
 * INPUT FILE FORMAT:
 * ----------
 * G
 * X Y
 * ...X.
 * .
 * X
 * .
 * ----------
 *
 * G    = number of generations to live
 * X    = width of grid
 * Y    = height of grid
 * ./X  = dead/alive characters
 *
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        GameOfLife g = new GameOfLife();

        g.loadFromFile("./resources/input1.txt");

        for (int i = 0; i < GENERATIONS; i++) {
            g.draw();
            g.nextGeneration();
            TimeUnit.MILLISECONDS.sleep(GENERATION_SLEEP_MS);
        }

    }
}
