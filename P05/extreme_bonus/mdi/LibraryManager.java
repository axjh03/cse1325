// +LibraryManager(library : Library)
// +listPublications()
// +addPublication()
// +addVideo()
// +checkOutPublication()
// +checkInPublication()
// +listPatrons()
// +addPatron()
// +loadData()
// +main(args: String[])
package mdi;

import library.Library;
import library.Patron;
import library.Publication;
import library.Video;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManager {
    private Library library;

    public LibraryManager(Library library) {
        this.library = library;
    }

    public void listPublications() {
        System.out.println("Publications:");
        System.out.println(library.toString());
    }

    public void addPublication() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the type of publication (Book/Video): ");
        String type = input.nextLine();
        System.out.print("Enter the title: ");
        String title = input.nextLine();
        System.out.print("Enter the author: ");
        String author = input.nextLine();
        System.out.print("Enter the copyright year: ");
        int copyright = input.nextInt();

        if (type.equalsIgnoreCase("Book")) {
            library.addPublication(new Publication(title, author, copyright));
        } else if (type.equalsIgnoreCase("Video")) {
            System.out.print("Enter the runtime (minutes): ");
            int runtime = input.nextInt();
            library.addPublication(new Video(title, author, copyright, runtime));
        } else {
            System.err.println("Invalid publication type. Only 'Book' and 'Video' are supported.");
        }
    }

    public void addVideo() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the title: ");
        String title = input.nextLine();
        System.out.print("Enter the author: ");
        String author = input.nextLine();
        System.out.print("Enter the copyright year: ");
        int copyright = input.nextInt();
        System.out.print("Enter the runtime (minutes): ");
        int runtime = input.nextInt();

        library.addPublication(new Video(title, author, copyright, runtime));
    }

    public void checkOutPublication() {
        // List available publications
        System.out.println("Available Publications:");
        listPublications();

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the index of the publication to check out: ");
        int publicationIndex = input.nextInt();

        if (publicationIndex >= 0 && publicationIndex < library.getPublications().size()) {
            // List patrons
            System.out.println("List of Patrons:");
            System.out.println(library.patronMenu());

            System.out.print("Enter the index of the patron who is checking out: ");
            int patronIndex = input.nextInt();

            if (patronIndex >= 0 && patronIndex < library.getPatrons().size()) {
                int checkedOutIndex = library.checkOut(publicationIndex, patronIndex);
                System.out.println("Publication with index " + checkedOutIndex + " checked out successfully.");
            } else {
                System.err.println("Invalid patron index.");
            }
        } else {
            System.err.println("Invalid publication index.");
        }
    }

    public void checkInPublication() {
        // List checked-out publications
        System.out.println("Checked-out Publications:");

        ArrayList<Publication> checkedOutPublications = new ArrayList<>();

        for (Publication publication : library.getPublications()) {
            if (publication.toString().contains("Loaned to:")) {
                checkedOutPublications.add(publication);
            }
        }

        for (int i = 0; i < checkedOutPublications.size(); i++) {
            Publication publication = checkedOutPublications.get(i);
            System.out.println(i + ") " + publication);
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the index of the publication to check in: ");
        int publicationIndex = input.nextInt();

        if (publicationIndex >= 0 && publicationIndex < checkedOutPublications.size()) {
            Publication publication = checkedOutPublications.get(publicationIndex);
            publication.checkIn();
            System.out.println("Publication with index " + publicationIndex + " checked in successfully.");
        } else {
            System.err.println("Invalid publication index.");
        }
    }

    public void listPatrons() {
        System.out.println("List of Patrons:");
        System.out.println(library.patronMenu());
    }

    public void addPatron() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of the patron: ");
        String name = input.nextLine();
        System.out.print("Enter the email of the patron: ");
        String email = input.nextLine();

        library.addPatron(new Patron(name, email));
    }

    public void loadData(String libraryFile, String patronsFile) {
        loadPublicationsFromFile(libraryFile);
        loadPatronsFromFile(patronsFile);
    }

    private void loadPublicationsFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 4) {
                    String type = parts[0];
                    String title = parts[1];
                    String author = parts[2];
                    int copyright = Integer.parseInt(parts[3].trim());

                    if (type.equalsIgnoreCase("Book")) {
                        library.addPublication(new Publication(title, author, copyright));
                    } else if (type.equalsIgnoreCase("Video")) {
                        int runtime = Integer.parseInt(parts[4].trim());
                        library.addPublication(new Video(title, author, copyright, runtime));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not load publications file: " + fileName);
            System.exit(1);
        }
    }

    private void loadPatronsFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 2) {
                    String name = parts[0];
                    String email = parts[1];
                    library.addPatron(new Patron(name, email));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not load patrons file: " + fileName);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Library library = new Library("The Kathmandu Library Lounge (Bagmati)");
        LibraryManager manager = new LibraryManager(library);
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("##########################################");
            System.out.printf("# %s #\n",library.toString());
            System.out.println("##########################################");
            // System.out.println(Library);
            System.out.println("\nPublications");
            System.out.println("1.) List");
            System.out.println("2.) Add");
            System.out.println("3.) Check out");
            System.out.println("4.) Check in");
            System.out.println("Patrons");
            System.out.println("5.) List");
            System.out.println("6.) Add");
            System.out.println("Files");
            System.out.println("7.) Load Files");
            System.out.println("0.) Exit");
            System.out.print("Select an option: ");

            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manager.listPublications();
                    break;
                case 2:
                    manager.addPublication();
                    break;
                case 3:
                    manager.checkOutPublication();
                    break;
                case 4:
                    manager.checkInPublication();
                    break;
                case 5:
                    manager.listPatrons();
                    break;
                case 6:
                    manager.addPatron();
                    break;
                case 7:
                    System.out.print("Enter Library File: ");
                    String libraryFile = input.nextLine();
                    System.out.print("Enter Patrons File: ");
                    String patronsFile = input.nextLine();
                    manager.loadData(libraryFile, patronsFile);
                    break;
                case 0:
                    System.out.println("Exiting Library Manager.");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
