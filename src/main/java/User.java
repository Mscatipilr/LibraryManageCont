import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int libraryCardNumber;
    private List<Book> booksOnLoan;
    private double lateFees;

    // Constructor to initialize a new user
    public User(String name, int libraryCardNumber) {
        this.name = name;
        this.libraryCardNumber = libraryCardNumber;
        this.booksOnLoan = new ArrayList<>();
        this.lateFees = 0.0;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public void setLibraryCardNumber(int libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
    }

    public List<Book> getBooksOnLoan() {
        return booksOnLoan;
    }

    public void setBooksOnLoan(List<Book> booksOnLoan) {
        this.booksOnLoan = booksOnLoan;
    }

    public double getLateFees() {
        return lateFees;
    }

    public void setLateFees(double lateFees) {
        this.lateFees = lateFees;
    }
}
