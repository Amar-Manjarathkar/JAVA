import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookstoreInventory {
    private static final String FILENAME = "inventory.ser";  // File name for serialized inventory
    private Map<String, Book> inventory = new HashMap<>();    // In-memory storage: Key=ID, Value=Book (fast lookups)
    private Scanner scanner = new Scanner(System.in);         // For reading user input from console

    /**
     * Entry point of the application.
     * Initializes the app, loads existing inventory, and starts the menu loop.
     * @param args Command-line arguments (unused)
     */
    public static void main(String[] args) {
        BookstoreInventory app = new BookstoreInventory();
        app.loadInventory(); // Load on startup
        app.displayMenu();
    }

    /**
     * Displays the admin menu and handles user choices in a loop until exit.
     * Uses switch expression (Java 14+) for clean case handling.
     * Catches exceptions to prevent crashes from invalid input.
     */
    private void displayMenu() {
        while (true) {
            System.out.println("\n=== Online Bookstore Inventory Management ===");
            System.out.println("1. Add New Book");
            System.out.println("2. Update Stock of a Book");
            System.out.println("3. Update Stock for All Books of a Given Category");
            System.out.println("4. Set Discount for All Books of a Given Category");
            System.out.println("5. Remove Books Not in Stock for Last 6 Months");
            System.out.println("6. Save Inventory to File");
            System.out.println("7. Load Inventory from File");
            System.out.println("8. Display All Books");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> addNewBook();  // Add a new book to inventory
                    case 2 -> updateStockById();  // Update stock for a specific book by ID
                    case 3 -> updateStockByCategory();  // Bulk update stock for all books in a category
                    case 4 -> setDiscountByCategory();  // Bulk set discount for all books in a category
                    case 5 -> removeOutdatedBooks();  // Remove books with zero stock older than 6 months
                    case 6 -> saveInventory();  // Serialize and save inventory to file
                    case 7 -> loadInventory();  // Deserialize and load inventory from file
                    case 8 -> displayAllBooks();  // Print all books in inventory
                    case 9 -> {
                        saveInventory(); // Auto-save on exit
                        System.out.println("Exiting. Goodbye!");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Handles adding a new book to the inventory.
     * Prompts for all required details, validates ID format and duplicates,
     * then adds to the HashMap.
     * Throws NumberFormatException if parsing fails (caught in displayMenu).
     */
    private void addNewBook() throws NumberFormatException {
        try {
            System.out.print("Enter Book ID (e.g., 978-3-16-14410-0): ");
            String id = scanner.nextLine().trim();
            if (!Book.isValidId(id)) {
                System.out.println("Invalid ID format!");
                return;
            }
            if (inventory.containsKey(id)) {
                System.out.println("Book with this ID already exists!");
                return;
            }

            System.out.print("Enter Title: ");
            String title = scanner.nextLine().trim();
            System.out.print("Enter Author: ");
            String author = scanner.nextLine().trim();
            System.out.print("Enter Category (FICTION/NONFICTION/SCIENCE/HISTORY/TECHNOLOGY): ");
            Category category = Category.valueOf(scanner.nextLine().trim().toUpperCase());
            System.out.print("Enter Price (INR): ");
            double price = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Enter Stock Quantity: ");
            int stock = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter Publisher: ");
            String publisher = scanner.nextLine().trim();

            Book book = new Book(id, title, author, category, price, stock, LocalDate.now(), publisher);
            inventory.put(id, book);
            System.out.println("Book added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    /**
     * Updates the stock quantity for a specific book identified by its ID.
     * Retrieves book from HashMap, prompts for new stock, and calls setStock().
     * Handles not-found cases.
     */
    private void updateStockById() {
        try {
            System.out.print("Enter Book ID: ");
            String id = scanner.nextLine().trim();
            Book book = inventory.get(id);
            if (book == null) {
                System.out.println("Book not found!");
                return;
            }
            System.out.print("Enter new Stock Quantity: ");
            int newStock = Integer.parseInt(scanner.nextLine().trim());
            book.setStock(newStock);
            System.out.println("Stock updated successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid stock quantity.");
        }
    }

    /**
     * Bulk-updates stock for all books in a specified category.
     * Iterates over inventory values, matches category, and sets new stock.
     * Counts and reports updated books.
     * Throws NumberFormatException if parsing fails (caught in displayMenu).
     */
    private void updateStockByCategory() throws NumberFormatException {
        try {
            System.out.print("Enter Category (FICTION/NONFICTION/SCIENCE/HISTORY/TECHNOLOGY): ");
            Category category = Category.valueOf(scanner.nextLine().trim().toUpperCase());
            System.out.print("Enter new Stock Quantity for all books in this category: ");
            int newStock = Integer.parseInt(scanner.nextLine().trim());

            long updatedCount = inventory.values().stream()
                    .filter(book -> book.getCategory() == category)
                    .peek(book -> book.setStock(newStock))
                    .count();
            System.out.println(updatedCount + " books updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    /**
     * Bulk-sets discount percentage for all books in a specified category.
     * Iterates over inventory values, matches category, and sets new discount.
     * Counts and reports updated books.
     * Throws NumberFormatException if parsing fails (caught in displayMenu).
     */
    private void setDiscountByCategory() throws NumberFormatException {
        try {
            System.out.print("Enter Category (FICTION/NONFICTION/SCIENCE/HISTORY/TECHNOLOGY): ");
            Category category = Category.valueOf(scanner.nextLine().trim().toUpperCase());
            System.out.print("Enter Discount Percentage (0-100): ");
            double discount = Double.parseDouble(scanner.nextLine().trim());

            long updatedCount = inventory.values().stream()
                    .filter(book -> book.getCategory() == category)
                    .peek(book -> book.setDiscount(discount))
                    .count();
            System.out.println(updatedCount + " books updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    /**
     * Removes books that have zero stock and haven't been updated in over 6 months.
     * Uses Iterator to safely remove entries from HashMap while iterating.
     * Calculates cutoff date and counts removals.
     */
    private void removeOutdatedBooks() {
        LocalDate cutoffDate = LocalDate.now().minus(6, ChronoUnit.MONTHS);
        List<String> keysToRemove = inventory.entrySet().stream()
                .filter(entry -> {
                    Book book = entry.getValue();
                    return book.getStock() == 0 && book.getStockUpdateDate().isBefore(cutoffDate);
                })
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        int removedCount = keysToRemove.size();
        keysToRemove.forEach(inventory::remove);
        System.out.println(removedCount + " outdated books removed!");
    }

    /**
     * Saves the current inventory to a serialized file using ObjectOutputStream.
     * Uses try-with-resources for auto-closing streams.
     * Writes the entire HashMap for easy deserialization.
     */
    private void saveInventory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(inventory);
            System.out.println("Inventory saved to " + FILENAME + " successfully!");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    /**
     * Loads the inventory from a serialized file using ObjectInputStream.
     * Checks if file exists first; skips if not.
     * Deserializes into a new HashMap and replaces the current one.
     * Uses @SuppressWarnings for unchecked cast.
     */
    private void loadInventory() {
        File file = new File(FILENAME);
        if (!file.exists()) {
            System.out.println("No saved inventory found. Starting fresh.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            @SuppressWarnings("unchecked")
            Map<String, Book> loadedInventory = (Map<String, Book>) ois.readObject();
            inventory = loadedInventory;
            System.out.println("Inventory loaded from " + FILENAME + " successfully! Loaded " + inventory.size() + " books.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    /**
     * Displays all books in the inventory.
     * Iterates over HashMap values and prints each book's toString().
     * Shows total count; handles empty inventory.
     */
    private void displayAllBooks() {
        if (inventory.isEmpty()) {
            System.out.println("No books in inventory.");
            return;
        }
        System.out.println("\n=== Current Inventory ===");
        inventory.values().forEach(book -> System.out.println(book));
        System.out.println("Total Books: " + inventory.size());
    }
}