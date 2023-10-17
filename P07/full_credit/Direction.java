/**
 * Enum representing compass directions with corresponding delta values for x and y.
 */
public enum Direction {
    N(0, -1), NE(1, -1), E(1, 0), SE(1, 1), S(0, 1), SW(-1, 1), W(-1, 0), NW(-1, -1);

    private int deltaX;
    private int deltaY;

    /**
     * Constructs a Direction with the given delta values for x and y.
     *
     * @param deltaX The change in x coordinate for this direction.
     * @param deltaY The change in y coordinate for this direction.
     */
    private Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * Gets the change in x coordinate for this direction.
     *
     * @return The change in the x coordinate.
     */
    public int deltaX() {
        return deltaX;
    }

    /**
     * Gets the change in y coordinate for this direction.
     *
     * @return The change in the y coordinate.
     */
    public int deltaY() {
        return deltaY;
    }
}
