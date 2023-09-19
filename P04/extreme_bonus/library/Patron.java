package library;

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
     * Creates a new patron with the given name and email.
     * 
     * @param name  The name of the patron
     * @param email The email address of the patron
     */
    public Patron(String name, String email) {
        this.name = name;
        this.email = email;
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
}
