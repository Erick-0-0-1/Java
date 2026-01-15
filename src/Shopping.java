import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class Shopping{

    public static void main(String[] args) {

        // Arrays for menu items and their prices
        String[] menuItems = {"Burger", "Pizza", "Ice Cream", "Fries", "Soda", "Salad", "Pasta"};
        double[] itemPrices = {5.99, 8.99, 3.49, 2.99, 1.99, 6.49, 7.99};
        boolean[] available = {true, true, true, true, true, true, true};

        // Shopping cart using ArrayList
        ArrayList<String> cartItems = new ArrayList<>();
        ArrayList<Double> cartPrices = new ArrayList<>();
        ArrayList<Integer> cartQuantities = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("$0.00");

        int choice;
        double total = 0.0;
        boolean ordering = true;

        System.out.println("==================================");
        System.out.println("    WELCOME TO FOOD ORDER SYSTEM   ");
        System.out.println("==================================\n");

        while (ordering) {
            displayMenu(menuItems, itemPrices, available);

            System.out.print("\nEnter item number (1-" + menuItems.length + "), 0 to checkout, or -1 to cancel an item: ");
            choice = scanner.nextInt();

            if (choice == 0) {
                // Checkout
                ordering = false;
            } else if (choice == -1) {
                // Cancel item from cart
                if (cartItems.isEmpty()) {
                    System.out.println("Your cart is empty!\n");
                } else {
                    removeFromCart(cartItems, cartPrices, cartQuantities, scanner);
                }
            } else if (choice > 0 && choice <= menuItems.length) {
                int itemIndex = choice - 1;

                if (!available[itemIndex]) {
                    System.out.println("Sorry, this item is currently unavailable!\n");
                    continue;
                }

                System.out.print("Enter quantity for " + menuItems[itemIndex] + ": ");
                int quantity = scanner.nextInt();

                if (quantity <= 0) {
                    System.out.println("Invalid quantity!\n");
                    continue;
                }

                // Add to cart
                double itemTotal = itemPrices[itemIndex] * quantity;

                // Check if item already in cart
                int existingIndex = cartItems.indexOf(menuItems[itemIndex]);
                if (existingIndex != -1) {
                    // Update existing item
                    int newQty = cartQuantities.get(existingIndex) + quantity;
                    cartQuantities.set(existingIndex, newQty);
                    cartPrices.set(existingIndex, itemPrices[itemIndex] * newQty);
                } else {
                    // Add new item
                    cartItems.add(menuItems[itemIndex]);
                    cartPrices.add(itemTotal);
                    cartQuantities.add(quantity);
                }

                total += itemTotal;

                System.out.println("\n✓ Added " + quantity + " x " + menuItems[itemIndex] + " to cart");
                System.out.println("  Price: " + df.format(itemPrices[itemIndex]) + " each");
                System.out.println("  Subtotal: " + df.format(itemTotal) + "\n");

            } else {
                System.out.println("Invalid choice! Please try again.\n");
            }
        }

        // Display final receipt
        displayReceipt(cartItems, cartPrices, cartQuantities, total, df);

        // Payment
        if (total > 0) {
            processPayment(total, scanner, df);
        }

        System.out.println("\n==================================");
        System.out.println("  Thank you for your order! ");
        System.out.println("==================================");

        scanner.close();
    }

    // Method to display the menu
    public static void displayMenu(String[] items, double[] prices, boolean[] available) {
        DecimalFormat df = new DecimalFormat("$0.00");

        System.out.println("========== MENU ==========");
        System.out.println("No.  Item          Price      Status");
        System.out.println("----------------------------------");

        for (int i = 0; i < items.length; i++) {
            String status = available[i] ? "Available" : "Unavailable";
            System.out.printf("%-3d %-13s %-10s %-12s%n",
                    (i+1), items[i], df.format(prices[i]), status);
        }
    }

    // Method to display receipt
    public static void displayReceipt(ArrayList<String> items, ArrayList<Double> prices,
                                      ArrayList<Integer> quantities, double total, DecimalFormat df) {

        if (items.isEmpty()) {
            System.out.println("\nNo items ordered. Have a nice day!");
            return;
        }

        System.out.println("\n========== RECEIPT ==========");
        System.out.println("Qty  Item          Price      Total");
        System.out.println("------------------------------------");

        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%-4d %-13s %-10s %-10s%n",
                    quantities.get(i), items.get(i),
                    df.format(prices.get(i)/quantities.get(i)),
                    df.format(prices.get(i)));
        }

        System.out.println("------------------------------------");
        System.out.printf("TOTAL: %23s%n", df.format(total));

        // Calculate tax (8%)
        double tax = total * 0.08;
        System.out.printf("TAX (8%%): %21s%n", df.format(tax));

        double grandTotal = total + tax;
        System.out.printf("GRAND TOTAL: %18s%n", df.format(grandTotal));
    }

    // Method to remove items from cart
    public static void removeFromCart(ArrayList<String> items, ArrayList<Double> prices,
                                      ArrayList<Integer> quantities, Scanner scanner) {

        System.out.println("\nItems in your cart:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i+1) + ". " + items.get(i) +
                    " (Qty: " + quantities.get(i) + ")");
        }

        System.out.print("Enter item number to remove (0 to cancel): ");
        int removeChoice = scanner.nextInt();

        if (removeChoice > 0 && removeChoice <= items.size()) {
            int index = removeChoice - 1;
            System.out.println("Removed " + items.get(index) + " from cart.");
            items.remove(index);
            prices.remove(index);
            quantities.remove(index);
        } else if (removeChoice != 0) {
            System.out.println("Invalid choice!");
        }
    }

    // Method to process payment
    public static void processPayment(double total, Scanner scanner, DecimalFormat df) {
        double tax = total * 0.08;
        double grandTotal = total + tax;

        System.out.println("\n========== PAYMENT ==========");
        System.out.println("Amount due: " + df.format(grandTotal));

        System.out.print("Enter payment amount: ");
        double payment = scanner.nextDouble();

        while (payment < grandTotal) {
            System.out.println("Insufficient payment! You need " +
                    df.format(grandTotal - payment) + " more.");
            System.out.print("Enter additional amount: ");
            payment += scanner.nextDouble();
        }

        double change = payment - grandTotal;
        System.out.println("\nPayment successful!");
        System.out.println("Change: " + df.format(change));

        System.out.print("Do you want a receipt? (yes/no): ");
        scanner.nextLine(); // Clear buffer
        String receiptChoice = scanner.nextLine().toLowerCase();

        if (receiptChoice.equals("yes") || receiptChoice.equals("y")) {
            System.out.println("Receipt printed. Keep it for your records.");
        }
    }
}