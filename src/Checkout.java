public class Checkout {
    private static final double SHIPPING = 30.0;

    public static void process(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        double subtotal = cart.getSubtotal();

        // Calculate total weight of shippable items
        double totalWeight = 0.0;
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            if (product instanceof Shippable shippable) {
                totalWeight += shippable.getWeight() * quantity;
            }
        }

        // Calculate shipping cost based on total weight
        double total = subtotal + SHIPPING;

        if (customer.getBalance() < total) {
            throw new IllegalStateException("Insufficient balance");
        }

        // Call shipping service if needed
        if (totalWeight > 0) {
            ShippingService shippingService = new ShippingService();
            shippingService.shipItems(cart.getItems());
        }

        // Print checkout receipt and update quantities
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            int qty = item.getQuantity();
            System.out.println(qty + "x " + p.getName() + " " + (p.getPrice() * qty));
            p.decreaseQuantity(qty);
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.2f%n", SHIPPING);
        System.out.printf("Amount %.2f%n", total);

        customer.decreaseBalance(total);
    }
}
