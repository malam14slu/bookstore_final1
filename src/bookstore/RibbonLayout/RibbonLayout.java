package bookstore.RibbonLayout;

//shah

import javax.swing.*;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.text.DefaultEditorKit;

import bookstore.bookDetails.BookDetails;
import bookstore.homepage.GridLayoutManager;
import bookstore.loginWindow.LoginWindow;
import src.bookstore.admin.UserAuthentication;
import src.bookstore.admin.UserRegistration;
import src.bookstore.bookDetails.BookSearchHandler;
import src.bookstore.databaseConnector.BookDatabaseConnector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RibbonLayout implements ActionListener {

    JFrame frame;
    JMenuBar menuBar;
    JMenu home, edit, search, cart, checkout, trade, help, admin, logout;
    JMenuItem cutItem, copyItem, pasteItem, selectAllItem, helpItem, adminItem, viewCart, clearCart;
   // Cart cart_obj;

    public RibbonLayout() {
        frame = new JFrame();

        //cart_obj = new Cart();

        // Setup Grid Layout Manager
        GridLayoutManager.setupGridLayout(frame);

        // Initialize components
        initializeMenuBar();

        // Set frame properties
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Method to initialize menu bar and its components
    private void initializeMenuBar() {
        menuBar = new JMenuBar();

        // Initialize menus
        home = new JMenu("Home");
        edit = new JMenu("Edit");
        search = new JMenu("Search");
        cart = new JMenu("Cart");
        checkout = new JMenu("Checkout");
        trade = new JMenu("Trade");
        help = new JMenu("Help");
        admin = new JMenu("Admin");
        logout = new JMenu("Logout");

        // Initialize menu items
        initializeMenuItems();

        // Add menus to the menu bar with separators
        addMenuItemWithSeparator(home);
        addMenuItemWithSeparator(edit);
        addMenuItemWithSeparator(search);
        addMenuItemWithSeparator(cart);
        addMenuItemWithSeparator(checkout);
        addMenuItemWithSeparator(trade);
        addMenuItemWithSeparator(help);
        addMenuItemWithSeparator(admin);
        addMenuItemWithSeparator(logout);

        // Set menu bar to frame
        frame.setJMenuBar(menuBar);

        // Add MenuListener to search menu
        search.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                handleSearch();
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
    }

    // Method to initialize menu items
    private void initializeMenuItems() {
        // Add logout menu item with its handler
        JMenuItem logoutMenuItem = new JMenuItem("Are you sure?");
        logoutMenuItem.addActionListener(new LogoutHandler());
        logout.add(logoutMenuItem);

        // Add items inside edit menu with their handlers
        cutItem = new JMenuItem(new DefaultEditorKit.CutAction());
        copyItem = new JMenuItem(new DefaultEditorKit.CopyAction());
        pasteItem = new JMenuItem(new DefaultEditorKit.PasteAction());
        selectAllItem = new JMenuItem("Select All");

        edit.add(cutItem);
        edit.add(copyItem);
        edit.add(pasteItem);
        edit.add(selectAllItem);

        // Initialize help JMenuItem and add ActionListener
        helpItem = new JMenuItem("Help");
        helpItem.addActionListener(this);
        help.add(helpItem);

        // Initialize items for the cart menu
        viewCart = new JMenuItem("View Cart");
        clearCart = new JMenuItem("Clear Cart");
        viewCart.addActionListener(this);
        clearCart.addActionListener(this);
        cart.add(viewCart);
        cart.add(clearCart);

        // Initialize admin JMenuItem and add ActionListener
        adminItem = new JMenuItem("Admin");
        adminItem.addActionListener(this);
        admin.add(adminItem);
    }

    // Method to add menu item with separator
    private void addMenuItemWithSeparator(JMenu menu) {
        menuBar.add(menu);
        menuBar.add(new JSeparator(SwingConstants.VERTICAL));
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RibbonLayout::new);
    }

    // Inner class for handling logout action
    private class LogoutHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            SwingUtilities.invokeLater(LoginWindow::new);
        }
    }

    private void handleSearch() {
        List<BookDetails> searchResults = new ArrayList<>();

        do {
            String searchQuery = JOptionPane.showInputDialog(null, "Enter ISBN number, author, or book title:");
            if (searchQuery != null && !searchQuery.isEmpty()) {
                BookDetails book = BookDatabaseConnector.searchBook(searchQuery);
                if (book != null) {
                    searchResults.add(book);
                    int option = JOptionPane.showConfirmDialog(null, "Book found. Do you want to search more books?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.NO_OPTION)
                        break;
                } else {
                    JOptionPane.showMessageDialog(null, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    int option = JOptionPane.showConfirmDialog(null, "Do you want to search more books?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.NO_OPTION)
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a search query.", "Error", JOptionPane.ERROR_MESSAGE);
                int option = JOptionPane.showConfirmDialog(null, "Do you want to search more books?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.NO_OPTION)
                    break;
            }
        } while (true);

        // Display search results
        if (!searchResults.isEmpty()) {
            StringBuilder resultMessage = new StringBuilder("Search Results:\n\n");
            for (BookDetails book : searchResults) {
                resultMessage.append(book.toString()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, resultMessage.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No books found.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // ActionPerformed method for handling menu actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == helpItem) {
            JOptionPane.showMessageDialog(frame,
                    "Please call following number: +1 816-100-3163\nOr Email us: bookstore.manager@bookstore.com\nWe will respond as soon as possible within 24 hours.",
                    "Help",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == adminItem) {
            handleAdminClick();
        }
    }

    // Method to handle click on admin menu item
    private void handleAdminClick() {
        if (UserAuthentication.showAuthenticationDialog()) {
            // If authentication successful, show registration form
            UserRegistration.showRegistrationForm();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid user credentials");
        }
    }

	public void displaySearchResult(src.bookstore.search.BookDetails book) {
		// TODO Auto-generated method stub
		
	}
}
