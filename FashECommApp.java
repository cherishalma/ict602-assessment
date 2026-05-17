// Fashion E-Commerce Platform Main App

import java.util.*;

public class FashECommApp {
    private static List<Product> inventory = new ArrayList<>();
    private static List<String> orderHistory = new ArrayList<>();
    private static List<Product> cart = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Test user
    private static Customer myUser = new Customer("johndoe@student.vit.edu.au", "securepassword");

    public static void main(String[] args) {
        // Current inventory for customer to browse
        inventory.add(new Product(1, "Ugg Boots", 120.00, 5));
        inventory.add(new Product(2, "Akubra Hat", 240.00, 3));
        inventory.add(new Product(3, "Bec + Bridge Dress", 300.00, 8));

        // Log in
        System.out.println("Welcome to the Fashion E-Commerce App! Please enter your details below to log in.");
        System.out.print("Email: ");
        String emailInput = scanner.nextLine();
        System.out.print("Password: ");
        String passInput = scanner.nextLine();

        if (myUser.login(emailInput, passInput)) {
            System.out.println("Welcome back, John Doe!");
            showPortalSelection();
        } else {
            System.out.println("Oops! Please check your email and password and try again.");
        }
    }

    private static void showPortalSelection() {
        while (true) {
            System.out.println("\nType 1 for Customer Portal or 2 for Seller Portal: ");
            int choice = scanner.nextInt();
            if (choice == 1) customerPortal();
            else if (choice == 2) sellerPortal();
            else break;
        }
    }

    // Customer Portal
    private static void customerPortal() {
        while (true) {
            System.out.println("Welcome to the Customer Portal!");
            System.out.println("Loyalty Points: " + myUser.getLoyaltyPoints());
            System.out.println("Select a number to perform the next action.\n1. Browse products\n2. Add to cart\n3. View cart and checkout\n4. View history\n5. Back");
            int choice = scanner.nextInt();
            if (choice == 1) {
                for (Product p : inventory) System.out.println(p);
            } else if (choice == 2) {
                System.out.print("Please enter the product id of your choice: ");
                int id = scanner.nextInt();
                cart.add(inventory.get(id - 1));
                System.out.println("Successfully added to cart!");
            } else if (choice == 3) {
                double total = 0;
                for (Product p : cart) {
                    System.out.println("* " + p.getName() + " | $" + p.getPrice());
                    total += p.getPrice();
                }
                System.out.println("Your total is $" + total);
                System.out.print("Enter 1 to confirm your order, or 2 to cancel: ");
                
                // Save user selection
                int checkoutAction = scanner.nextInt();

                if (checkoutAction == 1) { // Customer confirms order
                    String summary = "You have purchased " + cart.size() + " item(s). Your total spend is $" + total;

                    // Updates customer order history
                    myUser.addOrderToHistory(summary, total);
                    cart.clear();

                    // Confirmation message and new loyalty points
                    System.out.println("Your order has been confirmed!");
                    System.out.println("Loyalty Points: " + myUser.getLoyaltyPoints());
                
                } else if (checkoutAction == 2) { // Customer cancels order
                    System.out.println("Your order has been cancelled. Please browse our product selection: ");
                    for (Product p : inventory) System.out.println(p);
                }
            } else if (choice == 4) {
                myUser.viewOrderHistory();
            } else break;
        }
    }

    // Seller Portal
    private static void sellerPortal() {
        while (true) {
            System.out.println("\nWelcome to the Seller Portal!\nSelect a number to perform the next action.\n1. Add product\n2. Update stock\n3. Back");
            int choice = scanner.nextInt();
            if (choice == 1) { // Add a new product
                System.out.print("Product Name: ");
                scanner.nextLine();
                String name = scanner.nextLine();
                System.out.print("Price: ");
                double price = scanner.nextDouble();
                inventory.add(new Product(inventory.size() + 1, name, price, 0));
            } else if (choice == 2) { // Update inventory levels of existing product
                System.out.print("Current inventory and stock levels: \n");
                for (Product p : inventory) System.out.println(p);
                System.out.print("Please enter the Product ID to update inventory level: ");
                int id = scanner.nextInt();
                System.out.print("Stock Level Updated: ");
                int stock = scanner.nextInt();
                inventory.get(id - 1).setStock(stock);
                System.out.println("You've successfully updated the inventory!");
            } else break;
        }
    }
}