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
            throw new IllegalArgumentException("Invalid year. Year must be later than 1900");
        }
    }

    public void checkOut(String patron) {
        loanedTo = patron;
        this.dueDate = currentDate.plusDays(14);
    }

    // public void checkIn() {
    //    
	//
    // }

    @Override
    public String toString() {
        String str = String.format("'%s' by %s, copyright %d", title, author, copyright);

        if (loanedTo != null) {
            str += String.format("\n\t==> Checked out to %s, due %d/%d/%d", loanedTo, thisMonth, thisDay, dueDate.getYear());
        }

        return str;
    }
}
