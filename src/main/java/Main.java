import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create some books
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 218, "Fiction");
        Book book2 = new Book("A Brief History of Time", "Stephen Hawking", 1988, 212, "Science");
        Book book3 = new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 277, "Fiction");
        Book book4 = new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 2011, 443, "History");
        Book book5 = new Book("1984", "George Orwell", 1949, 328, "Fiction");

        // Initialize the library and add books
        Library library = new Library();
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);

        // Register some users
        User user1 = new User("Alice", 1001);
        User user2 = new User("Bob", 1002);
        library.registerUser(user1);
        library.registerUser(user2);

        // Test functionalities
        System.out.println("Books published in 1949:");
        List<Book> books1949 = library.findBooksByYear(1949);
        books1949.forEach(book -> System.out.println(book.getTitle()));

        System.out.println("\nBooks by George Orwell:");
        List<Book> booksByOrwell = library.findBooksByAuthor("George Orwell");
        booksByOrwell.forEach(book -> System.out.println(book.getTitle()));

        System.out.println("\nBook with most pages:");
        library.findBookWithMostPages().ifPresent(book -> System.out.println(book.getTitle() + " - " + book.getPages() + " pages"));

        System.out.println("\nBooks with more than 300 pages:");
        List<Book> booksMoreThan300Pages = library.findBooksWithMoreThanPages(300);
        booksMoreThan300Pages.forEach(book -> System.out.println(book.getTitle()));

        System.out.println("\nAll book titles sorted alphabetically:");
        List<String> sortedTitles = library.getAllBookTitlesSorted();
        sortedTitles.forEach(System.out::println);

        System.out.println("\nBooks in the 'Fiction' category:");
        List<Book> fictionBooks = library.findBooksByCategory("Fiction");
        fictionBooks.forEach(book -> System.out.println(book.getTitle()));

        System.out.println("\nLoan out '1984' to Alice:");
        library.loanOutBook("1984", user1);
        user1.getBooksOnLoan().forEach(book -> System.out.println("Alice has loaned out: " + book.getTitle()));

        System.out.println("\nReturn '1984' from Alice:");
        library.returnBook("1984", user1);
        System.out.println("Books on loan to Alice: " + user1.getBooksOnLoan().size());

        // Simulate passing time to test late fees
        user1.getBooksOnLoan().add(new Book("Dummy Book", "Dummy Author", 2000, 100, "Dummy Category") {{
            setOnLoan(true);
            setLoanDate(java.time.LocalDate.now().minusDays(20));
        }});
        library.calculateLateFees();
        System.out.println("\nLate fees for Alice: $" + user1.getLateFees());
    }
}
