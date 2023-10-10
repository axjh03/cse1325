// Please add +saveLibrary()
// Please fix and edit as required openLibrary()
package mdi;

import library.Library;
import library.Patron;
import library.Publication;
import library.Video;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            library.addPublication(new Publication(type,title, author, copyright));
        } else if (type.equalsIgnoreCase("Video")) {
            System.out.print("Enter the runtime (minutes): ");
            int runtime = input.nextInt();
            library.addPublication(new Video(title, author, copyright, runtime));
        } else {
            System.err.println("Invalid publication type. Only 'Book' and 'Video' are supported.");
        }
    }

    public void saveLibrary() {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the filename to save publications: ");
        String publicationsFileName = input.nextLine();
        
        System.out.print("Enter the filename to save patrons: ");
        String patronsFileName = input.nextLine();
    
        try (BufferedWriter publicationsBW = new BufferedWriter(new FileWriter(publicationsFileName));
             BufferedWriter patronsBW = new BufferedWriter(new FileWriter(patronsFileName))) {
            library.savePublications(publicationsBW);
            library.savePatrons(patronsBW);
            System.out.println("Library data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving library data: " + e.getMessage());
        }
    }
    
    // Modified openLibrary method to use the Library constructor
    public void openLibrary() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the filename to open the library data: ");
        String fileName = input.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            library = new Library(br);
            System.out.println("Library data loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error opening library data: " + e.getMessage());
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

        try {
            // List patrons
            System.out.println("List of Patrons:");
            // Use numPatrons() to get the number of patrons
            for (int index = 0; index < library.numPatrons(); ++index) {
                Patron patron = library.getPatron(index);
                System.out.println(index + ") " + patron);
            }

            System.out.print("Enter the index of the patron who is checking out: ");
            int patronIndex = input.nextInt();

            // Use numPatrons() to check if the patronIndex is valid
            if (patronIndex >= 0 && patronIndex < library.numPatrons()) {
                int checkedOutIndex = library.checkOut(publicationIndex, patronIndex);
                Publication publication = library.getPublications().get(checkedOutIndex);
                Patron patron = library.getPatron(patronIndex); // Use getPatron(int index)

                String loanedToInfo = "Loaned to: " + patron.toString() + " Due: "
                        + publication.toString().split("Due: ")[1];

                System.out.println(publication.toString().split("Loaned to:")[0] + "Loaned to: " + loanedToInfo);
            } else {
                System.err.println("Invalid patron index.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Invalid publication index.");
        }
    }

    public void checkInPublication() {
        // List checked-out publications
        System.out.println("Checked-out Publications:");

        // Create a list to store checked out publications
        ArrayList<Publication> checkedOutPublications = new ArrayList<>();

        for (int index = 0; index < library.numPublications(); ++index) {
            Publication publication = library.getPublication(index);
            if (publication.isCheckedOut()) {
                checkedOutPublications.add(publication);
            }
        }

        for (int i = 0; i < checkedOutPublications.size(); i++) {
            Publication publication = checkedOutPublications.get(i);
            System.out.println(i + ") " + publication);
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the index of the publication to check in: ");
        System.out.println("");
        int publicationIndex = input.nextInt();

        if (publicationIndex >= 0 && publicationIndex < checkedOutPublications.size()) {
            Publication publication = checkedOutPublications.get(publicationIndex);
            String loanedToInfo = publication.toString().substring(publication.toString().indexOf("Loaned to:") + 10);
            publication.checkIn();
            String[] parts = publication.toString().split("\"");
            System.out
                    .println(parts[1] + " is checked in from" + loanedToInfo.split(" Due:")[0] + " successfully.\n\n");
        } else {
            System.err.println("Invalid publication index.");
        }
    }

    public void listPatrons() {
        System.out.println("List of Patrons:");
        for (int index = 0; index < library.numPatrons(); ++index) {
            Patron patron = library.getPatron(index);
            System.out.println(index + ") " + patron);
        }
    }

    public void addPatron() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of the patron: ");
        String name = input.nextLine();
        System.out.print("Enter the email of the patron: ");
        String email = input.nextLine();

        library.addPatron(new Patron(name, email));
    }

    public void OpenLibrary(String libraryFile, String patronsFile) {
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
                        library.addPublication(new Publication(type,title, author, copyright));
                    } else if (type.equalsIgnoreCase("Video")) {
                        int runtime = Integer.parseInt(parts[4].trim());
                        library.addPublication(new Video(title, author, copyright, runtime));
                    }
                }
            }
            System.out.printf("Successfully loaded [ '%s' ] as Publication File\n", fileName);
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
            System.out.printf("Successfully loaded [ '%s' ] as Patrons File\n\n", fileName);
        } catch (FileNotFoundException e) {
            System.err.println("Could not load patrons file: " + fileName);
            System.exit(1);
        }
    }

    // public void cleanScreen(){
    // System.console("clear");
    // }
    private static void printBanner(String title) {
        int titleLength = title.length();
        int totalLength = titleLength + 6; // Add padding and borders

        StringBuilder banner = new StringBuilder();

        // Add top border
        for (int i = 0; i < totalLength; i++) {
            banner.append("#");
        }
        banner.append("\n");

        // Add title with padding
        banner.append("#  ");
        banner.append(title);
        banner.append("  #\n");

        // Add bottom border
        for (int i = 0; i < totalLength; i++) {
            banner.append("#");
        }
        banner.append("\n");

        System.out.println(banner.toString());
    }

    public static void clearScreen() {
        try {
            // Clear the terminal screen on macOS and Unix-like systems
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String LibraryName = "The Kathmandu Library Lounge (Bagmati)";
        Library library = new Library(LibraryName);
        LibraryManager manager = new LibraryManager(library);
        Scanner input = new Scanner(System.in);
        Integer fileLoadStatus = 0;
        Integer boot = 1;

        while (true) {

            if (boot == 1) {
                clearScreen();
                System.out.println("#####################################################");
                System.out.printf("# Welcome to %s #\n", LibraryName);
                System.out.println("#####################################################");
            } else {
                printBanner("Main Menu");
                System.out.printf("%s\n", LibraryName);
            }

            System.out.println("\nPublications");
            System.out.println("\t1.) List");
            System.out.println("\t2.) Add");
            System.out.println("\t3.) Check out");
            System.out.println("\t4.) Check in");
            System.out.println("\nPatrons");
            System.out.println("\t5.) List");
            System.out.println("\t6.) Add");
            System.out.println("\nFiles");
            System.out.println("\t7.) Load Files");
            System.out.println("\t8.) Save Library");
            System.out.println("\t0.) Exit");
            System.out.print("\nSelect an option: ");

            int choice = input.nextInt();
            input.nextLine(); // get newline

            switch (choice) {
                case 1:
                    clearScreen();
                    printBanner("Library Catalog");
                    manager.listPublications();
                    boot = 0;
                    break;
                case 2:
                    clearScreen();
                    printBanner("Add Publication");
                    manager.addPublication();
                    boot = 0;
                    break;
                case 3:
                    clearScreen();
                    printBanner("Checking Out");
                    manager.checkOutPublication();
                    boot = 0;
                    break;
                case 4:
                    clearScreen();
                    printBanner("Checking In");
                    manager.checkInPublication();
                    boot = 0;
                    break;
                case 5:
                    clearScreen();
                    printBanner("Patrons");
                    manager.listPatrons();
                    boot = 0;
                    break;
                case 6:
                    clearScreen();
                    printBanner("Add Patron");
                    manager.addPatron();
                    boot = 0;
                    break;
                case 7:
                    clearScreen();
                    printBanner("Reading Files");
                    System.out.println("Note: Please enter filename with '.txt' or any extension");
                    System.out.print("Enter Publications File: ");
                    String libraryFile = input.nextLine();
                    System.out.print("Enter Patrons File: ");
                    String patronsFile = input.nextLine();
                    manager.OpenLibrary(libraryFile, patronsFile);
                    fileLoadStatus = 1;
                    boot = 0;
                    break;
                case 8:
                    if (fileLoadStatus == 1) {
                        clearScreen();
                        printBanner("Saving Library");
                        manager.saveLibrary();
                    } else {
                        System.err.println("Please load library data first (Option 7) before saving.");
                    }
                    boot = 0;
                    break;
                case 0:
                    printBanner("Hope to see you again");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid choice. Please select a valid option.");
                    boot = 0;
            }
        }
    }
}
