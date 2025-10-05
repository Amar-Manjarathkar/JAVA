
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
class Book implements Serializable {
    private static final long serialVersionUID = 1L;  // Version ID for serialization compatibility

    // Private fields to encapsulate data (following OOP principles)
    private String id;                    // Unique identifier (e.g., ISBN)
    private String title;                 // Book title
    private String author;                // Book author
    private Category category;            // Book category (enum for type safety)
    private double price;                 // Price in INR
    private int stock;                    // Available stock quantity
    private LocalDate stockUpdateDate;    // Date of last stock update
    private String publisher;             // Book publisher
    private double discount;              // Discount percentage (default 0.0)

    /**
     * Constructor to create a new Book instance.
     * Initializes discount to 0.0 by default.
     * @param id Unique book ID
     * @param title Book title
     * @param author Book author
     * @param category Book category
     * @param price Book price
     * @param stock Initial stock quantity
     * @param stockUpdateDate Initial stock update date
     * @param publisher Book publisher
     */
    public Book(String id, String title, String author, Category category, double price,
                int stock, LocalDate stockUpdateDate, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.stockUpdateDate = stockUpdateDate;
        this.publisher = publisher;
        this.discount = 0.0; // Default discount value
    }

    // Getters: Provide read-only access to fields (encapsulation)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }  // Note: In production, make ID immutable

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    /**
     * Getter for price.
     * @return Current price in INR
     */
    public double getPrice() { return price; }

    /**
     * Setter for price with validation.
     * Ensures price is non-negative.
     * @param price New price value
     * @throws IllegalArgumentException if price is negative
     */
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        this.price = price;
    }

    /**
     * Getter for stock quantity.
     * @return Current available stock
     */
    public int getStock() { return stock; }

    /**
     * Setter for stock with validation and side-effect.
     * Ensures stock is non-negative and auto-updates stockUpdateDate to today.
     * @param stock New stock quantity
     * @throws IllegalArgumentException if stock is negative
     */
    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        this.stock = stock;
        this.stockUpdateDate = LocalDate.now(); // Update date on stock change
    }

    public LocalDate getStockUpdateDate() { return stockUpdateDate; }
    public void setStockUpdateDate(LocalDate stockUpdateDate) { this.stockUpdateDate = stockUpdateDate; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    /**
     * Getter for discount percentage.
     * @return Current discount (0.0 to 100.0)
     */
    public double getDiscount() { return discount; }

    /**
     * Setter for discount with validation.
     * Ensures discount is between 0 and 100.
     * @param discount New discount percentage
     * @throws IllegalArgumentException if discount is out of range
     */
    public void setDiscount(double discount) {
        if (discount < 0 || discount > 100) throw new IllegalArgumentException("Discount must be between 0 and 100.");
        this.discount = discount;
    }

    /**
     * Overrides toString() to provide a human-readable representation of the book.
     * Used for console display in inventory listings.
     * @return Formatted string with all book details
     */
    @Override
    public String toString() {
        return "ID: " + id + " | Title: " + title + " | Author: " + author + " | Category: " + category +
                " | Price: " + price + " INR | Stock: " + stock + " | Last Updated: " + stockUpdateDate +
                " | Publisher: " + publisher + " | Discount: " + discount + "%";
    }

    /**
     * Static utility method to validate book ID format.
     * Uses regex to match a simplified ISBN-13 pattern (e.g., 978-3-16-148410-0).
     * @param id The ID string to validate
     * @return true if format is valid, false otherwise
     */
    public static boolean isValidId(String id) {
        // Basic check: 13 digits with hyphens, e.g., 978-3-16-148410-0
        return Pattern.matches("\\d{3}-\\d-\\d{2}-\\d{5}-\\d", id);
    }
}