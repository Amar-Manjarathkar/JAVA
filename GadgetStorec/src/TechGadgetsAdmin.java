import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.LinkedHashMap; // Preserves insertion order
import java.util.stream.Collectors;


public class TechGadgetsAdmin {

    private static final Map<Integer, Gadget> inventory = new LinkedHashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        populateInitialData();

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addNewGadget();
                        break;
                    case 2:
                        updateGadgetStock();
                        break;
                    case 3:
                        applyDiscountToOldStock();
                        break;
                    case 4:
                        removeUnsoldVeryOldStock();
                        break;
                    case 5:
                        displayAllGadgets();
                        break;
                    case 0:
                        System.out.println("Exiting Admin Panel. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- TechGadgets Admin Panel ---");
        System.out.println("1. Add a new Gadget");
        System.out.println("2. Update stock of a Gadget");
        System.out.println("3. Add 25% discount on gadgets not sold in last 6 months");
        System.out.println("4. Remove gadgets listed over a year ago and never sold");
        System.out.println("5. Display all Gadgets");
        System.out.println("0. Exit");
        System.out.println("---------------------------------");
    }

    private static void addNewGadget() {
        try {
            System.out.println("\n--- Add New Gadget ---");
            System.out.print("Enter Category (LAPTOP, SMARTPHONE, TABLET, ACCESSORY): ");
            Category category = Category.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Enter Brand: ");
            String brand = scanner.nextLine();

            System.out.print("Enter Price (INR): ");
            double price = scanner.nextDouble();

            System.out.print("Enter Initial Stock: ");
            int stock = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (price <= 0 || stock < 0) {
                System.out.println("Price and stock must be positive. Gadget not added.");
                return;
            }

            Gadget newGadget = new Gadget(category, brand, price, stock);
            inventory.put(newGadget.getId(), newGadget);
            System.out.println("Successfully added new gadget:");
            System.out.println(newGadget);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Category. Please use one of the specified categories.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for price or stock. Please enter valid numbers.");
            scanner.nextLine(); // Clear the buffer
        }
    }

    private static void updateGadgetStock() {
        System.out.println("\n--- Update Gadget Stock ---");
        if (inventory.isEmpty()) {
            System.out.println("No gadgets in inventory to update.");
            return;
        }
        displayAllGadgets();
        try {
            System.out.print("Enter the ID of the gadget to update: ");
            int id = scanner.nextInt();

            Gadget gadget = inventory.get(id);
            if (gadget == null) {
                System.out.println("Gadget with ID " + id + " not found.");
                return;
            }

            System.out.print("Enter the new stock quantity: ");
            int newStock = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (newStock < 0) {
                System.out.println("Stock cannot be negative. Update failed.");
                return;
            }

            gadget.setStock(newStock);
            System.out.println("Stock updated successfully for Gadget ID " + id + ".");
            System.out.println(gadget);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for ID or stock.");
            scanner.nextLine(); // Clear the buffer
        }
    }

    private static void applyDiscountToOldStock() {
        System.out.println("\n--- Applying 25% Discount to Old Stock ---");
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        int count = 0;

        for (Gadget gadget : inventory.values()) {
            if (gadget.getLastSaleDate() != null && gadget.getLastSaleDate().isBefore(sixMonthsAgo)) {
                gadget.setDiscount(25.0);
                count++;
                System.out.println("Applied discount to: " + gadget);
            }
        }

        if (count == 0) {
            System.out.println("No gadgets found that haven't been sold in the last 6 months.");
        } else {
            System.out.println("Applied discount to " + count + " gadget(s).");
        }
    }

    private static void removeUnsoldVeryOldStock() {
        System.out.println("\n--- Removing Old, Unsold Stock ---");
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        // Using streams to find IDs to remove to avoid ConcurrentModificationException
        var idsToRemove = inventory.values().stream()
                .filter(g -> g.getListingDate().isBefore(oneYearAgo) && g.getLastSaleDate() == null)
                .map(Gadget::getId)
                .collect(Collectors.toList());

        if (idsToRemove.isEmpty()) {
            System.out.println("No gadgets found that were listed over a year ago and never sold.");
            return;
        }

        for (Integer id : idsToRemove) {
            Gadget removed = inventory.remove(id);
            System.out.println("Removed gadget: " + removed);
        }
        System.out.println("Removed " + idsToRemove.size() + " old, unsold gadget(s).");
    }

    private static void displayAllGadgets() {
        System.out.println("\n--- Current Gadget Inventory ---");
        if (inventory.isEmpty()) {
            System.out.println("The inventory is currently empty.");
        } else {
            inventory.values().forEach(System.out::println);
        }
        System.out.println("--------------------------------");
    }

    private static void populateInitialData() {
        // Gadgets that have been sold
        Gadget g1 = new Gadget(Category.LAPTOP, "Dell", 85000.0, 50);
        g1.setLastSaleDate(LocalDate.now().minusMonths(7)); // Qualifies for discount
        inventory.put(g1.getId(), g1);

        Gadget g2 = new Gadget(Category.SMARTPHONE, "Samsung", 72000.0, 100);
        g2.setLastSaleDate(LocalDate.now().minusMonths(2)); // Does not qualify
        inventory.put(g2.getId(), g2);

        // Gadgets that have never been sold
        Gadget g3 = new Gadget(Category.TABLET, "Apple", 45000.0, 30); // Listed recently
        inventory.put(g3.getId(), g3);

        Gadget g4 = new Gadget(Category.LAPTOP, "HP", 68000.0, 25);
        // Manually setting older listing date for testing removal logic
        try {
            java.lang.reflect.Field listingDateField = Gadget.class.getDeclaredField("listingDate");
            listingDateField.setAccessible(true);
            listingDateField.set(g4, LocalDate.now().minusYears(2));
        } catch (Exception e) { e.printStackTrace(); }
        inventory.put(g4.getId(), g4); // Qualifies for removal
    }
}