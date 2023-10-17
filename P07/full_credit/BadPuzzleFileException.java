import java.io.IOException;

/**
 * Exception class for representing a malformed puzzle file.
 */
public class BadPuzzleFileException extends IOException {
    /**
     * Constructs a BadPuzzleFileException with the specified puzzle name and line number.
     *
     * @param name      The name of the malformed puzzle.
     * @param lineNumber The line number where the issue occurred.
     */
    public BadPuzzleFileException(String name, int lineNumber) {
        super("Malformed puzzle '" + name + "' at line " + lineNumber);
    }
}
