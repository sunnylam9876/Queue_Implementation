import java.util.List;

public class App {

	public static void main(String[] args) {
		// Create a LinkedQueue to represent the book list
        //LinkedQueue<String> bookList = new LinkedQueue<>();
		ArrayQueue<String> bookList = new ArrayQueue<>();

        // Add books to the list
        bookList.enqueue("Book 1");
        bookList.enqueue("Book 2");
        bookList.enqueue("Book 3");
        bookList.enqueue("Book 4");

        // Display all books in the list using LinkedQueue
        /*
        Object[] allBooks = bookList.getAll();        
        for (Object book : allBooks) {
            String bookString = (String) book; // Manually cast each element to String
            System.out.println(bookString);
        } */
        
        // Display all books in the list using ArrayQueue
        List<String> allBooks = bookList.getAll();
        System.out.println("All books in the list:");
        for (String book : allBooks) {
            System.out.println(book);
        }

        // Display the front book in the list
        if (!bookList.isEmpty()) {
            System.out.println("Front book: " + bookList.getFront());
        } else {
            System.out.println("The book list is empty.");
        }

        // Remove and display books one by one
        while (!bookList.isEmpty()) {
            String removedBook = bookList.dequeue();
            System.out.println("Removed book: " + removedBook);
        }

        // Check if the list is empty after dequeuing
        if (bookList.isEmpty()) {
            System.out.println("The book list is empty.");
        }

        // Clear the list
        bookList.clear();

        // Check if the list is empty after clearing
        if (bookList.isEmpty()) {
            System.out.println("The book list is empty after clearing.");
        }
    }

	

}
