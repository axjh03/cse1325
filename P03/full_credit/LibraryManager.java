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

        // Print table
        System.out.println(library.toString());

        //Asking for book index
        // if a integer value was not entered, then exit
        //System.out.printf("\nWhich book to check out? ");
        Scanner input = new Scanner(System.in);
        int choice = 0;

        try 
        {
            System.out.print("Which book to check out? ");
            choice = input.nextInt();
        } 
        catch (java.util.InputMismatchException e) 
        {
            System.err.println("Invalid input. Please enter an integer.");
            System.exit(1); // Exit the program with an error code
        }

        //Asking for patron ID
        System.out.printf("Who are you? ");
        Scanner input2 = new Scanner(System.in);
        String ID = input2.nextLine();

        //Checkout publication
        library.checkOut(choice, ID);

        //printLines
        System.out.println("\n");
        // Print table
        System.out.println(library.toString());
    }
}