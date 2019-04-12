package GameOfLife;


/**
 * Class for holding all configuration infos
 *
 */
final class Config {
    // disable instancing of class
    private Config() {}

    /**
     * Number of generations to live
     */
    static int GENERATIONS;

    /**
     * Width of automata grid
     */
    static int WIDTH;

    /**
     * Height of automata grid
     */
    static int HEIGHT;

    /**
     * Character representing cell which is alive
     */
    static final char ALIVE = 'X';

    /**
     * Character representing cell which is dead
     */
    static final char DEAD = '.';

    /**
     * Integer representing time in ms to be waited after every generation
     */
    static final int GENERATION_SLEEP_MS = 1000;

    /**
     * Array off points used to discover surroundings of cell
     */
    static final Point[] SURROUNDINGS = {
            new Point(0 ,1), new Point(1, 1), new Point(1, 0),
            new Point(1, -1), new Point(0, -1), new Point(-1, -1),
            new Point(-1, 0), new Point(-1, 1),
    };
}