// Library
// -name: String
// -publications: Publication[I «ArrayList»
// +Library(name : String)
// +addPublication(publication : Publication)
// +checkOutpublicationindex : int, patron : String)
// +toString() : String // ibrary


// The name field is just the name of this Library, and can be anything at all.
// • The publications field is an ArrayList<Publication> (The «ArrayList» notation is a stereotype
// that tells you this - remember this for the exam!)
// • The constructor assigns its parameter to the name field. The publications field may be initialized
// in-line or in the constructor, as you please.
// • Method addPublication appends its parameter to the publications field. You may assume this will
// never fail.
// • Method checkout finds the Publication at index publicationIndex and calls its checkout method,
// passing the patron parameter to it. Note that if publicationIndex is not a valid index within
// publications, the ArrayList will throw an IndexOutOfBoundsException.
// • Method checkin is intentionally omitted for this assignment - you need not write it.
// • Method toString should return the name of the Library, followed by a menu of all publications in the
// Library - the index number followed by the publication. This is so a user can enter an index number to
// check out a publication. So something like this. (Do NOT literally print this text - print what is in the class
// fields!
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

    //

    //
    //Methods
    public void addPublication(Publication publication)
    {
        // In Java, we use the add method instead of append when adding elements to an ArrayList.
        this.publications.add(publication);
    }

    public int checkOutPublication(int publicationindex, String patron)
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
    public String toString() { //The Kathmandu Learning Lounge
        String result = String.format("The %s Library Lounge (Bagmati)\n\n", name);

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