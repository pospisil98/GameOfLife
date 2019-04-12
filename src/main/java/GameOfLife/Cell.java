package GameOfLife;


/**
 * Class representing Cell in our automata
 *
 */
class Cell {
    private boolean isAlive;

    Cell(boolean status)
    {
        isAlive = status;
    }

    boolean isAlive()
    {
        return this.isAlive;
    }

    void die()
    {
        this.isAlive = false;
    }

    void reborn() {
        this.isAlive = true;
    }
}
