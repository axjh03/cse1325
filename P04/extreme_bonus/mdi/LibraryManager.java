package mdi;

import library.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        System.out.println(library.toString());
    }

    // Load publications from a text file
    public static void loadPublicationsFromFile(Library library, String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 4) {
                    String type = parts[0];
                    String title = parts[1];
                    String author = parts[2];
                    int copyright = Integer.parseInt(parts[3].trim());

                    if (type.equals("Book")) {
                        library.addPublication(new Publication(title, author, copyright));
                    } else if (type.equals("Video")) {
                        int runtime = Integer.parseInt(parts[4].trim());
                        library.addPublication(new Video(title, author, copyright, runtime));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not load publications file: " + fileName);
            System.exit(1); // Exit the program with an error code
        }
    }

    // Load patrons from a text file
    // Load patrons from a text file
    public static void loadPatronsFromFile(Library library, String fileName) {
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
            System.exit(1); // Exit the program with an error code
        }
    }

}
