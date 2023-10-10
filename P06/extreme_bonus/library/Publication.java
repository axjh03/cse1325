package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Represents a publication in the library.
 */
public class Publication {

    private String title;
    private String author;
    private int copyrightYear;
    private Patron loanedTo;
    private LocalDate dueDate;

    /**
     * Creates a new publication with the provided title, author, and copyright
     * year.
     *
     * @param title         The title of the publication
     * @param author        The author of the publication
     * @param copyrightYear The copyright year of the publication
     */
    public Publication(String title, String author, int copyrightYear) {
        this.title = title;
        this.author = author;
        this.copyrightYear = copyrightYear;
    }

    /**
     * Checks if this publication is currently checked out.
     *
     * @return true if checked out, false if checked in
     */
    public boolean isCheckedOut() {
        return loanedTo != null;
    }

    /**
     * Checks out this publication to the provided patron.
     *
     * @param patron The patron checking out this publication
     */
    public void checkOut(Patron patron) {
        loanedTo = patron;
        dueDate = LocalDate.now().plusDays(14);
    }

    /**
     * Checks in a previously checked out publication.
     */
    public void checkIn() {
        loanedTo = null;
        dueDate = null;
    }

    /**
     * Gets the title of the publication.
     *
     * @return The title of the publication
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the string representation of this publication.
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return toStringBuilder("Book", "");
    }

    /**
     * Generates a string representation of the publication.
     *
     * @param pre The pre-text to include
     * @param mid The mid-text to include
     * @return The generated string
     */
    protected String toStringBuilder(String pre, String mid) {
        String str = pre + " \"" + title + "\" by " + author + " (©" + copyrightYear + ")" + mid;

        if (loanedTo != null) {
            str += "\n\tLoaned to: " + loanedTo + " Due: " + dueDate + "\n";
        }

        return str;
    }

    /**
     * Loads the state of this publication from the provided reader.
     *
     * @param reader The reader to load data from
     */
    public Publication(BufferedReader reader) throws IOException {
        title = reader.readLine();
        author = reader.readLine();
        copyrightYear = Integer.parseInt(reader.readLine());

        String status = reader.readLine();
        if (status.equals("checked in")) {
            loanedTo = null;
            dueDate = null;
        } else {
            String loanedToName = reader.readLine();
            String loanedToEmail = reader.readLine();
            loanedTo = new Patron(loanedToName, loanedToEmail);

            int year = Integer.parseInt(reader.readLine());
            int month = Integer.parseInt(reader.readLine());
            int day = Integer.parseInt(reader.readLine());
            dueDate = LocalDate.of(year, month, day);
        }
    }

    /**
     * Saves the state of this publication to the provided writer.
     *
     * @param writer The writer to save data to
     */
    public void save(BufferedWriter writer) throws IOException {
        writer.write(title + "\n");
        writer.write(author + "\n");
        writer.write("" + copyrightYear + "\n");

        if (loanedTo == null) {
            writer.write("checked in\n");
        } else {
            writer.write("checked out\n");
            writer.write(loanedTo.getName() + "\n");
            writer.write(loanedTo.getEmail() + "\n");
            writer.write("" + dueDate.getYear() + "\n");
            writer.write("" + dueDate.getMonthValue() + "\n");
            writer.write("" + dueDate.getDayOfMonth() + "\n");
        }
    }
}
