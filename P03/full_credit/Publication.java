/*
 * -title: String
 * -author :String
 * copyright: int
 * loanedTo: String
 * dueDate: LocalDate
 * 
 * +Publication(title: String, author: String, copyright: int)
 * +checkOut(patron: String): void
 * +toString(): String
 */


//  Publication includes 5 private fields.
// • The title and author (each a String) may contain any text. Who knows what an author may
// choose for their own or their book's name?
// • The copyright year is just an int. We'll make sure it's valid in the constructor.
// • loanedTo is the name of the person who has borrowed this book, or null if still on the shelves.
// • dueDate is the date by which the book must be returned. It will be set in method checkOut.
// • The Publication constructor initializes the fields as discussed above. It should also perform data
// validation - if copyright is less than 1900 or greater than today's year, throw a new
// IllegalArgumentException with a clear message explaining the cause of the exception.
// • Look at LocalDate's documentation. Which method will give the the current time right now?
// • Look at LocalDate's documentation. Given a LocalDate object for the current time right now, how
// can you get the year as an int so you can compare it to copyright?
// • The checkout method sets field loanedTo to its patron parameter, and sets dueDate to 14 days
// from now.
// • Look at LocalDate's documentation. Given a LocalDate object for the current time right now, how
// can you get a new LocalDate object that is 14 days from now?
// • The toString() method must return a String containing the title, author, copyright date, and (if
// loanedTo is not null) the patron to which it has been loaned (loanedTo) and when it is due back
// (dueDate). You may format it as you please - do NOT just use this format, choose something original -
// but ensure you include all 3 or 5 fields as required. (Don't forget the @Override annotation. We'll
// discuss why soon!.)


import java.time.LocalDate;

public class Publication {
    // Attributes
    private String title, author, loanedTo;
    private int copyright;
    private LocalDate dueDate;

    // LocalDate instance
    LocalDate currentDate = LocalDate.now(); // Creates a LocalDate instance with the current date
    int thisYear = currentDate.getYear(); // Gets the year from the LocalDate instance
    int thisMonth = currentDate.getMonthValue(); // Gets the month from the LocalDate instance
    int thisDay = currentDate.getDayOfMonth(); // Gets the day from the LocalDate instance

    // Constructors
    public Publication(String title, String author, int copyright) {
        this.title = title;
        this.author = author;
        this.copyright = copyright;

        // Check for invalid year
        if (copyright < 1900 || thisYear < copyright) {
            throw new IllegalArgumentException("Invalid year. Year must be later than 1900 and earlier than current year.");
        }
    }

    public void checkOut(String patron) {
        loanedTo = patron;
        this.dueDate = currentDate.plusDays(14);
    }

    @Override
    public String toString() {
        String str = String.format("\"%s\" by %s, copyright %d", title, author, copyright);

        if (loanedTo != null) {
            str += String.format(": loaned to %s until %d-%d-%d", loanedTo, dueDate.getYear(), dueDate.getMonthValue(), dueDate.getDayOfMonth());
        }

        return str;
    }
}
