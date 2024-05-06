package src.bookstore.bookDetails;

import java.awt.Component;

import javax.swing.JOptionPane;

import bookstore.bookDetails.BookDetails;
import src.bookstore.databaseConnector.BookDatabaseConnector;


public class BookSearchHandler {
    public static void handleSearch() {
        String searchQuery = JOptionPane.showInputDialog(null, "Enter ISBN number, author, or book title:");
        if (searchQuery != null && !searchQuery.isEmpty()) {
            BookDetails book = BookDatabaseConnector.searchBook(searchQuery);
			if (book != null) {
			    JOptionPane.showMessageDialog(null, book.toString(), "Book Details", JOptionPane.INFORMATION_MESSAGE);
			} else {
			    JOptionPane.showMessageDialog(null, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
			}
        }
    }

	public static Component getSearchResults() {
		// TODO Auto-generated method stub
		return null;
	}
}


