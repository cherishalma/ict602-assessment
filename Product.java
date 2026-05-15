// Handles the products

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getters allow other classes to view private
    public String getName() {return name; }
    public double getPrice() {return price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return id + ") " + name + " | $" + price + " | " + stock + " in stock.";
    }
}