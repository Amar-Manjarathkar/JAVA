import java.time.LocalDate;
import java.util.Objects;

public class Gadget {
    private static int idCounter = 0;

    private final int id;
    private final Category category;
    private final String brand;
    private final double price;
    private final LocalDate listingDate;

    private int stock;
    private double discount;
    private LocalDate stockUpdateDate;
    private LocalDate lastSaleDate;

    public Gadget(Category category, String brand, double price, int initialStock) {
        this.id = ++idCounter;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.stock = initialStock;
        this.listingDate = LocalDate.now();
        this.stockUpdateDate = LocalDate.now();
        this.lastSaleDate = null; // null indicates it has never been sold
        this.discount = 0.0;
    }

    // Getters
    public int getId() { return id; }
    public Category getCategory() { return category; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public LocalDate getListingDate() { return listingDate; }
    public int getStock() { return stock; }
    public double getDiscount() { return discount; }
    public LocalDate getStockUpdateDate() { return stockUpdateDate; }
    public LocalDate getLastSaleDate() { return lastSaleDate; }

    // Setters
    public void setStock(int stock) {
        this.stock = stock;
        this.stockUpdateDate = LocalDate.now();
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setLastSaleDate(LocalDate lastSaleDate) {
        this.lastSaleDate = lastSaleDate;
    }

    @Override
    public String toString() {
        String finalPrice = String.format("%,.2f", price * (1 - discount / 100.0));
        return String.format(
                "Gadget ID: %-3d | %-12s | %-10s | Stock: %-4d | Price: ₹%-10s | Discount: %-2.0f%% | Listed: %s | Last Sale: %s",
                id,
                category,
                brand,
                stock,
                finalPrice,
                discount,
                listingDate,
                (lastSaleDate == null ? "Never" : lastSaleDate.toString())
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gadget gadget = (Gadget) o;
        return id == gadget.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}