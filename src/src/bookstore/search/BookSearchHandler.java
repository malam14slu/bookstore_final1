package src.bookstore.search;

import javax.swing.JOptionPane;

import bookstore.RibbonLayout.RibbonLayout;

import javax.swing.*;

public class BookSearchHandler {
    private static RibbonLayout ribbonLayout;

    public static void setRibbonLayout(RibbonLayout ribbonLayout) {
        BookSearchHandler.ribbonLayout = ribbonLayout;
    }

    public static void handleSearch() {
        String searchQuery = JOptionPane.showInputDialog(null, "Enter ISBN number, author, or book title:");
        if (searchQuery != null && !searchQuery.isEmpty()) {
            BookDetails book = BookDatabaseConnector.searchBook(searchQuery);
            if (book != null) {
                ribbonLayout.displaySearchResult(book);
            } else {
                JOptionPane.showMessageDialog(null, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
