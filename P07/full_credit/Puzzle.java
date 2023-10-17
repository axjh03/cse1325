import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a word search puzzle, including the puzzle board and a list of words to find.
 */
public class Puzzle {
    private String name;
    private ArrayList<String> board = new ArrayList<>();
    private ArrayList<String> words = new ArrayList<>();

    /**
     * Constructs a Puzzle from a name and a BufferedReader containing puzzle data.
     *
     * @param name The name of the puzzle.
     * @param br   A BufferedReader containing puzzle data.
     * @throws IOException If there is an I/O error while reading the puzzle data.
     */
    public Puzzle(String name, BufferedReader br) throws IOException {
        this.name = name;

        int lineNumber = 0; // for reporting in exceptions
        String line = null; // each line read from br

        // Skip the solution
        while (!getLine(br).equals("Solution:")) {
            ++lineNumber;
        } // find "Solution:"
        while (!getLine(br).isEmpty()) {
            ++lineNumber;
        } // skip solution
        while ((line = getLine(br)).isEmpty()) {
            ++lineNumber;
        } // skip spaces before puzzle

        // Load the puzzle
        while (!line.isEmpty()) {
            board.add(line);
            line = getLine(br);
            ++lineNumber;
            if (line == null) // can't end puzzle before word list
                throw new BadPuzzleFileException(name, lineNumber);
        }
        // Load the word list
        while (line != null) {
            for (String word : line.split("\\s+")) {
                if (!word.isEmpty()) words.add(word);
            }
            line = br.readLine();
            ++lineNumber;
        }
    }

    /**
     * Gets the name of the puzzle.
     *
     * @return The name of the puzzle.
     */
    public String name() {
        return name;
    }

    /**
     * Gets the words to find in the puzzle.
     *
     * @return An array of words to find.
     */
    public String[] getWords() {
        return words.toArray(new String[0]);
    }

    /**
     * Gets the width of the puzzle board.
     *
     * @return The width of the puzzle board.
     */
    public int width() {
        return board.get(0).length();
    }

    /**
     * Gets the height of the puzzle board.
     *
     * @return The height of the puzzle board.
     */
    public int height() {
        return board.size();
    }

    /**
     * Gets the character at the specified position on the puzzle board.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The character at the specified position.
     */
    public char getChar(int x, int y) {
        return board.get(y).charAt(x);
    }

    /**
     * Gets the character at the specified position on the puzzle board in a specified direction.
     *
     * @param x        The x-coordinate.
     * @param y        The y-coordinate.
     * @param dir      The direction in which to search.
     * @param numChars The number of characters to look ahead.
     * @return The character at the specified position in the given direction.
     */
    public char getChar(int x, int y, Direction dir, int numChars) {
        try {
            return board.get(y + dir.deltaY() * numChars).charAt(x + dir.deltaX() * numChars);
        } catch (IndexOutOfBoundsException e) { // in x or y direction
            return '\0';
        }
    }

    /**
     * Remove all whitespace as lines are read.
     *
     * @param br The BufferedReader to read lines from.
     * @return A line with all whitespace removed.
     * @throws IOException If there is an I/O error while reading lines.
     */
    private String getLine(BufferedReader br) throws IOException {
        return br.readLine().replaceAll("\\s", "");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("From file " + name + "\n\n");
        for (String s : board) sb.append(s + '\n');
        sb.append("\n\n");
        int wordsPerLine = 3;
        for (String word : words) {
            sb.append(String.format("%28s  ", word));
            if (--wordsPerLine == 0) {
                wordsPerLine = 3;
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
