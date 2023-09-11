import java.util.Scanner; // for getting input and stuff

public class LibraryManager{
    public static void main(String[] args)
    {   
        // Instance a new Library and add 3 books to it
        Library library = new Library("Kathmandu");
        //Adding Books
        library.addPublication(new Publication("Hands-On Machine Learning with Scikit-Learn", "Aur\u00E9lien G\u00E9ron", 2019));
        library.addPublication(new Publication("The Way of the Superior Man", "David Deida", 2017));
        library.addPublication(new Publication("2001: A Space Odyssey", "Dick Hill", 1968));

        // Print table
        System.out.println(library.toString());

        //Asking for book index
        System.out.printf("\nWhich book to check out? ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        //Asking for patron ID
        System.out.printf("Who are you? ");
        Scanner input2 = new Scanner(System.in);
        String ID = input2.nextLine();

        //Checkout publication
        library.checkOutPublication(choice, ID);

        // Print table
        System.out.println(library.toString());

    }
}