package library;
import java.util.*;
/**
 * Represents a library that manages publications and patrons.
 * 
 * @author Alok Jha
 * @version 1.0
 * @since 1.0
 * @license.agreement Gnu General Public License version 3
 */
public class Library {

  /**
   * The name of the library.
   */
  private String name;

  /** 
   * The list of publications in the library.
   */
  private ArrayList<Publication> publications = new ArrayList<>();

  /**
   * The list of patrons registered with the library.  
   */
  private ArrayList<Patron> patrons = new ArrayList<>();
   
  /**
   * Creates a new Library with the given name.
   *  
   * @param name The name of the library
   */
  public Library(String name) {
    this.name = name;
  }

  /**
   * Adds a publication to the library.
   * 
   * @param publication The publication to add
   */
  public void addPublication(Publication publication) {
    this.publications.add(publication);
  }

  /**
   * Adds a patron to the library. 
   * 
   * @param patron The patron to add
   */
  public void addPatron(Patron patron) {
    this.patrons.add(patron);
  }
  
  /**
   * Gets the list of publications in the library.
   *
   * @return The list of publications
   */
  public ArrayList<Publication> getPublications() {
    return publications;
  }

  /**
   * Gets the list of patrons registered with the library.
   *
   * @return The list of patrons
   */
  public ArrayList<Patron> getPatrons() {
    return patrons;
  }

  /**
   * Generates a formatted string of all the patrons.
   * 
   * @return A string containing the list of patrons
   */
  public String patronMenu() {
    String result = "Patrons\n\n";

    for (int i = 0; i < patrons.size(); i++) {
      Patron patron = patrons.get(i);
      String patronInfo = String.format("%d) %s\n", i, patron.toString());
      result += patronInfo;
    }

    return result;
  }
  
  /**
   * Checks out a publication to a patron.
   *  
   * @param publicationIndex The index of the publication to check out 
   * @param patronIndex The index of the patron checking out
   * @return The index of the checked out publication
   */
  public int checkOut(int publicationIndex, int patronIndex) {
    Publication publication = publications.get(publicationIndex);
    Patron patron = patrons.get(patronIndex);
    publication.checkOut(patron);

    return publicationIndex;
  }

  /**
   * Checks in a publication.
   * 
   * @param publicationIndex The index of the publication to check in
   * @return The index of the checked in publication
   */
  public int checkIn(int publicationIndex) {
    if (publicationIndex >= 0 && publicationIndex < publications.size()) {
      Publication publication = publications.get(publicationIndex);
      publication.checkIn();
    }
    return publicationIndex;
  }

  /**
   * Generates a formatted string of all publications in the library.
   * 
   * @return A string containing the list of publications
   */
  @Override
  public String toString() {
    String result = String.format("%s", name);

    for (int i = 0; i < publications.size(); i++) {
      Publication publication = publications.get(i);
      String info = String.format("%d) %s\n", i, publication);
      result += info;
    }

    return result;
  }

}
