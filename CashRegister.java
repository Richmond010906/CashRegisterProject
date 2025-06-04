package cash.register;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Product {
    String name;
    double price;
    int quantity;

    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    double getTotalPrice() {
        return price * quantity;
    }

    public String toString() {
        return name + " | Price: P" + String.format("%.2f", price) + " | Qty: " + quantity + " | Total: P" + String.format("%.2f", getTotalPrice());
    }
}

class User {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class CashRegister {
    static Scanner ashi = new Scanner(System.in);
    static List<Product> cart = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static String currentUser = "";

    // Regex patterns
    static final String USERNAME_REGEX = "^[\\w]{5,15}$";
    static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,20}$";

    // Input validation with try-catch
    public static double getValidDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = ashi.nextLine().trim();
                double value = Double.parseDouble(input);
                if (value > 0) return value;
                System.out.println("Error: Please enter a positive number.\n");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format. Please enter a valid decimal number.\n");
            }
        }
    }

    public static int getValidInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = ashi.nextLine().trim();
                int value = Integer.parseInt(input);
                if (value > 0) return value;
                System.out.println("Error: Please enter a positive integer.\n");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format. Please enter a valid integer.\n");
            }
        }
    }

    public static int getValidChoice(String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                String input = ashi.nextLine().trim();
                int choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) return choice;
                System.out.println("Error: Please enter a number between " + min + " and " + max + ".\n");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.\n");
            }
        }
    }

    // User signup with enhanced error handling
    public static void signUp() {
        System.out.println("\n----- User Sign-up -----");

        while (true) {
            try {
                System.out.print("\nEnter username (5-15 characters, alphanumeric): ");
                String username = ashi.nextLine().trim();
                
                if (username.isEmpty()) {
                    System.out.println("Error: Username cannot be empty.");
                    continue;
                }
                
                if (!username.matches(USERNAME_REGEX)) {
                    System.out.println("Error: Username must be 5-15 characters long and contain only letters, numbers, and underscores.");
                    continue;
                }

                // Check if username already exists
                boolean userExists = false;
                for (User user : users) {
                    if (user.username.equals(username)) {
                        userExists = true;
                        break;
                    }
                }
                
                if (userExists) {
                    System.out.println("Error: Username already exists. Please choose a different username.");
                    continue;
                }

                System.out.print("Enter password (8-20 chars, include uppercase, lowercase, number): ");
                String password = ashi.nextLine().trim();
                
                if (!password.matches(PASSWORD_REGEX)) {
                    System.out.println("Error: Password must be 8-20 characters long and include at least one uppercase letter, one lowercase letter, and one number.");
                    continue;
                }

                users.add(new User(username, password));
                System.out.println("\n Signup successful! You can now log in.\n");
                break;
                
            } catch (Exception e) {
                System.out.println("Error: An unexpected error occurred during signup. Please try again.");
            }
        }
    }

    // User login with enhanced error handling
    public static boolean login() {
        System.out.println("\n----- User Log in -----");

        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            try {
                System.out.print("\nEnter username: ");
                String username = ashi.nextLine().trim();

                if (username.isEmpty()) {
                    System.out.println("Error: Username cannot be empty.");
                    attempts++;
                    continue;
                }

                System.out.print("Enter password: ");
                String password = ashi.nextLine().trim();

                if (password.isEmpty()) {
                    System.out.println("Error: Password cannot be empty.");
                    attempts++;
                    continue;
                }

                for (User user : users) {
                    if (user.username.equals(username) && user.password.equals(password)) {
                        currentUser = username;
                        System.out.println("\n Login successful! Welcome, " + username + "!\n");
                        return true;
                    }
                }

                attempts++;
                System.out.println("\nError: Invalid username or password. Attempts remaining: " + (MAX_ATTEMPTS - attempts));

            } catch (Exception e) {
                System.out.println("Error: An unexpected error occurred during login.");
                attempts++;
            }
        }

        System.out.println("\nToo many failed attempts. Returning to main menu...\n");
        return false;
    }

    // Enhanced cart features
    public static void addProduct() {
        try {
            System.out.print("\nEnter product name: ");
            String name = ashi.nextLine().trim();
            
            if (name.isEmpty()) {
                System.out.println("Error: Product name cannot be empty.\n");
                return;
            }

            double price = getValidDouble("Enter product price: P");
            int quantity = getValidInt("Enter product quantity: ");

            cart.add(new Product(name, price, quantity));
            System.out.println("\n Product '" + name + "' added to cart!\n");
            
        } catch (Exception e) {
            System.out.println("Error: Failed to add product. Please try again.\n");
        }
    }

    public static void updateProductQuantity() {
        if (cart.isEmpty()) {
            System.out.println("\nCart is empty. Nothing to update.\n");
            return;
        }

        try {
            displayCart();
            int index = getValidChoice("Enter product number to update (1-" + cart.size() + "): ", 1, cart.size()) - 1;
            
            Product product = cart.get(index);
            System.out.println("Current quantity for '" + product.name + "': " + product.quantity);
            
            int newQuantity = getValidInt("Enter new quantity: ");
            product.quantity = newQuantity;
            
            System.out.println("\n Quantity updated successfully!\n");
            
        } catch (Exception e) {
            System.out.println("Error: Failed to update product quantity.\n");
        }
    }

    public static void removeProduct() {
        if (cart.isEmpty()) {
            System.out.println("\nCart is empty. Nothing to remove.\n");
            return;
        }

        try {
            displayCart();
            int index = getValidChoice("Enter product number to remove (1-" + cart.size() + "): ", 1, cart.size()) - 1;
            
            Product removedProduct = cart.remove(index);
            System.out.println("\n '" + removedProduct.name + "' removed from cart!\n");
            
        } catch (Exception e) {
            System.out.println("Error: Failed to remove product.\n");
        }
    }

    public static void displayCart() {
        System.out.println("\n----- Cart Items -----");
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.\n");
            return;
        }

        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            Product p = cart.get(i);
            System.out.println((i + 1) + ". " + p);
            total += p.getTotalPrice();
        }

        System.out.printf("\nTotal Price: P%.2f\n\n", total);
    }

    // Transaction logging
    public static void logTransaction(double total, double payment, double change) {
        try (FileWriter writer = new FileWriter("transactions.txt", true)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            writer.write("=== TRANSACTION LOG ===\n");
            writer.write("Date & Time: " + now.format(formatter) + "\n");
            writer.write("Cashier: " + currentUser + "\n");
            writer.write("Items Purchased:\n");
            
            for (Product p : cart) {
                writer.write("  " + p.name + " | Qty: " + p.quantity + " | Price: P" + 
                           String.format("%.2f", p.price) + " | Subtotal: P" + 
                           String.format("%.2f", p.getTotalPrice()) + "\n");
            }
            
            writer.write("Total Amount: P" + String.format("%.2f", total) + "\n");
            writer.write("Payment Received: P" + String.format("%.2f", payment) + "\n");
            writer.write("Change Given: P" + String.format("%.2f", change) + "\n");
            writer.write("========================\n\n");
            
            System.out.println("Transaction logged successfully!");
            
        } catch (IOException e) {
            System.out.println("Error: Failed to log transaction to file.");
        }
    }

    public static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("\nCart is empty. Add some products first.\n");
            return;
        }

        try {
            double total = 0;
            for (Product p : cart) {
                total += p.getTotalPrice();
            }

            System.out.println("\n----- CHECKOUT -----");
            displayCart();
            System.out.printf("Total amount due: P%.2f\n", total);
            
            double payment;
            do {
                payment = getValidDouble("Enter payment amount: P");
                if (payment < total) {
                    System.out.printf("Insufficient payment! You need at least P%.2f more.\n", (total - payment));
                }
            } while (payment < total);

            double change = payment - total;
            System.out.printf("\n Payment accepted!\n");
            System.out.printf("Change: P%.2f\n", change);
            
            // Log the transaction
            logTransaction(total, payment, change);
            
            cart.clear();
            System.out.println("\nThank you for your purchase!\n");
            
        } catch (Exception e) {
            System.out.println("Error: Checkout failed. Please try again.\n");
        }
    }

    public static void viewTransactionHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"))) {
            System.out.println("\n----- TRANSACTION HISTORY -----");
            String line;
            boolean hasTransactions = false;
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                hasTransactions = true;
            }
            
            if (!hasTransactions) {
                System.out.println("No transactions found.");
            }
            
            System.out.println("\n");
            
        } catch (FileNotFoundException e) {
            System.out.println("\nNo transaction history found. Complete a purchase to create transaction logs.\n");
        } catch (IOException e) {
            System.out.println("Error: Failed to read transaction history.\n");
        }
    }

    // Main method
    public static void main(String[] args) {
        System.out.println("\n==================================");
        System.out.println("      Welcome to ShopMate!!");
        System.out.println("   Enhanced Cash Register System");
        System.out.println("==================================");

        while (true) {
            try {
                System.out.println("\n1. Sign Up");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                
                int choice = getValidChoice("Choose an option (1-3): ", 1, 3);

                switch (choice) {
                    case 1:
                        signUp();
                        break;
                    case 2:
                        if (login()) {
                            runCashRegister();
                        }
                        break;
                    case 3:
                        System.out.println("\n==================================");
                        System.out.println("  Thank you for using ShopMate!");
                        System.out.println("      Have a wonderful day!");
                        System.out.println("==================================\n");
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error: An unexpected error occurred. Please try again.\n");
            }
        }
    }

    public static void runCashRegister() {
        while (true) {
            try {
                System.out.println("\n======= ShopMate Cash Register =======");
                System.out.println("Logged in as: " + currentUser);
                System.out.println("1. Add Product to Cart");
                System.out.println("2. View Cart");
                System.out.println("3. Update Product Quantity");
                System.out.println("4. Remove Product from Cart");
                System.out.println("5. Checkout");
                System.out.println("6. View Transaction History");
                System.out.println("7. Log out");

                int choice = getValidChoice("Enter choice (1-7): ", 1, 7);

                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        displayCart();
                        break;
                    case 3:
                        updateProductQuantity();
                        break;
                    case 4:
                        removeProduct();
                        break;
                    case 5:
                        checkout();
                        break;
                    case 6:
                        viewTransactionHistory();
                        break;
                    case 7:
                        System.out.println("\nLogging out... Goodbye, " + currentUser + "!\n");
                        currentUser = "";
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error: An unexpected error occurred in the cash register. Please try again.\n");
            }
        }
    }
}
