import java.util.List;

public class ShippingService {
    public void shipItems(List<CartItem> items) {
        System.out.println("** Shipment notice **");

        double totalWeight = 0.0;

        for (CartItem item : items) {
            Product p = item.getProduct();
            int qty = item.getQuantity();

            if (p instanceof Shippable shippable) {
                double weightForAll = shippable.getWeight() * qty; // in kg
                totalWeight += weightForAll;
                System.out.printf("%dx %s %dg%n", qty, shippable.getName(), (int)(weightForAll * 1000));
            }
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }

}
