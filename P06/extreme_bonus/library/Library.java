// Please add 
// +Library(br: BufferedReader)
// +save(bw: BufferedWriter) 

package library;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;

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

  // New constructor to read Library data from a BufferedReader
  public Library(BufferedReader br) throws IOException {
    name = br.readLine();
    int numPublications = Integer.parseInt(br.readLine());
    for (int i = 0; i < numPublications; i++) {
        String type = br.readLine();
        if (type.equalsIgnoreCase("Book")) {
            publications.add(new Publication(br));
        } else if (type.equalsIgnoreCase("Video")) {
            publications.add(new Video(br));
        }
    }
    int numPatrons = Integer.parseInt(br.readLine());
    for (int i = 0; i < numPatrons; i++) {
        patrons.add(new Patron(br));
    }
}


public void save(BufferedWriter bw) throws IOException {
  bw.write(name); // Write the library name
  bw.newLine();
  bw.write(Integer.toString(publications.size())); // Write the number of publications
  bw.newLine();
  for (Publication publication : publications) {
      publication.save(bw); // Write each publication
      bw.newLine();
  }
  bw.write(Integer.toString(patrons.size())); // Write the number of patrons
  bw.newLine();
  for (Patron patron : patrons) {
      patron.save(bw); // Write each patron
      bw.newLine();
  }
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
     * Gets the number of publications in the library.
     *
     * @return The number of publications in the library
     */
    public int numPublications() {
      return publications.size();
  }

  /**
     * Gets the number of patrons registered with the library.
     *
     * @return The number of patrons registered with the library
     */
    public int numPatrons() {
      return patrons.size();
  }

  /**
   * Gets a patron at the specified index.
   *
   * @param index The index of the patron to retrieve
   * @return The patron at the specified index
   * @throws IndexOutOfBoundsException if the index is out of bounds
   */
  public Patron getPatron(int index) {
    if (index >= 0 && index < patrons.size()) {
      return patrons.get(index);
    } else {
      throw new IndexOutOfBoundsException("Invalid patron index.");
    }
  }
  
  /**
   * Gets a publication by index from the list of publications.
   * 
   * @param index The index of the publication to retrieve
   * @return The publication at the specified index
   */
  public Publication getPublication(int index) {
    if (index >= 0 && index < publications.size()) {
      return publications.get(index);
    } else {
      throw new IndexOutOfBoundsException("Invalid publication index.");
    }
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

  public void savePublications(BufferedWriter bw) throws IOException {
    for (Publication publication : publications) {
        publication.save(bw);
        bw.newLine();
    }
}

public void savePatrons(BufferedWriter bw) throws IOException {
    for (Patron patron : patrons) {
        patron.save(bw);
    }
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
    String info = "";
    for (int i = 0; i < publications.size(); i++) {
      Publication publication = publications.get(i);
      info += String.format("%d) %s\n", i, publication);
    }

    return info;
  }
}
