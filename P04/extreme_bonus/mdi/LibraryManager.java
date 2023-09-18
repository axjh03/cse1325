package mdi;

import library.Publication;
import library.Patron;
import library.Library;

import java.util.Calendar;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LibraryManager {
    public static void main(String[] args) {
        Library library = new Library("The Kathmandu Library Lounge (Bagmati)");

        // Load publications from the publications.txt file
        loadPublicationsFromFile(library, "publications.txt");

        // Load patrons from the patrons.txt file
        loadPatronsFromFile(library, "patrons.txt");

        // Print table
        System.out.println(library.toString());

        // Input for Selecting Book
        Scanner input = new Scanner(System.in);
        int bookChoice = 0, patronChoice = 0;

        try {
            System.out.print("Which book to check out? ");
            bookChoice = input.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.err.println("Invalid input. Please enter an integer.");
            System.exit(1); // Exit the program with an error code
        }

        System.out.println(library.patronMenu());

        try {
            System.out.print("Who are you? ");
            patronChoice = input.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.err.println("Invalid input. Please enter an integer.");
            System.exit(1); // Exit the program with an error code
        }

        // Checkout publication
        library.checkOut(bookChoice, patronChoice);

        // Print updated table
        System.out.println("\nUpdated Library Contents:");
        System.out.println(library.toString());
    }

    // Load publications from a text file
    public static void loadPublicationsFromFile(Library library, String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int currentYear = Calendar.getInstance().get(Calendar.YEAR); // Get the current year
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); // Assuming comma-separated format

                if (parts.length >= 3) {
                    String title = parts[0];
                    String author = parts[1];
                    int copyright;

                    try {
                        copyright = Integer.parseInt(parts[2].trim()); // Trim leading and trailing spaces
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid year format for publication" );
                    }

                    if (copyright >= 1900 && copyright <= currentYear) {
                        library.addPublication(new Publication(title, author, copyright));
                    } else {
                        throw new IllegalArgumentException("\n\nInvalid year. Year must be later than 1900 and earlier than current year.\n");
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
            System.exit(1); // Exit the program with an error code
        }
    }
    // Load patrons from a text file
    public static void loadPatronsFromFile(Library library, String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); // Assuming comma-separated format

                if (parts.length >= 2) {
                    String name = parts[0];
                    String email = parts[1];

                    library.addPatron(new Patron(name, email));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
            System.exit(1); // Exit the program with an error code
        }
    }
}
