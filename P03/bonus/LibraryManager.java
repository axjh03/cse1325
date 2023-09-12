import java.util.Scanner; // for getting input and stuff

public class LibraryManager{
    public static void main(String[] args)
    {   
        // Instance a new Library and add 3 books to it
        Library library = new Library("The Kathmandu Library Lounge (Bagmati)");
        
        //Adding Books
        library.addPublication(new Publication("Hands-On Machine Learning with Scikit-Learn", "Aur\u00E9lien G\u00E9ron", 2023));
        library.addPublication(new Publication("The Way of the Superior Man", "David Deida", 2017));
        library.addPublication(new Publication("2001: A Space Odyssey", "Dick Hill", 1968));

        //Adding Patrons
        library.addPatron(new Patron("Ryan Davis", "davis@yahoo.com"));
        library.addPatron(new Patron("Priyanka Karki", "priyanka@gmail.com"));
        library.addPatron(new Patron("Emily Davis", "emily.davis@outlook.com"));
        library.addPatron(new Patron("John Smith", "john@uidaho.com"));


        // Print table
        System.out.println(library.toString());

        //Asking for book index
        // if a integer value was not entered, then exit
        //System.out.printf("\nWhich book to check out? ");
        Scanner input = new Scanner(System.in);
        int BookChoice = 0, PatronChoice = 0;


        // Input for Selecting Book
        try 
        {
            System.out.print("Which book to check out? ");
            BookChoice = input.nextInt();
        } 
        catch (java.util.InputMismatchException e) 
        {
            System.err.println("Invalid input. Please enter an integer.");
            System.exit(1); // Exit the program with an error code
        }

        //Asking for patron ID




        System.out.println(library.patronMenu());
        // Input for selecting Patrons Book
        try 
        {
            System.out.print("Who are you? ");
            PatronChoice = input.nextInt();
        } 
        catch (java.util.InputMismatchException e) 
        {
            System.err.println("Invalid input. Please enter an integer.");
            System.exit(1); // Exit the program with an error code
        }

        //Checkout publication
        library.checkOut(BookChoice, PatronChoice);

        //printLines
        System.out.println("\n");
        // Print table
        System.out.println(library.toString());
    }
}