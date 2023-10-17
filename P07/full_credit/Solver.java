/**
 * A class for solving word search puzzles.
 */
public class Solver {
    private Puzzle puzzle;

    /**
     * Constructs a Solver for a specific puzzle.
     *
     * @param puzzle The puzzle to solve.
     */
    public Solver(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * Implement a brute force search to find a word in the puzzle.
     *
     * @param word The word to search for.
     * @return A Solution object representing the word's location or null if the word is not found.
     */
    public Solution solve(String word) {
        if (word.isEmpty()) return null;
        for (int x = 0; x < puzzle.width(); ++x) {
            for (int y = 0; y < puzzle.height(); ++y) {
                for (Direction d : Direction.values()) {
                    boolean found = true;
                    for (int i = 0; i < word.length(); ++i) {
                        if (word.charAt(i) != puzzle.getChar(x, y, d, i)) {
                            found = false;
                            break;
                        }
                    }
                    if (found)
                        return new Solution(puzzle.name(), word, x, y, d);
                }
            }
        }
        return null;
    }
}
