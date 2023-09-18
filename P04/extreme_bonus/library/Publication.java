package library;

import java.time.LocalDate;

public class Publication {
    private String title;
    private String author;
    private int copyright;
    private Patron loanedTo;
    private LocalDate dueDate;

    public Publication(String title, String author, int copyright) {
        this.title = title;
        this.author = author;
        this.copyright = copyright;

        if (copyright < 1900) {
            throw new IllegalArgumentException("Copyright must be 1900 or later");
        }
    }

    public void checkOut(Patron patron) {
        loanedTo = patron;
        dueDate = LocalDate.now().plusDays(14);
    }

    public void checkIn() {
        loanedTo = null;
        dueDate = null;
    }

    protected String toStringBuilder(String pre, String mid) {
        String str = pre + " \"" + title + "\" by " + author + " (©" + copyright + ")" + mid;

        if (loanedTo != null) {
            str += "\nLoaned to: " + loanedTo + " Due: " + dueDate;
        }

        return str;
    }

    @Override
    public String toString() {
        return toStringBuilder("Book", "");
    }
}
