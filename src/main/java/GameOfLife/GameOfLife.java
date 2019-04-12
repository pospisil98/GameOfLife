package GameOfLife;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import static GameOfLife.Config.*;

/**
 * Class encapsulating all important parts of Conway's game of life
 *
 */
class GameOfLife {
    private Cell[] grid;

    GameOfLife() {
        this.grid = null;
    }


    /**
     * Simulate next generation of automata - compute new states
     */
    void nextGeneration() {
        // creates grid for next generation and initializes it to the same values
        Cell[] newGrid = new Cell[HEIGHT * WIDTH];
        deepCopyGrid(newGrid);

        int aliveCount;

        for (int i = 0; i < grid.length; i++) {
            aliveCount = getAliveCountAround(i);

            // implementing rules for classic Conway's game of life
            if (grid[i].isAlive()) {
                if (aliveCount < 2 || aliveCount > 3) {
                    newGrid[i].die();
                }
            } else {
                if (aliveCount == 3) {
                    newGrid[i].reborn();
                }
            }
        }

        // swap old and new grid to make changes
        this.grid = newGrid;
    }

    /**
     * Prints one generation and flushes command line after every generation
     */
    void draw() {
        // escape sequence for command line deletion
        System.out.print("\033[H\033[2J");
        System.out.flush();

        for (int i = 0; i < grid.length; i++) {
            if (grid[i].isAlive()) {
                System.out.print(ALIVE);
            } else {
                System.out.print(DEAD);
            }

            // print linebreak at appropriate place
            if ((i + 1) % WIDTH == 0) {
                System.out.print("\n");
            }
        }
    }

    /**
     * Loads game input state from given file
     *
     * @param filename String with path to input file
     */
    void loadFromFile(String filename) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        StringTokenizer st;
        String line;
        int arrayPosition = 0;

        try {
            // load first row with number of generations
            st = new StringTokenizer(br.readLine());
            GENERATIONS = Integer.valueOf(st.nextToken());

            // load dimensions of automata
            st = new StringTokenizer(br.readLine());
            WIDTH = Integer.valueOf(st.nextToken());
            HEIGHT = Integer.valueOf(st.nextToken());

            // initializes grid array to correct size
            initGrid();

            // process given input symbols for cells
            for (int i = 0; i < HEIGHT; i++) {
                st = new StringTokenizer(br.readLine());
                line = String.valueOf(st.nextToken());

                for (int j = 0; j < line.length(); j++) {
                    grid[arrayPosition] = createCellFromChar(line.charAt(j));
                    arrayPosition++;
                }
            }
        } catch (IOException e) {
            System.out.println("Weird input format, please repair it.");
        }

    }

    /**
     * Function that parses input character to cell with correct state
     *
     * @param c Input character determining cell state
     * @return Cell with correct state
     */
    private Cell createCellFromChar(char c) {
        if (c == ALIVE) {
            return new Cell(true);
        } else {
            return new Cell(false);
        }
    }

    /**
     * Returns number of alive cells in one-distance surrounding
     *
     * @param arrayIndex Index of cell to examine
     * @return Number of cells alive around
     */
    private int getAliveCountAround(int arrayIndex) {
        // get cells coords
        int cellX = getXposFromArrayIndex(arrayIndex);
        int cellY = getYposFromArrayIndex(arrayIndex);

        int alive = 0;

        // try every direction in surrounding tiles and possibly increase count
        for (Point p: SURROUNDINGS) {
            int x = cellX + p.x;
            int y = cellY + p.y;

            if (Point.isOnGrid(x, y))
            {
                if (grid[getArrayIndexFromXY(x, y)].isAlive())
                    alive++;
            }
        }

        return alive;
    }

    /**
     * Returns Y coordinates from given array index
     * @param arrayIndex Index of cell in automata grid array representation
     * @return Number representing Y coords (0 is first)
     */
    private int getYposFromArrayIndex(int arrayIndex) {
        return arrayIndex / WIDTH;
    }

    /**
     * Returns C coordinates from given array index
     * @param arrayIndex Index of cell in automata grid array representation
     * @return Number representing C coords (0 is first)
     */
    private int getXposFromArrayIndex(int arrayIndex) {
        return arrayIndex % WIDTH;
    }

    /**
     * Returns array index from xy coords
     *
     * @param x X position (0 first)
     * @param y Y position (0 first)
     * @return Index in linear grid array
     */
    private int getArrayIndexFromXY(int x, int y) {
        return y * WIDTH + x;
    }

    /**
     * Deep copy of grid state
     *
     * @param newGrid New independent grid filled with values from current grid
     */
    private void deepCopyGrid(Cell[] newGrid) {
        for (int i = 0; i < this.grid.length; i++) {
            newGrid[i] = new Cell(this.grid[i].isAlive());
        }
    }

    /**
     * Inits grid to correct size array
     */
    private void initGrid() {
        this.grid = new Cell[HEIGHT * WIDTH];
    }
}
