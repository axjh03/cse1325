package mdi;

import library.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LibraryManager {

    public static void main(String[] args) {
        Library library = new Library("My Library");
        loadPublicationsFromFile(library, "publications.txt");
    }

    public static void loadPublicationsFromFile(Library library, String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));

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
            System.out.println("Could not load publications file");
        }
    }
}
