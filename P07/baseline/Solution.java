
public class Solution implements Comparable<Solution> {
    public Solution(String name, String word, int x, int y, Direction direction) {
        this.name = name;
        this.word = word;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return String.format("In %s: %s found at (%d,%d,%s)", name, word, x, y, direction);
    }

    // Implement the compareTo method to define the natural order
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
