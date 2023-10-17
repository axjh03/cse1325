/**
 * Represents a solution to a word search puzzle.
 */
public class Solution implements Comparable<Solution> {
    /**
     * Constructs a Solution object with the specified attributes.
     *
     * @param name      The name of the puzzle.
     * @param word      The word that was found.
     * @param x         The x-coordinate (column) where the word starts.
     * @param y         The y-coordinate (row) where the word starts.
     * @param direction The direction in which the word was found.
     */
    public Solution(String name, String word, int x, int y, Direction direction) {
        this.name = name;
        this.word = word;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /**
     * Returns a string representation of the solution.
     *
     * @return A formatted string describing the solution.
     */
    @Override
    public String toString() {
        return String.format("In %s: %s found at (%d,%d,%s)", name, word, x, y, direction);
    }

    /**
     * Compares this solution to another solution for ordering purposes.
     *
     * @param other The other solution to compare to.
     * @return A negative integer, zero, or a positive integer if this solution is less than, equal to, or greater than
     *         the specified solution.
     */
    @Override
    public int compareTo(Solution other) {
        // First, compare by name
        int nameComparison = this.name.compareTo(other.name);

        // If the names are the same, compare by word
        if (nameComparison == 0) {
            return this.word.compareTo(other.word);
        }

        return nameComparison;
    }

    private String name;
    private String word;
    private int x;
    private int y;
    private Direction direction;
}
