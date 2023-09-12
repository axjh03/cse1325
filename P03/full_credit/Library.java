import java.util.*;

public class Library
{
    //Attributes
    private String name; // name of the library
    private ArrayList<Publication> publications = new ArrayList<>(); // rm = new ArrayList<>();
    //private ArrayList<Patron> patrons;

    //Constructor
    public Library(String name)
    {
        this.name = name;
    }

    //Methods
    public void addPublication(Publication publication)
    {
        // In Java, we use the add method instead of append when adding elements to an ArrayList.
        this.publications.add(publication);
    }

    public int checkOut(int publicationindex, String patron)
    {
        try
        {
            publications.get(publicationindex).checkOut(patron);
        }

        catch(IndexOutOfBoundsException e)
        {
            System.out.println("\nIndex out of bounds\nPlease enter number in range");
            System.exit(-1);
        }
        return publicationindex;
    }

    @Override
    public String toString() { 
        String result = String.format("%s\n\n", name);

        for (int i = 0; i < publications.size(); i++) {
            Publication publication = publications.get(i);
            // Use the toString function from the Publication class // SMART !
            String publicationInfo = String.format("%d) %s\n", i, publication.toString());
            result += publicationInfo;
        }
        //System.out.println("\n\n");
        return result;
    }
}