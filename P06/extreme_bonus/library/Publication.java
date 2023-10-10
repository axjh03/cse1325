package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Represents a publication in the library.
 */
public class Publication {
    public String type;
    private String title;
    private String author;
    private int copyright;
    private Patron loanedTo;
    private LocalDate dueDate;

    // Constructor

    /**
     * Details of the Publication
     * 
     * @param title     Title of the publication
     * @param author    Author of the publication
     * @param copyright Copyright year of the publication
     */
    public Publication(String type, String title, String author, int copyright) {
        this.type = type; // Set the type here
        this.title = title;
        this.author = author;
        this.copyright = copyright;
    
        if (copyright < 1900) {
            throw new IllegalArgumentException("Copyright must be 1900 or later");
        }
    }
    

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCopyrightYear() {
        return copyright;
    }

    /**
     * Checks if this publication is currently checked out.
     *
     * @return true if the publication is checked out, false otherwise
     */
    public boolean isCheckedOut() {
        return loanedTo != null;
    }

    /**
     * Checks in a previously checked out publication.
     */

    /**
     * Checks out this publication to the given patron.
     * 
     * @param patron The patron checking out the publication
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

    public Publication(BufferedReader br) throws IOException {
        title = br.readLine();
        author = br.readLine();
        copyright = Integer.parseInt(br.readLine());

        String loanedToStatus = br.readLine();
        if (loanedToStatus.equals("checked out")) {
            loanedTo = new Patron(br);
            dueDate = LocalDate.parse(br.readLine());
        } else {
            loanedTo = null;
            dueDate = null;
        }
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(type + "," + title + "," + author + "," + copyright);
        if (type.equalsIgnoreCase("Video")) {
            bw.write(",");
        }
        //bw.newLine();
    }

    /**
     * Generates a string representation of the publication.
     * 
     * @param pre The pre-text to include
     * @param mid The mid-text to include
     * @return The generated string
     */
    protected String toStringBuilder(String pre, String mid) {
        String str = pre + " \"" + title + "\" by " + author + " (©" + copyright + ")" + mid;

        if (loanedTo != null) {
            str += "\n\tLoaned to: " + loanedTo + " Due: " + dueDate + "\n";
        }

        return str;
    }

    @Override
    public String toString() {
        return toStringBuilder("Book", "");
    }
}
