package GameOfLife;

import static GameOfLife.Config.*;

/**
 * Class representing point / direction in grid
 *
 */
class Point {
    int x;
    int y;

    Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Check existence of cell in grid
     *
     * @param x X coords of cell
     * @param y Y coords of cell
     * @return Returns true/false depending whether tile is on grid or not
     */
    static boolean isOnGrid(int x, int y) {
        return x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT;
    }
}
