package library;

import java.util.*;

public class Library {
    // Attributes
    private String name;
    private ArrayList<Publication> publications = new ArrayList<>();
    private ArrayList<Patron> patrons = new ArrayList<>();

    // Constructor
    public Library(String name) {
        this.name = name;
    }

    // Methods
    public void addPublication(Publication publication) {
        this.publications.add(publication);
    }

    public void addPatron(Patron patron) {
        this.patrons.add(patron);
    }

    public String patronMenu() {
        String result = "Patrons\n\n";

        for (int i = 0; i < patrons.size(); i++) {
            Patron patron = patrons.get(i);
            String patronInfo = String.format("%d) %s\n", i, patron.toString());
            result += patronInfo;
        }
        return result;
    }

    public int checkOut(int publicationIndex, int patronIndex) {
        try {
            Publication publication = publications.get(publicationIndex);
            Patron patron = patrons.get(patronIndex);
            publication.checkOut(patron);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nIndex out of bounds\nPlease enter a number in the range.");
            System.exit(-1);
        }
        return publicationIndex;
    }

    @Override
    public String toString() {
        String result = String.format("%s\n\n", name);

        for (int i = 0; i < publications.size(); i++) {
            Publication publication = publications.get(i);
            String publicationInfo = String.format("%d) %s\n", i, publication.toString());
            result += publicationInfo;
        }
        return result;
    }
}
