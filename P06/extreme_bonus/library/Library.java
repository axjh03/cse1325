package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a library that manages publications and patrons.
 */
public class Library {

  private String name;
  private ArrayList<Publication> publications;
  private ArrayList<Patron> patrons;

  /**
   * Creates a new library with the provided name.
   *
   * @param name The name of the library
   */
  public Library(String name) {
    this.name = name;
    publications = new ArrayList<>();
    patrons = new ArrayList<>();
  }

  /**
   * Gets the name of the library.
   *
   * @return The name of the library
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the library.
   *
   * @param name The name of the library to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Adds a publication to the library's collection.
   *
   * @param publication The publication to add
   */
  public void addPublication(Publication publication) {
    publications.add(publication);
  }

  /**
   * Removes a publication from the library's collection.
   *
   * @param publication The publication to remove
   */
  public void removePublication(Publication publication) {
    publications.remove(publication);
  }

  /**
   * Adds a patron to the library's list of patrons.
   *
   * @param patron The patron to add
   */
  public void addPatron(Patron patron) {
    patrons.add(patron);
  }

  /**
   * Removes a patron from the library's list of patrons.
   *
   * @param patron The patron to remove
   */
  public void removePatron(Patron patron) {
    patrons.remove(patron);
  }

  /**
   * Checks if a publication with the given title is available in the library.
   *
   * @param title The title of the publication to check
   * @return true if the publication is available, false otherwise
   */
  public boolean isPublicationAvailable(String title) {
    for (Publication publication : publications) {
      if (publication.getTitle().equalsIgnoreCase(title) && !publication.isCheckedOut()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks out a publication to a patron.
   *
   * @param title  The title of the publication to check out
   * @param patron The patron checking out the publication
   * @return true if the checkout is successful, false otherwise
   */
  public boolean checkOutPublication(String title, Patron patron) {
    for (Publication publication : publications) {
      if (publication.getTitle().equalsIgnoreCase(title) && !publication.isCheckedOut()) {
        publication.checkOut(patron);
        return true;
      }
    }
    return false;
  }

  /**
   * Checks in a publication that was previously checked out.
   *
   * @param title The title of the publication to check in
   */
  public void checkInPublication(String title) {
    for (Publication publication : publications) {
      if (publication.getTitle().equalsIgnoreCase(title) && publication.isCheckedOut()) {
        publication.checkIn();
        return;
      }
    }
  }

  /**
   * Generates a string representation of the library.
   *
   * @return The string representation
   */
  @Override
  public String toString() {
    String str = "Library: " + name + "\n";
    str += "Publications:\n";
    for (Publication publication : publications) {
      str += publication.toString() + "\n";
    }
    str += "Patrons:\n";
    for (Patron patron : patrons) {
      str += patron.toString() + "\n";
    }
    return str;
  }

  /**
   * Loads the state of the library from the provided reader.
   *
   * @param reader The reader to load data from
   * @throws IOException If an I/O error occurs
   */
  public Library(BufferedReader reader) throws IOException {
    name = reader.readLine();

    int numPubs = Integer.parseInt(reader.readLine());
    publications = new ArrayList<>();
    for (int i = 0; i < numPubs; i++) {
      String type = reader.readLine();
      if (type.equals("publication")) {
        publications.add(new Publication(reader));
      } else {
        publications.add(new Video(reader));
      }
    }

    int numPatrons = Integer.parseInt(reader.readLine());
    patrons = new ArrayList<>();
    for (int i = 0; i < numPatrons; i++) {
      patrons.add(new Patron(reader));
    }
  }

  /**
   * Saves the state of the library to the provided writer.
   *
   * @param writer The writer to save data to
   * @throws IOException If an I/O error occurs
   */
  public void save(BufferedWriter writer) throws IOException {
    writer.write(name + "\n");

    writer.write(publications.size() + "\n");
    for (Publication pub : publications) {
      if (pub instanceof Publication) {
        writer.write("publication\n");
      } else {
        writer.write("video\n");
      }
      pub.save(writer);
    }

    writer.write(patrons.size() + "\n");
    for (Patron patron : patrons) {
      patron.save(writer);
    }
  }
}
