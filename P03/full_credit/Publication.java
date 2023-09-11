import java.time.LocalDate; 

public class Publication
{
	// Attributes
	private String title, author, loanedTo;
	private int copyright;
	// private Patron loanedTo;
	private LocalDate dueDate;
	
	// LocalDate instance
	LocalDate currentDate = LocalDate.now(); // Creates a LocalDate instance with the current date
	int thisYear = currentDate.getYear(); // Gets the year from the LocalDate instance
	int thisMonth = currentDate.getMonthValue(); // Gets the month from the LocalDate instance
	int thisDay = currentDate.getDayOfMonth(); // Gets the day from the LocalDate instance


	//Constructors
	public Publication(String title, String author, int copyright)
	{
		this.title = title;
		this.author = author;
		this.copyright = copyright;
		
		// Check for invalid year
		if(copyright<1900 || thisYear>copyright)
		{
			throw new IllegalArgumentException("Invalid year\nYear Must be later than 1900");
			System.exit(-1); // Exits program
		}
		 
	}

	
	 //Publication is a constructor for the Publication class, and it doesn't have a return type. However, checkOut is not a constructor; it's a regular method for checking out a publication. It should have a return type of void to indicate that it doesn't return any value.'


	public void checkOut(String patron)
 	{
		// this.loanedTo = patron;
		loanedTo = patron;
		this.dueDate = currentDate.plusDays(14);
	}

	// public checkIn()
	// {

	// }

	@Override
	public String toString()
	{
		// String str = String.format('"' + "%s" + '"' + " by " + "%s" + "," + "copyright %d", title, author, copyright);
		String str = String.format("'%s' by %s, %d", title, author, thisYear);

		if(loanedTo != null)
		{
			str += String.format("\n\t==> Checked out to %s, due %d/%d/%d", loanedTo, thisMonth, thisDay, thisYear);
		}

		return str;
	
	}

}
