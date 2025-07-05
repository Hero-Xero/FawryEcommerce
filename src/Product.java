import java.time.LocalDate;

public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;
    protected LocalDate expiryDate; // null if not expirable

    public Product(String name, double price, int quantity, LocalDate expiryDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void decreaseQuantity(int amount) { quantity -= amount; }

    public boolean isExpired() {
        return expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }
}
