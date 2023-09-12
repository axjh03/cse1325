import java.util.*;

public class Library
{
    //Attributes
    private String name; // name of the library
    private ArrayList<Publication> publications = new ArrayList<>(); // rm = new ArrayList<>();
    //private ArrayList<Patron> patrons;
    private ArrayList<Patron> patrons = new ArrayList<>(); // rm = new ArrayList<>();

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

    public void addPatron(Patron patron)
    {
        this.patrons.add(patron);
    }

    public String patronMenu()
    {
        String result = String.format("Patrons\n\n");

        for (int i = 0; i < patrons.size(); i++) {
            Patron patron = patrons.get(i);
            // Use the toString function from the Publication class 
            String patronInfo = String.format("%d) %s\n", i, patron.toString());
            result += patronInfo;
        }
        //System.out.println("\n\n");
        return result;
    }

    public int checkOut(int publicationIndex, int patronIndex) 
    {
      try 
      {
    
        Publication publication = publications.get(publicationIndex);
        Patron patron = patrons.get(patronIndex);
        publication.checkOut(patron);
      } 
      catch (IndexOutOfBoundsException e) 
      {
        System.out.println("\nIndex out of bounds\nPlease enter number in range");
        System.exit(-1);
      }
      return publicationIndex;
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