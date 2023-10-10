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
     * Checks in a previously The method checkOut(Patron) in the type Publication is not applicable for the arguments ()checked out publication.
     */

    /**
     * Checks out this publication to the given patron.
     * 
     * @param patron The patron checking out the publication
     */

     public void checkOut(Patron patron) {
        if (loanedTo == null) {
            loanedTo = patron;
            dueDate = LocalDate.now().plusDays(14); // Set due date to 14 days from now
        }
    }
    

    /**
     * Checks in a previously checked out publication.
     */

     public void checkIn() {
        loanedTo = null;
        dueDate = null;
    }

    public Publication(BufferedReader br) throws IOException {
        type = br.readLine(); // Read the type of publication
        title = br.readLine(); // Read the title
        author = br.readLine(); // Read the author
        
        String copyrightStr = br.readLine();
        if ("checked in".equals(copyrightStr)) {
            copyright = 0; // Set a default value for copyright when checked in
        } else {
            copyright = Integer.parseInt(copyrightStr); // Parse the copyright year
        }
        
        String loanedToStatus = br.readLine();
        if ("checked out".equals(loanedToStatus)) {
            loanedTo = new Patron(br); // Read and construct the loanedTo Patron object
            dueDate = LocalDate.parse(br.readLine()); // Read and parse the dueDate
        } else {
            loanedTo = null; // Publication is checked in
            dueDate = null; // Due date is not applicable
        }
    }
    
    

    public void save(BufferedWriter bw) throws IOException {
        bw.write(type + ',');  // Write the type of publication
        bw.write(title + ','); // Write the title
        bw.write(author + ','); // Write the author
        bw.write(Integer.toString(copyright) + ','); // Write the copyright year

        if (loanedTo == null) {
            bw.write("checked in,"); // Publication is checked in
        } else {
            bw.write("checked out,"); // Publication is checked out
            loanedTo.save(bw); // Write the loanedTo information
            bw.write(dueDate.toString()); // Write the dueDate
        }
    }

    public void load(BufferedReader br) throws IOException {
        // Read other fields (type, title, author, copyright)
        type = br.readLine();
        title = br.readLine();
        author = br.readLine();
        copyright = Integer.parseInt(br.readLine());
    
        // Read checked-in/checked-out status
        String status = br.readLine();
        boolean checkedOut;
        if (status.equals("checked in")) {
            checkedOut = false;
        } else if (status.equals("checked out")) {
            checkedOut = true;
            // Read loanedTo details (you might need to modify this part)
            String loanedToName = br.readLine(); // Assuming loanedTo has a setName() method
            loanedTo = new Patron(loanedToName, ""); // Create a Patron object with the name
            // Read and parse dueDate as a string back to a LocalDate
            String dueDateString = br.readLine();
            dueDate = LocalDate.parse(dueDateString);
        }
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
