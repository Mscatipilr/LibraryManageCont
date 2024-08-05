import java.nio.file.attribute.UserPrincipal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<User> users;

    //Constructor to initialize the library
    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    //Constructor to initialize the library with a list of books
    public Library(List<Book> books) {
        this.books = books;
        this.users = new ArrayList<>();
    }

    //Getter and Setters
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    // Method to add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    //Remove a book from the library
    public void removeBook(String title) {
        books.removeIf(b -> b.getTitle().equalsIgnoreCase(title));
    }

    //Method to find all books published in a specific year
    public List<Book> findBooksByYear(int year) {
        return books.stream()
                .filter(book -> book.getPublicationYear() == year)
                .collect(Collectors.toList());
    }

    //Method to find all books by a specific author
    public List<Book> findBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    //Method to find the book with the most pages
    public Optional<Book> findBookWithMostPages() {
        return books.stream()
                .max(Comparator.comparingInt(Book::getPages));
    }

    //Method to find all books with more than n pages
    public List<Book> findBooksWithMoreThanPages(int pages) {
        return books.stream()
                .filter(book -> book.getPages() > pages)
                .collect(Collectors.toList());
    }

    //Method to print all book titles in the library, sorted alphabetically
    public List<String> getAllBookTitlesSorted() {
        return books.stream()
                .map(Book::getTitle)
                .sorted()
                .collect(Collectors.toList());
    }

    //Method to find all books in a specific category
    public List<Book> findBooksByCategory(String category) {
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    //Method to loan out a book to a user
    public void loanOutBook(String title, User user) {
        books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title) && !book.isOnLoan())
                .findFirst()
                .ifPresent(book -> {
                    book.setOnLoan(true);
                    book.setLoanDate(LocalDate.now());
                    user.getBooksOnLoan().add(book);
                });
    }

    //Method to return a book from a user
    public void returnBook(String title, User user) {
        Optional<Book> bookToReturn = user.getBooksOnLoan().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst();
        bookToReturn.ifPresent(book -> {
            book.setOnLoan(false);
            user.getBooksOnLoan().remove(book);
        });
    }

    //Method to register a new user
    public void registerUser(User user) {
        users.add(user);
    }

    //Method to calculate late fees for all users
    public void calculateLateFees() {
        for (User user : users) {
            for (Book book : user.getBooksOnLoan()) {
                long daysOnLoan = ChronoUnit.DAYS.between(book.getLoanDate(), LocalDate.now());
                if (daysOnLoan > 14) {
                    user.setLateFees(user.getLateFees() + daysOnLoan - 14);
                }
            }
        }
    }

}
