// Please add 
// +Patron(br: BufferedReader)
// +save(bw: BufferedWriter) 

package library;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Represents a patron of the library.
 */
public class Patron {
    /**
     * The name of the patron.
     */
    private String name;
    /**
     * The email address of the patron.
     */
    private String email;
    /**
     * The list of publications checked out by the patron.
     */
    private ArrayList<Publication> checkedOutPublications = new ArrayList<>();

    /**
     * Creates a new patron with the given name and email.
     *
     * @param name  The name of the patron
     * @param email The email address of the patron
     */
    public Patron(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // New constructor to read Patron data from a BufferedReader
    public Patron(BufferedReader br) throws IOException {
        name = br.readLine();
        email = br.readLine();
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + "," + email);
        bw.newLine();
    }
    

    /**
     * Returns a string representation of the patron.
     *
     * @return The patron's name and email address
     */
    @Override
    public String toString() {
        return name + " (" + email + ")";
    }

    /**
     * Checks if the patron has any publications checked out.
     *
     * @return true if the patron has publications checked out, false otherwise
     */
    public boolean hasCheckedOutPublications() {
        return !checkedOutPublications.isEmpty();
    }

    /**
     * Checks out a publication to the patron.
     *
     * @param publication The publication to check out
     */
    public void checkOutPublication(Publication publication) {
        checkedOutPublications.add(publication);
    }

    /**
     * Checks in a publication that was previously checked out by the patron.
     *
     * @param publication The publication to check in
     */
    public void checkInPublication(Publication publication) {
        checkedOutPublications.remove(publication);
    }

    public String getName() {
        return null;
    }
}
