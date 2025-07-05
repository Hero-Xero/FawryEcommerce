import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        if (product.isExpired()) throw new IllegalStateException(product.getName() + " is expired");
        if (quantity > product.getQuantity()) throw new IllegalStateException("Not enough stock for " + product.getName());

        // Optional: prevent duplicates
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                throw new IllegalStateException("Product already in cart");
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}
