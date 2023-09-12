import java.time.LocalDate;

public class Publication {
    // Attributes
    private String title, author;
    private Patron loanedTo;
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

        //Check for invalid year
        if (copyright < 1900 || thisYear < copyright) {
            throw new IllegalArgumentException("\n\nInvalid year. Year must be later than 1900 and earlier than current year.\n");
        }
    }

    public void checkOut(Patron patron) {
        loanedTo = patron;
        this.dueDate = currentDate.plusDays(14);
    }

    @Override
    public String toString() {
        String str = String.format("\"%s\" by %s (\u00A9 %d)", title, author, copyright);

        if (loanedTo != null) {
            str += String.format("\n\tLoaned to: %s \n\tDue: %d-%02d-%02d\n", loanedTo, dueDate.getYear(), dueDate.getMonthValue(), dueDate.getDayOfMonth());
        }

        return str;
    }
}
