import java.util.ArrayList;
import java.util.List;

// Customer profile
public class Customer extends User {
    private int loyaltyPoints;
    private List<String> orderHistory;

    public Customer(String email, String password) {
        super(email, password);
        this.loyaltyPoints = 0;
        this.orderHistory = new ArrayList<>();
    }
        
    // Add purchase to history
    public void addOrderToHistory(String details, double total) {
        orderHistory.add(details);
    }

    public void viewOrderHistory() {
        System.out.println("Your Past Orders:");
        if (orderHistory.isEmpty()) {
            System.out.println("You have not purchased any items.");
        } else {
            for (String record: orderHistory) {
                System.out.println("* " + record);
            }
        }
    }
}