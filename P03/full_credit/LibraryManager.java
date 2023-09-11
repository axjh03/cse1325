public class LibraryManager{
    public static void main(String[] args)
    {   
        // Instance a new Library and add 3 books to it
        Library library = new Library("Columbus");
        library.addPublication(new Publication("The Great Gatsby", "F. Scott Fitzgerald", 1925));
        library.addPublication(new Publication("The Grapes of Wrath", "John Steinbeck", 1939));
        library.addPublication(new Publication("Nineteen Eighty-Four", "George Orwell", 1949));

        //Print the Library to the console, and ask the user which book to check out (an int representing publicationIndex).

        System.out.println(library.toString());

        System.out.printf("\nWhich book would you like to check out? (Enter a number 0-2) ");

    }
}